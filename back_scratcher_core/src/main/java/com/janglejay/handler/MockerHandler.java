package com.janglejay.handler;

import com.google.common.base.CaseFormat;
import com.janglejay.deconstruction.MockerDeconstruction;
import com.janglejay.enums.MockerTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;

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
            return List.of(mockGenerics(mockerDeconstruction));
        }
        if (mockerTypeEnum.equals(MockerTypeEnum.NORMAL)) {
            return List.of(mockNormal(mockerDeconstruction));
        }
        return null;
    }


    private static String build(String className, String valName, String classType) {
        return className + " " + valName + " = " + "mock(" + classType + ".class);";
    }

    private static String mockNormal(MockerDeconstruction mockerDeconstruction) {
        //mock 普通的
        //QueryBankTypeParam queryBankParam
        //QueryBankTypeParam queryBankParam = mock(QueryBankTypeParam.class);
        String className = mockerDeconstruction.getClassName();
        String valName = mockerDeconstruction.getValName();
        return build(className, valName, className);
    }

    private static String mockGenerics(MockerDeconstruction mockerDeconstruction) {
        //mock 带泛型
        //OpResult<QueryBankTypeResult> queryBankTypeResultOpResult
        //OpResult<QueryBankTypeResult> queryBankTypeResultOpResult = mock(OpResult.class);
        String className = mockerDeconstruction.getClassName();
        String valName = mockerDeconstruction.getValName();
        String classType = className.substring(0, className.indexOf("<") + 1);
        return build(className, valName, classType);
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
        return List.of(line1, line2, line3);
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
        return List.of(line1, line2);
    }
}
