package com.petrel.mapper;

import com.petrel.domain.Property;

import java.util.List;

public interface PropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Property record);

    Property selectByPrimaryKey(Long id);

    List<Property> selectAll();

    int updateByPrimaryKey(Property record);

    List<Property> getPropertyByCatalogId(Long catalogId);
}