package com.janglejay.myEnum;


public enum UserEnum {
    TOURIST("tourist", 0),
    FORMAL("formal_user", 1);
    private String authDesc;
    private Integer authCode;
    UserEnum(String authDesc, Integer authCode) {
        this.authDesc = authDesc;
        this.authCode = authCode;
    }

    public String getAuthDesc() {
        return authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }

    public Integer getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Integer authCode) {
        this.authCode = authCode;
    }

    public static UserEnum getByAuthCode(Integer code) {
        for (UserEnum e : values()) {
            if (e.authCode.equals(code)) return e;
        }
        return null;
    }
}
