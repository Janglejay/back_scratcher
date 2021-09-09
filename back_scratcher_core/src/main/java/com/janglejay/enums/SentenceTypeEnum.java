package com.janglejay.enums;

public enum SentenceTypeEnum {
    MIXED(0),
    MOCK_ONLY(1),
    DO_RETURN_ONLY(2);
    private int type;

    SentenceTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SentenceTypeEnum getByType(int type) {
        for (SentenceTypeEnum value : values()) {
            if (value.getType() == type) return value;
        }
        return null;
    }
}
