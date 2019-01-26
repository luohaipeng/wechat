package com.petrel.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
}
