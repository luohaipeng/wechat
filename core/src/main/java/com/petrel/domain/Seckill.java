package com.petrel.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter@Getter
public class Seckill extends BaseDomain {

    private ProductSku sku;

    private Date startTime;

    private Date endTime;

    private Byte status;

}