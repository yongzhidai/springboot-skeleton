package com.dyz.skeleton.configuration.filter;

import com.dyz.skeleton.domain.account.LoginUser;
import com.dyz.skeleton.infrastructure.UserGateway;
import com.dyz.skeleton.utils.JwtTokenUtil;
import org.slf4j.MDC;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private UserGateway userGateway;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        UUID uuid = UUID.randomUUID();
        MDC.put("traceid", uuid.toString().replace("-",""));
        try {
            String token = request.getHeader("token");
            if (!StringUtils.hasLength(token)) {
                chain.doFilter(request, response);
                return;
            }
            // 如果请求头中有token，则进行解析，并且设置认证信息
            Long userId = JwtTokenUtil.getUserIdFromToken(token);
            if (userId == null) {//已失效，未登录状态
                chain.doFilter(request, response);
                return;
            }
            LoginUser loginUser = userGateway.getLoginUserById(userId);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }finally {
            MDC.remove("traceid");
        }

    }
}
