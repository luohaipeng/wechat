package com.petrel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @GetMapping(value = "list")
    public String list(){
        return "order/list";
    }
}
