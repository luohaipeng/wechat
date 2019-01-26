package com.petrel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户相关资源
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 个人中心
     */
    @GetMapping(value = "/index")
    public String index(){
        return "user/index";
    }
}
