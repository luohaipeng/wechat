package com.petrel.controller;


import com.petrel.domain.*;
import com.petrel.qo.PageResult;
import com.petrel.qo.ProductQueryObject;
import com.petrel.service.*;
import com.petrel.util.UploadUtil;
import com.petrel.vo.JSONResult;
import com.petrel.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/products")
public class ProductController {

    @Value("${file.path}")
    private String filePath;

    @Autowired
    private IProductService productService;
    @Autowired
    private ICatalogService catalogService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private IPropertyService propertyService;
    @Autowired
    private IPropertyValueService propertyValueService;
    @Autowired
    private IProductSkuService productSkuService;
    @Autowired
    private ISkuPropertyService skuPropertyService;



    /**
     * /products/view
     * GET
     * 商品管理界面
     */
    @GetMapping(value = "/view")
    public String product(){
        return "product/product";
    }

    /**
     * /products
     * GET
     * 商品列表（带分页）
     */
    @GetMapping(value = "")
    public String productPage(ProductQueryObject qo, Map map){
        PageResult pageResult = productService.productPage(qo);
        map.put("pageResult",pageResult);
        return "product/product_list";
    }

    /**
     * /products/add
     * GET
     * 添加商品页面
     * @param map
     * @return
     */
    @GetMapping(value = "/add")
    public String add(Map map){
        List<Catalog> catalogList = catalogService.getAllCatalog();
        catalogList.remove(0);
        List<Brand> brandList = brandService.getAllBrand();
        map.put("catalogList",catalogList);
        map.put("brandList",brandList);
        return "product/product_add";
    }

    /**
     * /products
     * 新增商品
     * POST
     * @param productVo
     * @return
     */
    @PostMapping(value = "")
    @ResponseBody
    public JSONResult save(ProductVo productVo) {
        JSONResult jsonResultVo = new JSONResult();
        productService.save(productVo);
        return jsonResultVo;

    }

    /**
     *
     * @param upload ckedit默认找upload变量名
     * @param request ckedit上传图片需要用到
     * @param response ckedit上传图片需要用到
     */
    @RequestMapping(value = "/ckeditUploadImg")
    public void ckeditUploadImg(MultipartFile upload, HttpServletRequest request,HttpServletResponse response){
        try {
            //上传文件并返回文件名
            String fileName = UploadUtil.upload(upload, filePath);
            // 结合ckeditor功能
            // imageContextPath为图片在服务器地址，如/upload/123.jpg,非绝对路径
            String imageContextPath = "/upload/"+fileName;
            response.setContentType("text/html;charset=UTF-8");
            String callback = request.getParameter("CKEditorFuncNum");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");
            out.println("</script>");
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 上传商品图片
     * @param img
     * @return
     */
    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public String uploadImage(MultipartFile img){
        String fileName = UploadUtil.upload(img, filePath);
        return "/upload/"+fileName;
    }

    /**
     * /products/{id}/skus
     * 进入生成商品sku或者sku管理界面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping(value = "/{id}/skus")
    public String generateSku(@PathVariable("id") Long productId, Map map){
        //通过商品id获取整个商品对象
        Product product = productService.getProductById(productId);
        map.put("product",product);

        //如果该商品有生成过sku
        List<ProductSku> skuList = productSkuService.getProductSku(productId);
        if(skuList.size() > 0){
            map.put("skuList",skuList);
            ArrayList<String> skuPropertyList = new ArrayList<>();
            skuList.get(0).getProductSkuPropertyList().forEach(skuPro -> {
                skuPropertyList.add(skuPro.getSkuProperty().getName());
            });
            map.put("skuPropertyList",skuPropertyList);
            //那么就进入到sku管理界面
            return "product_sku/sku_list";
        }
        //否则，进入sku生成界面

        //通过该商品对象中的分类，获取该分类下的sku属性
        List<SkuProperty> skuProList = skuPropertyService.getPropertyByCatalogId(product.getCatalog().getId());
        map.put("skuPropertyList",skuProList);
        return "product_sku/generate_sku";
    }

    /**
     * 通过商品id获取整个商品对象
     */
    @GetMapping(value = "/{id}")
    public String getProduct(@PathVariable("id")Long productId,Model model){
        ProductVo productVo = productService.getProductVo(productId);
        model.addAttribute("productVo",productVo);
        List<Catalog> catalogList = catalogService.getAllCatalog();
        catalogList.remove(0);
        List<Brand> brandList = brandService.getAllBrand();
        model.addAttribute("catalogList",catalogList);
        model.addAttribute("brandList",brandList);
        model.addAttribute("propertyList",propertyService.getProValByCatalogId(productVo.getProduct().getCatalog().getId()));
        return "product/product_add";
    }

    /**
     * 获取商品详细介绍
     */
    @GetMapping(value = "/detail")
    @ResponseBody
    public String getDetail(Long productId){
        ProductDetails productDetails = productService.getProductDetail(productId);
        return productDetails.getDetails();
    }
}
