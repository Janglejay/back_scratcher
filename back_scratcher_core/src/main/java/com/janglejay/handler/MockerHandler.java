package com.janglejay.handler;

import com.janglejay.deconstruction.MockerDeconstruction;
import com.janglejay.enums.MockerTypeEnum;
import com.janglejay.utils.BasicTypeTable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
public class MockerHandler {
    public static List<String> doMocker(MockerDeconstruction mockerDeconstruction) {
        log.info("doMocker .......");
        MockerTypeEnum mockerTypeEnum = mockerDeconstruction.getMockerType();

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
            return Collections.singletonList(mockNormal(mockerDeconstruction));
        }
        return null;
    }


    private static String build(String className, String valName, String classType) {
        return className + " " + valName + " = " + "mock(" + classType + ".class);";
    }

    private static String build(Pair<String, String> basicType, String valName) {
        return basicType.getKey() + " " + valName + " = " + basicType.getValue() + ");";
    }

    private static Pair<String, String> filterBasicType(String className) {
        if (BasicTypeTable.TYPE_TABLE.containsKey(className)) {
            Pair<String, String> pair = new ImmutablePair<>(className, BasicTypeTable.TYPE_TABLE.get(className));
        }
        return null;
    }

    private static String mockNormal(MockerDeconstruction mockerDeconstruction) {
        //mock 普通的
        //QueryBankTypeParam queryBankParam
        //QueryBankTypeParam queryBankParam = mock(QueryBankTypeParam.class);
        String className = mockerDeconstruction.getClassName();
        Pair<String, String> pair = filterBasicType(className);
        String valName = mockerDeconstruction.getValName();
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
        String line1 = mockNormal(
                new MockerDeconstruction.Builder()
                        .setClassName(innerClassName)
                        .setValName(innerValName)
                        .build()
        );
        String line2 = build(className, valName, classType);
        return Arrays.asList(line1, line2);
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
        String line2 = mockNormal(
                new MockerDeconstruction.Builder()
                        .setMockerType(MockerTypeEnum.NORMAL)
                        .setClassName(innerClassName)
                        .setValName(innerValName)
                        .build()
        );
        String line3 = valName + ".add(" + innerValName + ");";
        return Arrays.asList(line1, line2, line3);
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
        String line1 = mockNormal(
                new MockerDeconstruction.Builder()
                        .setMockerType(MockerTypeEnum.NORMAL)
                        .setClassName(innerClassName)
                        .setValName(innerValName)
                        .build()
        );
        String line2 = className + " " + valName + " = " + "Optional.of(" + innerValName + ");";
        return Arrays.asList(line1, line2);
    }


}
