package com.dyz.skeleton.configuration;

import com.alibaba.fastjson.JSONObject;
import com.dyz.skeleton.common.ResultDto;
import com.dyz.skeleton.common.exception.BizException;
import com.dyz.skeleton.configuration.filter.JWTAuthenticationFilter;
import com.dyz.skeleton.domain.account.AccountRole;
import com.dyz.skeleton.infrastructure.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class WebSecurityConfiguration {
    @Resource
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/user/login","/user/register","/vc.jpg").anonymous()
                        .antMatchers("/admin/**").hasAuthority(AccountRole.ADMIN.getFlag())
                        .anyRequest().permitAll())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new UserAuthenticationEntryPoint())
                .and()
                .build();
    }


    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            if(authException instanceof InsufficientAuthenticationException){
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSONObject.toJSONString(ResultDto.notLogin()));
                return;
            }
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(ResultDto.bizFail(new BizException(BizException.Authenticated_Fail))));

        }
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }

}
