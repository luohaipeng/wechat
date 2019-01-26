package com.petrel.mapper;

import com.petrel.domain.OrderInfo;
import com.petrel.qo.OrderQueryObject;

import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    List<OrderInfo> selectAll();

    int updateByPrimaryKey(OrderInfo record);

    int queryCount(OrderQueryObject qo);

    List<OrderInfo> queryList(OrderQueryObject qo);
}