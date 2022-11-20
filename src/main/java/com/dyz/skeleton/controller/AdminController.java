package com.dyz.skeleton.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/list")
    public String listOrder(){
        return "listOrder";
    }
}
