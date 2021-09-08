package com.janglejay.enums;

public enum MethodTypeEnum {
    NORMAL(0),
    FINALORSTATIC(1);
    Integer code;
    MethodTypeEnum(Integer code) {
        this.code = code;
    }
    public Integer getCode() {
        return this.code;
    }
    public MethodTypeEnum getByCode(Integer code) {
        for (MethodTypeEnum value : values()) {
            if (value.getCode().equals(code)) return value;
        }
        return null;
    }
}
