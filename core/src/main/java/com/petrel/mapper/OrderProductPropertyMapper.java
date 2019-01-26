package com.petrel.mapper;

import com.petrel.domain.OrderProductProperty;

import java.util.List;

public interface OrderProductPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderProductProperty record);

    OrderProductProperty selectByPrimaryKey(Long id);

    List<OrderProductProperty> selectAll();

    int updateByPrimaryKey(OrderProductProperty record);
}