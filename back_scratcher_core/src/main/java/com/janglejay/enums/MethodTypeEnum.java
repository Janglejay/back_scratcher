package com.janglejay.enums;

public enum MethodTypeEnum {
    NORMAL(0),
    STATIC(1),
    PRIVATE(2);
    private int type;
    MethodTypeEnum(int type) {
        this.type = type;
    }
    public int getType() {
        return this.type;
    }
    public MethodTypeEnum getByType(int type) {
        for (MethodTypeEnum value : values()) {
            if (value.getType() == type) return value;
        }
        return null;
    }
}
