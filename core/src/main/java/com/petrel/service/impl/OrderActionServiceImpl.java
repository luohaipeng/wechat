package com.petrel.service.impl;

import com.petrel.domain.OrderAction;
import com.petrel.domain.OrderInfo;
import com.petrel.mapper.OrderActionMapper;
import com.petrel.service.IOrderActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class OrderActionServiceImpl implements IOrderActionService {

    @Autowired
    private OrderActionMapper orderActionMapper;

    @Override
    public List<OrderAction> getOrderAction(Long orderId) {
        return orderActionMapper.getOrderAction(orderId);
    }

    @Override
    public void addOrderAction(OrderInfo orderInfo, String admin, String place, String note) {
        OrderAction orderAction = new OrderAction();
        orderAction.setActionTime(new Date());
        orderAction.setFlowStatus(orderInfo.getFlowStatus());
        orderAction.setOrderStatus(orderInfo.getOrderStatus());
        orderAction.setActionPlace(place);
        orderAction.setActionUser(admin);
        orderAction.setOrderId(orderInfo.getId());
        orderAction.setActionNote(note);
        orderAction.setPayStatus(orderInfo.getPayStatus());
        orderActionMapper.insert(orderAction);
    }
}
