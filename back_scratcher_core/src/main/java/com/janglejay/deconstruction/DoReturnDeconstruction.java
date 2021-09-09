package com.janglejay.deconstruction;


import com.janglejay.enums.ReturnValueTypeEnum;
import com.janglejay.enums.MethodTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DoReturnDeconstruction extends Deconstruction {
    private final MethodTypeEnum methodType;
    private final ReturnValueTypeEnum returnValueType;
    private final String className;
    private final String methodName;
    private final String returnValue;
    private final Integer argsNumber;

    private DoReturnDeconstruction(Builder builder) {
        this.methodType = builder.methodType;
        this.returnValueType = builder.returnValueType;
        this.className = builder.className;
        this.methodName = builder.methodName;
        this.returnValue = builder.returnValue;
        this.argsNumber = builder.argsNumber;
    }

    public static class Builder {
        private MethodTypeEnum methodType;
        private ReturnValueTypeEnum returnValueType;
        private String className;
        private String methodName;
        private String returnValue;
        private Integer argsNumber;

        public Builder() {

        }

        public Builder(MethodTypeEnum methodType, ReturnValueTypeEnum returnValueType) {
            this.methodType = methodType;
            this.returnValueType = returnValueType;
        }


        public Builder setMethodType(MethodTypeEnum methodType) {
            this.methodType = methodType;
            return this;
        }

        public Builder setReturnValueType(ReturnValueTypeEnum returnValueType) {
            this.returnValueType = returnValueType;
            return this;
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
            this.returnValue = returnValue;
            return this;
        }

        public Builder setArgs(Integer argsNumber) {
            this.argsNumber = argsNumber;
            return this;
        }


        public Builder buildRight(String right) {
            //不能把参数里面的 . 分割
            int dotIndex = right.indexOf(".");
            //参数里面可能也有 .
            if (dotIndex != -1 && dotIndex < right.indexOf("(")) {
                className = right.substring(0, dotIndex);
            } else {
                className = "spy";
                dotIndex = -1;
            }
            String rr = right.substring(dotIndex + 1);
            int leftBracketIndex = rr.indexOf("(");
            int rightBracketIndex = rr.lastIndexOf(")");
            String methodName = rr.substring(0, leftBracketIndex);
            List<String> args = Arrays.stream(rr.substring(leftBracketIndex + 1, rightBracketIndex).split(",")).filter(x -> !x.trim().equals("")).collect(Collectors.toList());
            int argsNumber = args.size();
            this.setClassName(className)
                    .setMethodName(methodName)
                    .setArgs(argsNumber);
            if (className.equals("spy")) {
                this.setMethodType(MethodTypeEnum.PRIVATE);
                return this;
            }
            if (Character.isUpperCase(className.charAt(0))) {
                //Static
//        CatMonitorUtils.addExeWithSubType(CatEventEnum.CLAIM_COMPANY_MODIFY_MATERIAL, ClaimAttrConstants.REPORT_CAT_SUCCESS + "");
//        doNothing().when(CatMonitorUtils.class, "addExeWithSubType", any(), any());
                this.setMethodType(MethodTypeEnum.STATIC);
                return this;
            }

            if (Character.isLowerCase(className.charAt(0))) {
                //Normal or Final
//        claimCheckService.checkOrderOwner(orderId, orderDetailId, userInfo);
//        doNothing().when(claimCheckService).checkOrderOwner(anyLong(), anyLong(), any());
                this.setMethodType(MethodTypeEnum.NORMAL);
                return this;
            }
            return this;
        }

        public DoReturnDeconstruction build() {
            return new DoReturnDeconstruction(this);
        }


    }


}
