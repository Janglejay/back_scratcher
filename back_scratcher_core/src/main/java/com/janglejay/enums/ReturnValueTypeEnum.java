package com.janglejay.enums;

public enum ReturnValueTypeEnum {
    DO_RETURN(0),
    DO_NOTHING(1);
    int type;
    ReturnValueTypeEnum(int type) {
        this.type = type;
    }
    public int getType() {
        return this.type;
    }
    public ReturnValueTypeEnum getByType(int type) {
        for (ReturnValueTypeEnum value : values()) {
            if (value.getType() == type) return value;
        }
        return null;
    }
}
