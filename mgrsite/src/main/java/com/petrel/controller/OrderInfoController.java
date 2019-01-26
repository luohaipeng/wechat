package com.petrel.controller;

import com.petrel.domain.OrderAction;
import com.petrel.domain.OrderInfo;
import com.petrel.domain.UserInfo;
import com.petrel.qo.OrderQueryObject;
import com.petrel.qo.PageResult;
import com.petrel.service.IOrderActionService;
import com.petrel.service.IOrderInfoService;
import com.petrel.service.IUserInfoService;
import com.petrel.vo.JSONResult;
import com.petrel.vo.OrderStatusChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/orders")
public class OrderInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IOrderActionService orderActionService;

    /**
     * /orders/view
     * GET
     * 进入订单管理界面
     * @return
     */
    @GetMapping(value = "/view")
    public String orderInfo(){

        return "order/order_info";
    }

    /**
     * /orders
     * GET
     * @param map
     * @param qo
     * @return
     */
    @GetMapping(value = "")
    public String page(Map map, OrderQueryObject qo){
        PageResult page = orderInfoService.query(qo);
        map.put("page",page);
        return "order/order_list";
    }

    /**
     * /orders/{id}
     * GET
     * @param id
     * @param map
     * @return
     */
    @GetMapping(value = "/{id}")
    public String detail(@PathVariable("id") Long id, Map map){
        OrderInfo orderInfo = orderInfoService.getById(id);
        UserInfo userInfo = userInfoService.getById(orderInfo.getUserId());
        List<OrderAction> orderActionList =  orderActionService.getOrderAction(id);
        map.put("userInfo",userInfo);
        map.put("orderInfo",orderInfo);
        map.put("orderActionList",orderActionList);
        return "order/order_detail";
    }

    /**
     * /orders/{id}
     * PATCH
     * @param vo
     * @return
     */
    @PostMapping(value = "/{id}")
    @ResponseBody
    public JSONResult changeStatus(@PathVariable("id") Long id, OrderStatusChangeVo vo){
        vo.setOrderId(id);
        JSONResult jsonResult = new JSONResult();
        vo.setPlace("后台");
        orderInfoService.changeStatus(vo);
        return jsonResult;
    }
}






















