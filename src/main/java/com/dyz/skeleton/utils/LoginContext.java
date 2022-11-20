package com.dyz.skeleton.utils;

import com.dyz.skeleton.domain.account.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoginContext {
    private LoginContext() {
    }

    public static LoginUser getLoginUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal != null && principal instanceof LoginUser){
            return (LoginUser)principal;
        }
        return null;
    }
}
