package com.petrel.service;

import com.petrel.domain.OrderAction;
import com.petrel.domain.OrderInfo;

import java.util.List;


public interface IOrderActionService {


    List<OrderAction> getOrderAction(Long orderId);


    /**
     * 新增一条订单操作日志
     * @param orderInfo
     * @param admin
     * @param place
     * @param note
     */
    void addOrderAction(OrderInfo orderInfo, String admin, String place, String note);
}
