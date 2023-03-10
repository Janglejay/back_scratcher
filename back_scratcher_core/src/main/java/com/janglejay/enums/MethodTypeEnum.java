package com.janglejay.enums;

public enum MethodTypeEnum {
    /**
     * 普通类型
     */
    NORMAL(0),
    /**
     * 静态方法
     */
    STATIC(1),
    /**
     * 私有方法
     */
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
