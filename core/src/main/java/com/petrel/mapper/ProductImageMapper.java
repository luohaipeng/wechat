package com.petrel.mapper;

import com.petrel.domain.ProductImage;

import java.util.List;

public interface ProductImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductImage record);

    ProductImage selectByPrimaryKey(Long id);

    List<ProductImage> selectAll();

    int updateByPrimaryKey(ProductImage record);

    List<ProductImage> getImageByProductId(Long productId);

}