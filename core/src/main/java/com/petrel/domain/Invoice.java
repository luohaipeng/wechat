package com.petrel.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Invoice extends BaseDomain{

    private Byte invoiceType;

    private Byte invoiceHead;

    private Long userId;

    private Long orderId;

    private Byte invoiceContent;

    private String invoicePhone;

    private String invoiceEmail;


}