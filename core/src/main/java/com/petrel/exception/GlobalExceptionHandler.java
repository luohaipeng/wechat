package com.petrel.exception;

import com.petrel.vo.JSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public JSONResult exceptionHandler(Exception e){
        e.printStackTrace();
        JSONResult jsonResultVo = new JSONResult();
        if(e instanceof GlobalException){
            GlobalException ge = (GlobalException) e;
            jsonResultVo.setExceptionMsg(ge.getCodeMsg());
        }else{
            jsonResultVo.setExceptionMsg(CodeMsg.SERVER_EXPTION);
        }
        return jsonResultVo;
    }
}
