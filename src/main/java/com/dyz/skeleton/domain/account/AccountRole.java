package com.dyz.skeleton.domain.account;

public enum AccountRole {
    ADMIN(0,"ADMIN","管理员"),
    PLAIN(1,"PLAIN","普通用户"),
    ;


    int code;
    String flag;
    String desc;

    AccountRole(int code, String flag, String desc) {
        this.code = code;
        this.flag = flag;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getFlag() {
        return flag;
    }
    public static AccountRole valueOf(int code){
        for(AccountRole item : values()){
            if(code == item.getCode()){
                return item;
            }
        }
        throw new RuntimeException("未识别的账号角色");
    }
}
