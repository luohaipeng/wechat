package com.petrel.vo;

import com.petrel.domain.Product;
import com.petrel.domain.SkuProperty;
import com.petrel.domain.SkuPropertyValue;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Setter@Getter
public class GenerateVo implements Serializable{

    private Product product;
    private List<SkuProperty> skuPropertyList;
    private List<SkuPropertyValue> skuPropertyValueList;
}
