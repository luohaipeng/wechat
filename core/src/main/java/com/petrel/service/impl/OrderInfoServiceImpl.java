package com.petrel.service.impl;


import com.petrel.domain.*;
import com.petrel.mapper.InvoiceMapper;
import com.petrel.mapper.OrderInfoMapper;
import com.petrel.mapper.OrderProductMapper;
import com.petrel.mapper.OrderProductPropertyMapper;
import com.petrel.qo.OrderQueryObject;
import com.petrel.qo.PageResult;
import com.petrel.service.*;
import com.petrel.util.IdGenerateUtil;
import com.petrel.vo.CarVo;
import com.petrel.vo.OrderStatusChangeVo;
import com.petrel.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class OrderInfoServiceImpl implements IOrderInfoService {


    /*@Autowired
    private RedisTemplate redisTemplate;*/

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private OrderProductPropertyMapper orderProductPropertyMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private IUserAddressService userAddressService;

    @Autowired
    private IProductSkuService productSkuService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderActionService orderActionService;

    @Override
    public PageResult query(OrderQueryObject qo) {
        int count = orderInfoMapper.queryCount(qo);
        if(count == 0){
            return PageResult.empty();
        }
        List<OrderInfo> orderInfoList = orderInfoMapper.queryList(qo);

        return new PageResult(orderInfoList,count,qo.getCurrentPage(),qo.getPageSize());
    }

    @Override
    public OrderInfo getById(Long id) {
        return orderInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void createOrder(OrderVo vo)  {
        UserLogin userLogin = vo.getUserLogin();
        //创建订单对象，设置用户id
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userLogin.getId());
        //生成订单编号
        long orderSn = IdGenerateUtil.get().nextId();
        orderInfo.setOrderSn(orderSn+"");
        //通过用户收货地址id获取用户收货地址对象，并设置到订单信息中
        UserAddress userAddress = userAddressService.getById(vo.getUserAddressId());
        orderInfo.setZipcode(userAddress.getZipcode());
        orderInfo.setPhone(userAddress.getPhone());
        orderInfo.setDistrict(userAddress.getDistrict());
        orderInfo.setCity(userAddress.getCity());
        orderInfo.setAddress(userAddress.getAddress());
        orderInfo.setConsignee(userAddress.getConsignee());
        orderInfo.setCountry(userAddress.getCountry());
        orderInfo.setProvince(userAddress.getProvince());
        //设置订单的其他信息，比如订单状态，付款状态，物流状态，下单时间等
        orderInfo.setOrderStatus(new Byte("0"));
        orderInfo.setFlowStatus(new Byte("0"));
        orderInfo.setPayStatus(new Byte("0"));
        orderInfo.setPayType(vo.getPay().getPayType());
        orderInfo.setOrderTime(new Date());
        //设置订单总金额
        BigDecimal orderAmount = new BigDecimal(0);
        orderInfo.setOrderAmount(orderAmount);
        //插入该订单
        orderInfoMapper.insert(orderInfo);
        //获取购物车商品列表遍历，并生成订单商品明细
        for(CarVo car : vo.getCarList()){
            //car的信息转移到订单商品明细中
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(orderInfo.getId());
            orderProduct.setSkuId(car.getSkuId());
            orderProduct.setProductNumber(car.getNumber());
            //通过skuId获取sku对象
            ProductSku sku = productSkuService.getById(car.getSkuId());
            orderProduct.setProductPrice(sku.getPrice());
            //计算订单商品明细中的商品小计
            orderProduct.setTotalPrice(orderProduct.getProductPrice().multiply(new BigDecimal(orderProduct.getProductNumber())));
            //把订单商品明细中的商品小计累加到订单总额中
            orderAmount = orderAmount.add(orderProduct.getTotalPrice());
            //设置product对象相关信息
            Product product = productService.getProductById(sku.getProductId());
            orderProduct.setProductName(product.getName());
            orderProduct.setProductImg(product.getImage());
            //插入该订单商品明细
            orderProductMapper.insert(orderProduct);
            //获取sku属性，并设置到订单商品明细中
            sku.getProductSkuPropertyList().forEach(skuProperty -> {
                //创建一个订单商品明细属性
                OrderProductProperty orderProductProperty = new OrderProductProperty();
                orderProductProperty.setValue(skuProperty.getValue());
                orderProductProperty.setName(skuProperty.getSkuProperty().getName());
                orderProductProperty.setOrderProductId(orderProduct.getId());
                orderProductPropertyMapper.insert(orderProductProperty);
            });
            //判断Redis中有无该购物车数据，如果有则删除
            /*String carKey = RedisConstant.CAR_KEY
                    .replace("USERID", userLogin.getId() + "")
                    .replace("PRODUCTID", car.getProductId() + "")
                    .replace("SKUID", car.getSkuId() + "");
            if(redisTemplate.hasKey(carKey))
                redisTemplate.delete(carKey);*/

        }

        //从新设置订单总额
        orderInfo.setOrderAmount(orderAmount);
        //更新该订单
        orderInfoMapper.updateByPrimaryKey(orderInfo);
        //判断是否需要开发票，如果要则,设置发票人和发票对应的订单，并插入发票信息
        Invoice invoice = vo.getInvoice();
        if(invoice != null){
            invoice.setOrderId(orderInfo.getId());
            invoice.setUserId(userLogin.getId());
            invoiceMapper.insert(invoice);
        }
        //记录订单操作日志
        orderActionService.addOrderAction(orderInfo,userLogin.getUserName(),"前台","生成订单");
    }

    @Override
    public void changeStatus(OrderStatusChangeVo vo) {
        //获取订单对象
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(vo.getOrderId());
        //判断提交上来的订单修改类型
        switch (vo.getChangeType()){
            //如果是1的话，把订单的orderStatus改为1
            case 1:
                orderInfo.setOrderStatus(new Byte("1"));
                break;
                //如果是2的话，把订单的flowStatus改为1
            case 2:
                orderInfo.setFlowStatus(new Byte("1"));
                break;
                //如果是3的话，把订单的flowStatus改为3
            case 3:
                orderInfo.setFlowStatus(new Byte("3"));
                break;
                //如果是4的话，不需要修改订单状态
            case 4:
                break;
            //如果是5的话，把orderStatus改为2,把flowStatus改为2
            case 5:
                orderInfo.setFlowStatus(new Byte("2"));
                orderInfo.setOrderStatus(new Byte("2"));
                break;
        }
        //更新该订单
        orderInfoMapper.updateByPrimaryKey(orderInfo);

        //记录订单操作日志
        orderActionService.addOrderAction(orderInfo,"admin",vo.getPlace(),vo.getNote());
    }
}














