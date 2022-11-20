package com.dyz.skeleton.application;

import com.dyz.skeleton.common.exception.BizException;
import com.dyz.skeleton.domain.account.LoginUser;
import com.dyz.skeleton.dto.LoginReq;
import com.dyz.skeleton.dto.LoginResp;
import com.dyz.skeleton.dto.RegisterReq;
import com.dyz.skeleton.infrastructure.UserGateway;
import com.dyz.skeleton.utils.JwtTokenUtil;
import com.dyz.skeleton.utils.VerifyCodeUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserBiz {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserGateway userGateway;

    public void register(RegisterReq req) throws Exception {
        VerifyCodeUtil.verify(req.getKaptcha());
        if(userGateway.isExist(req.getUserName())){
            throw new BizException(BizException.Use_Exist);
        }
        userGateway.registerUser(req);
    }

    public LoginResp login(LoginReq req) throws BizException {
        VerifyCodeUtil.verify(req.getKaptcha());
        Authentication authenticate = authenticationManager.authenticate(req.getAuthentication());
        if(authenticate == null){
            throw new BizException(BizException.Authenticated_Fail);
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String token = JwtTokenUtil.generateToken(loginUser.getUserId());
        return new LoginResp(loginUser.getUsername(),token);
    }

    public void logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }


}
