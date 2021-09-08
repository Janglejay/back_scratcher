package com.janglejay.enums;

public enum ReturnValueTypeEnum {
    DORETURN(0),
    DONOTHING(1);
    Integer code;
    ReturnValueTypeEnum(Integer code) {
        this.code = code;
    }
    public Integer getCode() {
        return this.code;
    }
    public ReturnValueTypeEnum getByCode(Integer code) {
        for (ReturnValueTypeEnum value : values()) {
            if (value.getCode().equals(code)) return value;
        }
        return null;
    }
}
