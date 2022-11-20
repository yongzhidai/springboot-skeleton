package com.dyz.skeleton.controller;

import com.dyz.skeleton.domain.account.LoginUser;
import com.dyz.skeleton.utils.LoginContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class WelcomeController {

    @RequestMapping("/hello")
    public String hello(){
        LoginUser loginUser = LoginContext.getLoginUser();
        log.info("hello");
        log.warn("hello");
        if(loginUser == null){
            log.error("未登录");
            return "hello(未登录)";
        }
        return "hello("+loginUser.getUsername()+")";
    }
}
