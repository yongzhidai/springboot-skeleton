package com.dyz.skeleton.dto;

import com.dyz.skeleton.common.exception.BizException;
import com.dyz.skeleton.common.exception.ParamException;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Data
public class LoginReq {
    private String userName;
    private String password;
    private String kaptcha;

    public Authentication getAuthentication() {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,password);
        authentication.setDetails(kaptcha);
        return authentication;
    }

    public void checkParam()throws ParamException {
        if(!StringUtils.hasText(userName) || !StringUtils.hasText(password)){
            throw new ParamException("用户名或密码不能为空");
        }
        if(!StringUtils.hasText(kaptcha)){
            throw new ParamException("验证码不能为空");
        }
    }
}
