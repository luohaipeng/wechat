package com.petrel.mapper;

import com.petrel.domain.ProductDetails;

import java.util.List;

public interface ProductDetailsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductDetails record);

    ProductDetails selectByPrimaryKey(Long id);

    List<ProductDetails> selectAll();

    int updateByPrimaryKey(ProductDetails record);

    ProductDetails getDetailByProductId(Long productId);

}