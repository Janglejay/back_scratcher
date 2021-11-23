package com.janglejay.handler;

import com.google.common.collect.Lists;
import com.janglejay.constant.StringConstants;
import com.janglejay.deconstruction.MockerDeconstruction;
import com.janglejay.enums.MockerTypeEnum;
import com.janglejay.resolver.impl.MockerResolver;
import com.janglejay.utils.BasicTypeTable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
public class MockerHandler {
    public static List<String> handle(MockerDeconstruction mockerDeconstruction) {
        MockerTypeEnum mockerTypeEnum = mockerDeconstruction.getMockerType();

        if (Objects.equals(mockerTypeEnum, MockerTypeEnum.PARAMETER)) {
            try {
                return mockParameter(mockerDeconstruction);
            } catch (Exception e) {
                return new ArrayList<>();
            }
        }
        if (Objects.equals(mockerTypeEnum, MockerTypeEnum.LIST)) {
            return mockList(mockerDeconstruction);
        }
        if (Objects.equals(mockerTypeEnum, MockerTypeEnum.OPTIONAL)) {
            return mockOptional(mockerDeconstruction);
        }

        if (Objects.equals(mockerTypeEnum, MockerTypeEnum.GENERICS)) {
            return mockGenerics(mockerDeconstruction);
        }

        if (Objects.equals(mockerTypeEnum, MockerTypeEnum.NORMAL)) {
            return Lists.newArrayList(mockNormal(mockerDeconstruction));
        }
        return null;
    }


    private static String build(String className, String valName, String classType) {
        return className + StringConstants.SPACE + valName + " = " + "mock(" + classType + ".class);";
    }

    private static String build(Pair<String, String> basicType, String valName) {
        return basicType.getKey() + StringConstants.SPACE + valName + " = " + basicType.getValue() + ";";
    }

    private static Pair<String, String> filterBasicType(String className) {
        if (BasicTypeTable.TYPE_TABLE.containsKey(className)) {
            return new ImmutablePair<>(className, BasicTypeTable.TYPE_TABLE.get(className));
        }
        return null;
    }

    private static List<String> mockParameter(MockerDeconstruction mockerDeconstruction) throws Exception {
        List<String> lines = new ArrayList<>();
        String[] classNames = mockerDeconstruction.getClassName().split(StringConstants.COMMA);
        String[] valNames = mockerDeconstruction.getValName().split(StringConstants.COMMA);
        MockerResolver mockerResolver = new MockerResolver();
        for (int i = 0; i < classNames.length; i++) {
            lines.addAll(
                    Objects.requireNonNull(MockerHandler.handle(
                            mockerResolver.resolve(classNames[i] + StringConstants.SPACE + valNames[i])
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
        if (Objects.nonNull(pair)) {
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
            deconstruction = resolver.resolve(innerClassName + StringConstants.SPACE + innerValName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> ret = new ArrayList<>();
        ret.addAll(MockerHandler.handle(deconstruction));
        String line2 = build(className, valName, classType);
        ret.add(line2);
        return ret;
    }

    private static List<String> mockList(MockerDeconstruction mockerDeconstruction) {
        //mock 容器List
        //List<QueryHeadBankInfoVO> superBankList
        //List<QueryHeadBankInfoVo> superBankList = new ArrayList<>();
        //QueryHeadBankInfoVo queryHeadBankInfoVo = mock(QueryHeadBankInfoVo.class);
        //superBankList.add(queryHeadBankInfoVo);
        String className = mockerDeconstruction.getClassName();
        String valName = mockerDeconstruction.getValName();
        String line1 = className + StringConstants.SPACE + valName + " = " + "new ArrayList<>();";
        String innerClassName = mockerDeconstruction.getInnerClassName();
        String innerValName = mockerDeconstruction.getInnerValName();

        MockerResolver resolver = new MockerResolver();
        MockerDeconstruction deconstruction = null;
        List<String> ret = new ArrayList<>();
        try {
            deconstruction = resolver.resolve(innerClassName + StringConstants.SPACE + innerValName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ret.add(line1);
        ret.addAll(MockerHandler.handle(deconstruction));

        String line3 = valName + ".add(" + innerValName + ");";
        ret.add(line3);
        return ret;
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
        List<String> ret = new ArrayList<>();
        try {
            deconstruction = resolver.resolve(innerClassName + StringConstants.SPACE + innerValName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ret.addAll(MockerHandler.handle(deconstruction));
        String line2 = className + StringConstants.SPACE + valName + " = " + "Optional.of(" + innerValName + ");";
        ret.add(line2);
        return ret;
    }


}
