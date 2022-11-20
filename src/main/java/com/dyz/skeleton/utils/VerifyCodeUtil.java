package com.dyz.skeleton.utils;

import com.dyz.skeleton.common.exception.BizException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class VerifyCodeUtil {
    private static final String SESSION_KEY = "kaptcha";
    private VerifyCodeUtil() {
    }
    public static void save(String kaptcha){
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        req.getSession().setAttribute(VerifyCodeUtil.SESSION_KEY, kaptcha);
    }

    public static void verify(String kaptcha)throws BizException {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionKaptcha = (String) req.getSession().getAttribute(SESSION_KEY);
        if(sessionKaptcha == null || !kaptcha.equalsIgnoreCase(sessionKaptcha)){
            throw new BizException(BizException.VerifyCode_Error);
        }
    }
}
