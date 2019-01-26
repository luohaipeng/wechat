package com.petrel.service;

import com.petrel.domain.OrderInfo;
import com.petrel.qo.OrderQueryObject;
import com.petrel.qo.PageResult;
import com.petrel.vo.OrderStatusChangeVo;
import com.petrel.vo.OrderVo;


public interface IOrderInfoService {

    void createOrder(OrderVo vo);

    PageResult query(OrderQueryObject qo);

    OrderInfo getById(Long id);

    /**
     * 订单状态修改服务
     * @param vo
     */
    void changeStatus(OrderStatusChangeVo vo);
}
