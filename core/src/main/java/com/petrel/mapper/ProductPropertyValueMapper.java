package com.petrel.mapper;

import com.petrel.domain.ProductPropertyValue;

import java.util.List;

public interface ProductPropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductPropertyValue record);

    ProductPropertyValue selectByPrimaryKey(Long id);

    List<ProductPropertyValue> selectAll();

    int updateByPrimaryKey(ProductPropertyValue record);

    List<ProductPropertyValue> getProValByProductId(Long productId);
}