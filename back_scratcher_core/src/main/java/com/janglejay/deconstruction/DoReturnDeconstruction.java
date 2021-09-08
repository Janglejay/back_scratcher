package com.janglejay.deconstruction;


import com.janglejay.enums.ReturnValueTypeEnum;
import com.janglejay.enums.MethodTypeEnum;

public class DoReturnDeconstruction extends Deconstruction{
    private final MethodTypeEnum methodType;
    private final ReturnValueTypeEnum returnValueType;
    private final String className;
    private final String methodName;
    private final String returnValue;
    private final String args;

    private DoReturnDeconstruction(Builder builder) {
        this.methodType = builder.methodType;
        this.returnValueType = builder.returnValueType;
        this.className = builder.className;
        this.methodName = builder.methodName;
        this.returnValue = builder.returnValue;
        this.args = builder.args;
    }

    public static class Builder {
        private MethodTypeEnum methodType;
        private ReturnValueTypeEnum returnValueType;
        private String className;
        private String methodName;
        private String returnValue;
        private String args;

        public Builder(MethodTypeEnum methodType, ReturnValueTypeEnum returnValueType) {
            this.methodType = methodType;
            this.returnValueType = returnValueType;
        }


        public Builder setClassName(String className) {
            this.className = className;
            return this;
        }

        public Builder setMethodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public Builder setReturnValue(String returnValue) {
            this.returnValueType = returnValueType;
            return this;
        }

        public Builder setArgs(String args) {
            this.args = args;
            return this;
        }

        public DoReturnDeconstruction build() {
            return new DoReturnDeconstruction(this);
        }

    }


}
