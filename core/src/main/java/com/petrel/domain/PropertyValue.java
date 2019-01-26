package com.petrel.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class PropertyValue extends BaseDomain {

    private static final long serialVersionUID=1L;

    private Long propertyId;

    private String value;


}