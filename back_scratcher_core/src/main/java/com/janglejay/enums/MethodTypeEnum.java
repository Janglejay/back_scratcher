package com.janglejay.enums;

public enum MethodTypeEnum {
    NORMAL(0),
    STATIC(1),
    PRIVATE(2);
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
