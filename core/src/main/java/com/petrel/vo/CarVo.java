package com.petrel.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Setter@Getter
public class CarVo implements Serializable {

    private Long productId;
    private Long skuId;
    private int number;
    private BigDecimal price;
    private Byte status;
    private String productName;
    private String productImg;
    private Map<String,String> skuProperty;
    private Boolean select;
}
