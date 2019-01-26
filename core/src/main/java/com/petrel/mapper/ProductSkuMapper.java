package com.petrel.mapper;

import com.petrel.domain.ProductSku;

import java.util.List;

public interface ProductSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSku record);

    ProductSku selectByPrimaryKey(Long id);

    List<ProductSku> selectAll();

    int updateByPrimaryKey(ProductSku record);

    List<ProductSku> getProductSku(Long productId);
}