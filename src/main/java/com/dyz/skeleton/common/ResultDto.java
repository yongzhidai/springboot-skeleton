package com.dyz.skeleton.common;

import com.dyz.skeleton.common.exception.BizException;
import com.dyz.skeleton.common.exception.ParamException;
import com.dyz.skeleton.dto.LoginResp;
import lombok.Getter;

@Getter
public class ResultDto<T> {
    private int code;
    private String msg;
    private T data;

    public ResultDto(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultDto<Void> notLogin(){
        ResultDto<Void> resultDto = new ResultDto<>(-1,"尚未登录",null);
        return resultDto;
    }

    public static <T> ResultDto<T> success(){
        ResultDto<T> resultDto = new ResultDto<>(0,"成功",null);
        return resultDto;
    }

    public static <T> ResultDto<T> success(T data){
        ResultDto<T> resultDto = new ResultDto<>(0,"成功",data);
        return resultDto;
    }

    public static <T> ResultDto<T> paramFail(ParamException e) {
        ResultDto<T> resultDto = new ResultDto<>(1,e.getMessage(),null);
        return resultDto;
    }
    public static <T> ResultDto<T> bizFail(BizException e) {
        ResultDto<T> resultDto = new ResultDto<>(e.getCode(),e.getMessage(),null);
        return resultDto;
    }
    public static <T> ResultDto<T> sysFail(Exception e) {
        ResultDto<T> resultDto = new ResultDto<>(3,"系统繁忙，请稍后重试",null);
        return resultDto;
    }
}
