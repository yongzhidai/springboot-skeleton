package com.dyz.skeleton.domain.account;

import com.dyz.skeleton.infrastructure.db.entity.UserPO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class LoginUser implements UserDetails {
    private Long userId;
    private String userName;
    private String password;
    private String role;

    public static LoginUser from(UserPO userPO) {
        LoginUser loginUser = new LoginUser(
                userPO.getId(),
                userPO.getName(),
                userPO.getPassword(),
                AccountRole.valueOf(userPO.getRole()).getFlag());
        return loginUser;
    }

    private LoginUser(Long userId, String userName, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
