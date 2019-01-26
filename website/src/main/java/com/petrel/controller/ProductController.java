package com.petrel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品资源
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @GetMapping(value = "/index")
    public String index(){
        return "product/index";
    }

    @GetMapping(value = "/detail")
    public String detail(){
        return "product/detail";
    }

    @GetMapping(value = "/list")
    public String list() {
        return "product/list";
    }
}
