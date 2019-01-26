package com.petrel.vo;

import com.petrel.domain.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Setter@Getter
public class ProductVo implements Serializable{

    private Product product;
    private ProductDetails productDetails;
    private List<ProductImage> productImagesList = new ArrayList<>();
    private List<ProductPropertyValue> productPropertyValueList = new ArrayList<>();
    private List<ProductSku> productSkuList;


}
