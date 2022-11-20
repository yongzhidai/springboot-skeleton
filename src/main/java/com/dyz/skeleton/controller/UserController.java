package com.dyz.skeleton.controller;

import com.dyz.skeleton.application.UserBiz;
import com.dyz.skeleton.common.ResultDto;
import com.dyz.skeleton.dto.LoginReq;
import com.dyz.skeleton.dto.LoginResp;
import com.dyz.skeleton.dto.RegisterReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserBiz userBiz;

    @PostMapping("/register")
    public ResultDto<Void> register(@RequestBody RegisterReq req) throws Exception{
        req.checkParam();
        userBiz.register(req);
        return ResultDto.success();
    }

    @PostMapping("/login")
    public ResultDto<LoginResp> login(@RequestBody LoginReq req) throws Exception{
        req.checkParam();
        return ResultDto.success(userBiz.login(req));
    }
    @PostMapping("/logout")
    public ResultDto<Void> logout(){
        userBiz.logout();
        return ResultDto.success(null);
    }
}
