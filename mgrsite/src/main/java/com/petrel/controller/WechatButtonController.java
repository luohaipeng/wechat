package com.petrel.controller;

import com.petrel.service.IWechatButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WechatButtonController {

    @Autowired
    private IWechatButtonService wechatButtonService;

    @RequestMapping(value = "/getAll")
    public List getWechatButton(){
        return wechatButtonService.getAllWechatButtom();
    }
}


















