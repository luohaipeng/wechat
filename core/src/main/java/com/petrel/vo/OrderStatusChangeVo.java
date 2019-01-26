package com.petrel.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by luohaipeng on 2018/2/24.
 */
@Setter@Getter
public class OrderStatusChangeVo implements Serializable{

    private Long orderId;
    private Integer changeType;
    private String note;
    private String userName;
    private String place;
}
