package com.janglejay.handler;

import com.janglejay.deconstruction.MockerDeconstruction;
import com.janglejay.enums.MockerTypeEnum;
import com.janglejay.resolver.impl.MockerResolver;
import com.janglejay.utils.BasicTypeTable;
import com.janglejay.utils.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;


@Slf4j
public class MockerHandler {
    public static List<String> handle(MockerDeconstruction mockerDeconstruction) {
        MockerTypeEnum mockerTypeEnum = mockerDeconstruction.getMockerType();

        if (mockerTypeEnum.equals(MockerTypeEnum.PARAMETER)) {
            try {
                return mockParameter(mockerDeconstruction);
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
        if (mockerTypeEnum.equals(MockerTypeEnum.LIST)) {
            return mockList(mockerDeconstruction);
        }
        if (mockerTypeEnum.equals(MockerTypeEnum.OPTIONAL)) {
            return mockOptional(mockerDeconstruction);
        }

        if (mockerTypeEnum.equals(MockerTypeEnum.GENERICS)) {
            return mockGenerics(mockerDeconstruction);
        }

        if (mockerTypeEnum.equals(MockerTypeEnum.NORMAL)) {
            return ListUtils.of(mockNormal(mockerDeconstruction));
        }
        return null;
    }


    private static String build(String className, String valName, String classType) {
        return className + " " + valName + " = " + "mock(" + classType + ".class);";
    }

    private static String build(Pair<String, String> basicType, String valName) {
        return basicType.getKey() + " " + valName + " = " + basicType.getValue() + ";";
    }

    private static Pair<String, String> filterBasicType(String className) {
        if (BasicTypeTable.TYPE_TABLE.containsKey(className)) {
            return new ImmutablePair<>(className, BasicTypeTable.TYPE_TABLE.get(className));
        }
        return null;
    }

    private static List<String> mockParameter(MockerDeconstruction mockerDeconstruction) throws Exception {
        List<String> lines = new ArrayList<>();
        String[] classNames = mockerDeconstruction.getClassName().split(",");
        String[] valNames = mockerDeconstruction.getValName().split(",");
        MockerResolver mockerResolver = new MockerResolver();
        for (int i = 0; i < classNames.length; i++) {
            lines.addAll(
                    Objects.requireNonNull(MockerHandler.handle(
                            mockerResolver.resolve(classNames[i] + " " + valNames[i])
                    ))
            );
        }
        return lines;
    }

    private static String mockNormal(MockerDeconstruction mockerDeconstruction) {
        //mock 普通的
        //QueryBankTypeParam queryBankParam
        //QueryBankTypeParam queryBankParam = mock(QueryBankTypeParam.class);
        String className = mockerDeconstruction.getClassName();
        String valName = mockerDeconstruction.getValName();
        Pair<String, String> pair = filterBasicType(className);
        if (pair != null) {
            return build(pair, valName);
        }
        return build(className, valName, className);
    }

    private static List<String> mockGenerics(MockerDeconstruction mockerDeconstruction) {
        //mock 带泛型
        //OpResult<QueryBankTypeResult> queryBankTypeResultOpResult
        //QueryBankTypeResult queryBankTypeResult = mock(QueryBankTypeResult.class);
        //OpResult<QueryBankTypeResult> queryBankTypeResultOpResult = mock(OpResult.class);
        String className = mockerDeconstruction.getClassName();
        String valName = mockerDeconstruction.getValName();
        String classType = className.substring(0, className.indexOf("<"));
        String innerClassName = mockerDeconstruction.getInnerClassName();
        String innerValName = mockerDeconstruction.getInnerValName();


        MockerResolver resolver = new MockerResolver();
        MockerDeconstruction deconstruction = null;
        try {
            deconstruction = resolver.resolve(innerClassName + " " + innerValName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line1 = MockerHandler.handle(deconstruction).get(0);

        String line2 = build(className, valName, classType);
        return ListUtils.of(line1, line2);
    }

    private static List<String> mockList(MockerDeconstruction mockerDeconstruction) {
        //mock 容器List
        //List<QueryHeadBankInfoVO> superBankList
        //List<QueryHeadBankInfoVo> superBankList = new ArrayList<>();
        //QueryHeadBankInfoVo queryHeadBankInfoVo = mock(QueryHeadBankInfoVo.class);
        //superBankList.add(queryHeadBankInfoVo);
        String className = mockerDeconstruction.getClassName();
        String valName = mockerDeconstruction.getValName();
        String line1 = className + " " + valName + " = " + "new ArrayList<>();";
        String innerClassName = mockerDeconstruction.getInnerClassName();
        String innerValName = mockerDeconstruction.getInnerValName();

        MockerResolver resolver = new MockerResolver();
        MockerDeconstruction deconstruction = null;
        try {
            deconstruction = resolver.resolve(innerClassName + " " + innerValName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line2 = MockerHandler.handle(deconstruction).get(0);

        String line3 = valName + ".add(" + innerValName + ");";
        return ListUtils.of(line1, line2, line3);
    }

    private static List<String> mockOptional(MockerDeconstruction mockerDeconstruction) {
        //mock 容器Optional
        //Optional<ClaimBankInfo> optional
        //ClaimBankInfo claimBankInfo = mock(ClaimBankInfo.class);
        //Optional<ClaimBankInfo> optional = Optional.of(claimBankInfo);
        String className = mockerDeconstruction.getClassName();
        String valName = mockerDeconstruction.getValName();
        String innerClassName = mockerDeconstruction.getInnerClassName();
        String innerValName = mockerDeconstruction.getInnerValName();


        MockerResolver resolver = new MockerResolver();
        MockerDeconstruction deconstruction = null;
        try {
            deconstruction = resolver.resolve(innerClassName + " " + innerValName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line1 = MockerHandler.handle(deconstruction).get(0);
        String line2 = className + " " + valName + " = " + "Optional.of(" + innerValName + ");";
        return ListUtils.of(line1, line2);
    }


}
