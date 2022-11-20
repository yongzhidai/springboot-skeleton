package com.dyz.skeleton.infrastructure;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dyz.skeleton.domain.account.LoginUser;
import com.dyz.skeleton.dto.RegisterReq;
import com.dyz.skeleton.infrastructure.db.entity.UserPO;
import com.dyz.skeleton.infrastructure.db.mapper.UserPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserGateway implements UserDetailsService {
    @Resource
    private UserPOMapper userPOMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserPO> queryWrapper = new LambdaQueryWrapper<>(UserPO.class);
        queryWrapper.eq(UserPO::getName,username);
        UserPO userPO = userPOMapper.selectOne(queryWrapper);
        if(userPO == null){
            throw new UsernameNotFoundException("");
        }
        return LoginUser.from(userPO);
    }

    public LoginUser getLoginUserById(Long userId){
        UserPO userPO = userPOMapper.selectById(userId);
        return LoginUser.from(userPO);
    }

    public boolean isExist(String userName){
        LambdaQueryWrapper<UserPO> queryWrapper = new LambdaQueryWrapper<>(UserPO.class);
        queryWrapper.eq(UserPO::getName,userName);
        Long count = userPOMapper.selectCount(queryWrapper);
        return count > 0;
    }

    public void registerUser(RegisterReq req) {
        UserPO userPO = new UserPO();
        userPO.setName(req.getUserName());
        userPO.setNickName(req.getNickName());
        userPO.setPassword(passwordEncoder.encode(req.getPassword()));
        userPO.setRole(0);
        userPO.setCreateTime(new Date());
        userPO.setUpdateTime(new Date());
        userPOMapper.insert(userPO);
    }
}
