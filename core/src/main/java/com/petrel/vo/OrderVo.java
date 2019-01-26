package com.petrel.vo;

import com.petrel.domain.Invoice;
import com.petrel.domain.UserLogin;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Setter@Getter
public class OrderVo implements Serializable{

    private Long userAddressId;
    private PayVo pay;
    private List<CarVo> carList;
    private Invoice invoice;
    private UserLogin userLogin;
}
