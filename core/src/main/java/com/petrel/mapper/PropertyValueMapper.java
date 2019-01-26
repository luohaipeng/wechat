package com.petrel.mapper;

import com.petrel.domain.PropertyValue;

import java.util.List;

public interface PropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PropertyValue record);

    PropertyValue selectByPrimaryKey(Long id);

    List<PropertyValue> selectAll();

    int updateByPrimaryKey(PropertyValue record);

    List<PropertyValue> getProListByProId(Long propertyId);
}