package com.petrel.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter@Getter
public class ProductSku extends BaseDomain{

    private Long productId;

    private String code;

    private BigDecimal price;

    private Integer stock;

    private List<ProductSkuProperty> productSkuPropertyList;
}