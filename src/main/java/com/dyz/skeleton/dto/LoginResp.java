package com.dyz.skeleton.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResp {
    private String userName;
    private String token;
}
