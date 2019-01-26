package com.petrel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping(value = "/main")
    public String main(){

        return "main";
    }

    @GetMapping(value = "/index")
    public String index(){

        return "/index";
    }

    @GetMapping(value = "/")
    public String rootPath(){

        return "forward:/main";
    }
}
