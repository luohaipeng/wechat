package com.petrel.vo;

import com.petrel.exception.CodeMsg;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter@Getter
public class JSONResult implements Serializable{


    private Boolean success = true;
    private String message = "success";
    private Integer code = 200;
    private Object result;

    public void setExceptionMsg(CodeMsg codeMsg){
        this.message = codeMsg.getMessage();
        this.code = codeMsg.getCode();
        this.success = false;
    }
}
