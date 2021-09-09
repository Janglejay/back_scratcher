package com.janglejay.handler;

import com.janglejay.deconstruction.DoReturnDeconstruction;
import com.janglejay.enums.MethodTypeEnum;
import com.janglejay.enums.ReturnValueTypeEnum;
import com.janglejay.utils.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DoReturnHandler {

    public static List<String> handle(DoReturnDeconstruction doReturnDeconstruction) {
        if (doReturnDeconstruction.getMethodType().equals(MethodTypeEnum.PRIVATE)) {
            return buildPrivate(doReturnDeconstruction);
        }

        if (doReturnDeconstruction.getMethodType().equals(MethodTypeEnum.STATIC)) {
            return buildStatic(doReturnDeconstruction);
        }

        if (doReturnDeconstruction.getMethodType().equals(MethodTypeEnum.NORMAL)) {
            return buildNormal(doReturnDeconstruction);
        }
        return null;
    }
    private static List<String> buildPrivate(DoReturnDeconstruction doReturnDeconstruction) {
        String className = doReturnDeconstruction.getClassName();
        String methodName = doReturnDeconstruction.getMethodName();
        String returnValue = doReturnDeconstruction.getReturnValueType()
                .equals(ReturnValueTypeEnum.DO_RETURN)
                ? "doReturn(" + doReturnDeconstruction.getReturnValue() + ")"
                : "doNothing()";
        List<String> anyList = new ArrayList<>();
        for (int i = 0; i < doReturnDeconstruction.getArgsNumber(); i++) {
            anyList.add("any()");
        }
        String args = anyList.isEmpty() ? null : String.join(", ", anyList);
        String line;
        if (args != null) {
            line = returnValue + "." + "when(" + className + ", " + "\"" + methodName +  "\", " + args + ");";
        }else {
            line = returnValue + "." + "when(" + className + ", " + "\"" + methodName +  "\"" + ");";
        }
        return ListUtils.of(line);
    }

    private static List<String> buildStatic(DoReturnDeconstruction doReturnDeconstruction) {
        String className = doReturnDeconstruction.getClassName() + ".class";
        String methodName = doReturnDeconstruction.getMethodName();
        String returnValue = doReturnDeconstruction.getReturnValueType()
                .equals(ReturnValueTypeEnum.DO_RETURN)
                ? "doReturn(" + doReturnDeconstruction.getReturnValue() + ")"
                : "doNothing()";
        List<String> anyList = new ArrayList<>();
        for (int i = 0; i < doReturnDeconstruction.getArgsNumber(); i++) {
            anyList.add("any()");
        }
        String args = anyList.isEmpty() ? null : String.join(", ", anyList);
        String line;
        if (args != null) {
             line = returnValue + "." + "when(" + className + ", " + "\"" + methodName +  "\", " + args + ");";
        }else {
             line = returnValue + "." + "when(" + className + ", " + "\"" + methodName +  "\"" + ");";
        }
        return ListUtils.of(line);
    }

    private static List<String> buildNormal(DoReturnDeconstruction doReturnDeconstruction) {
        String className = doReturnDeconstruction.getClassName();
        String methodName = doReturnDeconstruction.getMethodName();
        String returnValue = doReturnDeconstruction.getReturnValueType()
                .equals(ReturnValueTypeEnum.DO_RETURN)
                ? "doReturn(" + doReturnDeconstruction.getReturnValue() + ")"
                : "doNothing()";
        List<String> anyList = new ArrayList<>();
        for (int i = 0; i < doReturnDeconstruction.getArgsNumber(); i++) {
            anyList.add("any()");
        }
        String args = String.join(", ", anyList);
        String line = returnValue + "." + "when(" + className + ")." + methodName + "(" + args + ");";
        return ListUtils.of(line);
    }



}
