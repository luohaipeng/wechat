package com.petrel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 店铺相关资源
 */
@Controller
@RequestMapping(value = "/shop")
public class ShopController {

    @GetMapping(value = "/index")
    public String index(){
        return "shop/index";
    }
}
