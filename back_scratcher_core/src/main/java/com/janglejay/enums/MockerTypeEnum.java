package com.janglejay.enums;

public enum MockerTypeEnum {
    LIST(0),
    OPTIONAL(1),
    GENERICS(2),
    NORMAL(3);
    private int type;

    MockerTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
    public MockerTypeEnum getByType(int type) {
        for (MockerTypeEnum e : values()) {
            if (e.getType() == type) return e;
        }
        return null;
    }
}
