package com.dyz.skeleton.common.exception;

public class BizException extends Exception{
    public static final BizErr Authenticated_Fail = new BizErr(101,"用户名或密码错误");
    public static final BizErr Use_Exist = new BizErr(102,"用户名已存在");
    public static final BizErr VerifyCode_Error = new BizErr(103,"验证码错误");

    private int code;
    public BizException(BizErr bizErr) {
        super(bizErr.getMsg());
        this.code = bizErr.getCode();
    }
    public int getCode() {
        return code;
    }
    public static class BizErr{
        private int code;
        private String msg;
        private BizErr(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
