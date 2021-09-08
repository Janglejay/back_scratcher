package com.janglejay.handler;

import com.janglejay.deconstruction.DoReturnDeconstruction;
import com.janglejay.enums.MethodTypeEnum;
import com.janglejay.enums.ReturnValueTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class DoReturnHandler {

    public static List<String> doReturn(DoReturnDeconstruction doReturnDeconstruction) {

        if (doReturnDeconstruction.getMethodType().equals(MethodTypeEnum.STATIC)) {
            return buildStatic(doReturnDeconstruction);
        }

        if (doReturnDeconstruction.getMethodType().equals(MethodTypeEnum.NORMAL)) {
            return buildNormal(doReturnDeconstruction);
        }
        return null;
    }

    private static List<String> buildStatic(DoReturnDeconstruction doReturnDeconstruction) {
        String className = doReturnDeconstruction.getClassName() + ".class";
        String methodName = doReturnDeconstruction.getMethodName();
        String returnValue = doReturnDeconstruction.getReturnValueType()
                .equals(ReturnValueTypeEnum.DORETURN)
                ? "doReturn(" + doReturnDeconstruction.getReturnValue() + ")"
                : "doNothing()";
        List<String> anyList = new ArrayList<>();
        for (int i = 0; i < doReturnDeconstruction.getArgsNumber(); i++) {
            anyList.add("any()");
        }
        String args = String.join(", ", anyList);
        String line = returnValue + "." + "when(" + className + ", " + "\"" + methodName +  "\", " + args + ");";
        return List.of(line);
    }

    private static List<String> buildNormal(DoReturnDeconstruction doReturnDeconstruction) {
        String className = doReturnDeconstruction.getClassName();
        String methodName = doReturnDeconstruction.getMethodName();
        String returnValue = doReturnDeconstruction.getReturnValueType()
                .equals(ReturnValueTypeEnum.DORETURN)
                ? "doReturn(" + doReturnDeconstruction.getReturnValue() + ")"
                : "doNothing()";
        List<String> anyList = new ArrayList<>();
        for (int i = 0; i < doReturnDeconstruction.getArgsNumber(); i++) {
            anyList.add("any()");
        }
        String args = String.join(", ", anyList);
        String line = returnValue + "." + "when(" + className + ")." + methodName + "(" + args + ");";
        return List.of(line);
    }

}
