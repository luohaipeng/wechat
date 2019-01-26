package com.petrel.controller;

import com.petrel.domain.Catalog;
import com.petrel.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private ICatalogService catalogService;

    @GetMapping(value = "/getAllCatalog")
    @ResponseBody
    public List<Catalog> getAllCatalog(){
        return catalogService.getAllCatalog();
    }
}
