package com.petrel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/discounts")
public class DiscountsController {

    @GetMapping(value = "")
    public String discounts(){
        return "discounts/discounts";
    }
}
