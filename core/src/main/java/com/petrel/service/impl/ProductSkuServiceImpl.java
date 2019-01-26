package com.petrel.service.impl;

import com.petrel.domain.*;
import com.petrel.mapper.CatalogMapper;
import com.petrel.mapper.ProductSkuMapper;
import com.petrel.mapper.ProductSkuPropertyMapper;
import com.petrel.service.IProductSkuService;
import com.petrel.vo.GenerateVo;
import com.petrel.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class ProductSkuServiceImpl implements IProductSkuService {

    @Autowired
    private CatalogMapper catalogMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductSkuPropertyMapper productSkuPropertyMapper;

    @Override
    public List<Map<String, Object>> generateSku(GenerateVo vo) {

        Product product = vo.getProduct();
        List<SkuProperty> skuPropertyList = vo.getSkuPropertyList();
        List<SkuPropertyValue> skuPropertyValueList = vo.getSkuPropertyValueList();
        //生成sku编码前缀(使用MySQL自定义函数)
        String codePrefix = getCodePrefix(product);
        //用map对数据进行分类，key为属性的id，value为该属性下的属性值：Map<String,List<String>>
        Map<String,List<String>> mapList = getMapList(skuPropertyList,skuPropertyValueList);
        //把map数据转成List<List<String>>,准备递归
        List<List<String>> recursiveList = new ArrayList<List<String>>();
        skuPropertyList.forEach(pro -> {
            recursiveList.add(mapList.get(pro.getId()+""));
        });
        //进行递归算法,最后递归出来的结果为List<List<String>>
        List<List<String>> resultList = new ArrayList<List<String>>();
        recursive(resultList,recursiveList,0,new ArrayList<String>());
        //最后把list数据转成map到前台显示List<Map<String,Object>>
        List<Map<String,Object>> skuList = new ArrayList<Map<String,Object>>();
        for(int i=0;i<resultList.size();i++){
            Map<String, Object> sku = new HashMap<>();
            sku.put("code",codePrefix+(i+1));
            sku.put("price",product.getBasePrice());
            for(int j=0;j<resultList.get(i).size();j++){
                sku.put(skuPropertyList.get(j).getId()+"",resultList.get(i).get(j));
            }
            skuList.add(sku);
        }
        return skuList;
    }



    /**
     * 递归方法
     * @param resultList 最终递归出来的sku数据集合
     * @param recursiveList 用于递归的集合
     * @param layer 递归的层级
     * @param data 每一个sku数据
     */
    private void recursive(List<List<String>> resultList, List<List<String>> recursiveList, int layer, List<String> data) {

        //判断当前的递归的层级是否为最后一级
        if(recursiveList.size() - 1 > layer){
            //不是最后一层
            recursiveList.get(layer).forEach(val -> {
                List<String> list = new ArrayList<>(data);
                list.add(val);
                recursive(resultList,recursiveList,layer+1,list);
            });

        }else{
            //是最后一层
            recursiveList.get(layer).forEach(val -> {
                List<String> list = new ArrayList<>(data);
                list.add(val);
                resultList.add(list);
            });
        }
    }

    private Map<String,List<String>> getMapList(List<SkuProperty> skuPropertyList, List<SkuPropertyValue> skuPropertyValueList) {
        //定义最终需要返回的数据
        Map<String, List<String>> mapList = new HashMap<>();
        //遍历sku属性，拿出sku属性id作为map的key，value创建一个空集合
        skuPropertyList.forEach(skuProperty -> {
            mapList.put(skuProperty.getId()+"",new ArrayList<String>());
        });
        //遍历sku属性值集合，没遍历出一个sku属性值就拿出该属性值对应的属性id，用属性id作为key，去map获取对应的value
        //该value是一个集合，并且把该遍历出来的属性值设置到集合中
        skuPropertyValueList.forEach(skuPropertyValue -> {
            mapList.get(skuPropertyValue.getSkuPropertyId()+"").add(skuPropertyValue.getValue());
        });

        /*//遍历前端选择sku属性集合
        skuPropertyList.forEach(pro -> {
            //判断该sku属性的id是否存在mapList的key中
            if(!mapList.containsKey(pro.getId()+"")){
                //如果不存在，创建一个List集合，用于存放该sku属性下的属性值
                mapList.put(pro.getId()+"",new ArrayList<String>());
            }
            //遍历前端编辑的sku属性值集合
            skuPropertyValueList.forEach(val -> {
                //判断当前遍历的属性id和当前遍历的属性值对应的属性id是否一样
                if(pro.getId() == val.getSkuPropertyId()){
                    //如果相等的话，就把该属性值添加到该属性对应的属性值的集合中
                    mapList.get(pro.getId()+"").add(val.getValue());
                }
            });

        });*/
        return mapList;
    }

    /**
     * 获取sku编码前缀
     * @param product
     * @return
     */
    private String getCodePrefix(Product product) {
        List<Catalog> catalogList = catalogMapper.getParentCatalogList(product.getCatalog().getId());
        catalogList.remove(0);
        StringBuilder sb = new StringBuilder();
        //变历分类
        for(int i=0;i<catalogList.size();i++){
            Catalog catalog = catalogList.get(i);
            //判断变量出的分类是否为顶级分类
            if(i==0){
                //如果是顶级分类，则获取分类编号的前两位
                sb.append(catalog.getCode().length() > 2 ? catalog.getCode().substring(0,2) : catalog.getCode());
            }else{
                // 否则获取排序号
                sb.append(catalog.getSort() > 9 ? catalog.getSort() : "0"+catalog.getSort());
            }
        }
        return sb.append(product.getId()).toString();
    }

    @Override
    public void save(ProductVo vo) {
        List<ProductSku> productSkuList = vo.getProductSkuList();
        productSkuList.forEach(sku -> {
            sku.setProductId(vo.getProduct().getId());
            productSkuMapper.insert(sku);
            sku.getProductSkuPropertyList().forEach(skuPro -> {
                skuPro.setProductSkuId(sku.getId());
                productSkuPropertyMapper.insert(skuPro);
            });
        });
    }

    @Override
    public List<ProductSku> getProductSku(Long productId) {
        return productSkuMapper.getProductSku(productId);
    }

    @Override
    public ProductSku getById(Long skuId) {
        return productSkuMapper.selectByPrimaryKey(skuId);
    }
}
