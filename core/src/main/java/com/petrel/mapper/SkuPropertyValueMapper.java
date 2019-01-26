package com.petrel.mapper;

import com.petrel.domain.SkuPropertyValue;

import java.util.List;

public interface SkuPropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SkuPropertyValue record);

    SkuPropertyValue selectByPrimaryKey(Long id);

    List<SkuPropertyValue> selectAll();

    int updateByPrimaryKey(SkuPropertyValue record);

    List<SkuPropertyValue> getProListByProId(Long propertyId);
}