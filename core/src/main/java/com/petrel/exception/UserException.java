package com.petrel.exception;

public class UserException extends GlobalException {

    public UserException(){
        super();
    }
    public UserException(CodeMsg codeMsg) {
        super(codeMsg);
    }
}
