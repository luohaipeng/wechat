package com.petrel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 客说相关资源
 */
@Controller
@RequestMapping(value = "/speak")
public class SpeakController {

    /**
     * 客说首页
     */
    @GetMapping(value = "/index")
    public String speak(){
        return "speak/index";
    }

    /**
     * 发布客说界面
     */
    @GetMapping(value = "/view")
    public String view(){
        return "speak/view";
    }

    /**
     *客说详细界面
     */
    @GetMapping(value = "/detail")
    public String detail(){
        return "speak/detail";
    }

}
