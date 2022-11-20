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
public class RegisterReq {
    private String userName;
    private String nickName;
    private String password;
    private String kaptcha;

    public Authentication getAuthentication() {
        return new UsernamePasswordAuthenticationToken(userName,password);
    }

    public void checkParam()throws ParamException {
        if(!StringUtils.hasText(userName) || !StringUtils.hasText(password)){
            throw new ParamException("用户名或密码不能为空");
        }
        if(!StringUtils.hasText(nickName)){
            throw new ParamException("用户昵称不能为空");
        }
        if(!StringUtils.hasText(kaptcha)){
            throw new ParamException("验证码不能为空");
        }
    }
}
