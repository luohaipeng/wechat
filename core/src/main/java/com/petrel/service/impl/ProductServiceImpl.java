package com.petrel.service.impl;

import com.petrel.domain.Product;
import com.petrel.domain.ProductDetails;
import com.petrel.domain.ProductImage;
import com.petrel.domain.ProductPropertyValue;
import com.petrel.mapper.ProductDetailsMapper;
import com.petrel.mapper.ProductImageMapper;
import com.petrel.mapper.ProductMapper;
import com.petrel.mapper.ProductPropertyValueMapper;
import com.petrel.qo.PageResult;
import com.petrel.qo.QueryObject;
import com.petrel.service.IProductService;
import com.petrel.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductDetailsMapper productDetailsMapper;
    @Autowired
    private ProductImageMapper productImageMapper;
    @Autowired
    private ProductPropertyValueMapper productPropertyValueMapper;
    

    @Override
    public List<Product> getAllProduct() {
        return productMapper.selectAll();
    }

    @Override
    public PageResult productPage(QueryObject qo) {
        int totalCount = productMapper.queryCount(qo);
        List data = productMapper.queryList(qo);
        return new PageResult(data,totalCount,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public void save(ProductVo productVo) {
        //保存商品对象
        productVo.getProduct().setCreatedDate(new Date());
        productVo.getProduct().setLastModifiedDate(new Date());
        int record = productMapper.insert(productVo.getProduct());
        if(record > 0){
            //保存商品详细
            productVo.getProductDetails().setProductId(productVo.getProduct().getId());
            productDetailsMapper.insert(productVo.getProductDetails());
            //保存商品相册
            List<ProductImage> productImagesList = productVo.getProductImagesList();
            productImagesList.forEach(image -> {
                if(image.getImagePath() != null && image.getImagePath().trim().length() > 0){
                    image.setProductId(productVo.getProduct().getId());
                    productImageMapper.insert(image);
                }
            });
            //保存商品属性
            List<ProductPropertyValue> productPropertyValueList = productVo.getProductPropertyValueList();
            productPropertyValueList.forEach(proVal ->{
                proVal.setProductId(productVo.getProduct().getId());
                productPropertyValueMapper.insert(proVal);
            });
        }
    }

    @Override
    public Product getProductById(Long productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public ProductVo getProductVo(Long productId) {
        ProductVo productVo = new ProductVo();
        productVo.setProduct(productMapper.selectByPrimaryKey(productId));
        //productVo.setProductDetails(productDetailsMapper.getDetailByProductId(productId));
        productVo.setProductImagesList(productImageMapper.getImageByProductId(productId));
        productVo.setProductPropertyValueList(productPropertyValueMapper.getProValByProductId(productId));
        return productVo;
    }

    @Override
    public ProductDetails getProductDetail(Long productId) {
        return productDetailsMapper.getDetailByProductId(productId);
    }
}
