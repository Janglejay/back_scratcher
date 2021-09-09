package com.janglejay.resolver;

import com.google.common.base.CaseFormat;
import com.janglejay.deconstruction.MockerDeconstruction;
import com.janglejay.enums.MockerTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;

@Slf4j
public class MockerResolver implements Resolver {

    @Override
    public MockerDeconstruction resolve(String line) throws Exception{
        log.info("resolver running ......");
//        String[] sides = line.split("=");
//        String left = sides[0];
//        String[] strings = left.split(" ");

        List<String> stringList = Arrays.stream(line.split(" ")).filter(
                x -> !x.trim().equals("")
        ).collect(Collectors.toList());

        if (stringList.isEmpty()) {
            log.info("stringList is Empty .......");
        }

        String startString = stringList.get(0);
        if (startString.startsWith("List")) {
            //mock 容器List
            //List<QueryHeadBankInfoVO> superBankList
            //List<QueryHeadBankInfoVo> superBankList = new ArrayList<>();
            //QueryHeadBankInfoVo queryHeadBankInfoVo = mock(QueryHeadBankInfoVo.class);
            //superBankList.add(queryHeadBankInfoVo);
            String className = stringList.get(0);
            String valName = stringList.get(1);
            String innerClassName = className.substring(className.indexOf("<") + 1, className.indexOf(">"));
            String innerValName = CaseFormat.UPPER_CAMEL.to(LOWER_CAMEL, innerClassName);
            return new MockerDeconstruction.Builder()
                    .setMockerType(MockerTypeEnum.LIST)
                    .setClassName(className)
                    .setValName(valName)
                    .setInnerClassName(innerClassName)
                    .setInnerValName(innerValName)
                    .build();
        }
        if (startString.startsWith("Optional")) {
            //mock 容器Optional
            //Optional<ClaimBankInfo> optional
            //ClaimBankInfo claimBankInfo = mock(ClaimBankInfo.class);
            //Optional<ClaimBankInfo> optional = Optional.of(claimBankInfo);
            String className = stringList.get(0);
            String valName = stringList.get(1);
            String innerClassName = className.substring(className.indexOf("<") + 1, className.indexOf(">"));
            String innerValName = CaseFormat.UPPER_CAMEL.to(LOWER_CAMEL, innerClassName);
            return new MockerDeconstruction.Builder()
                    .setMockerType(MockerTypeEnum.OPTIONAL)
                    .setClassName(className)
                    .setValName(valName)
                    .setInnerClassName(innerClassName)
                    .setInnerValName(innerValName)
                    .build();
        }

        if (startString.contains("<") && startString.contains(">")) {
            //mock 带泛型
            //OpResult<QueryBankTypeResult> queryBankTypeResultOpResult
            //QueryBankTypeResult queryBankTypeResult = mock(QueryBankTypeResult.class);
            //OpResult<QueryBankTypeResult> queryBankTypeResultOpResult = mock(OpResult.class);
            String className = stringList.get(0);
            String valName = stringList.get(1);
            String innerClassName = className.substring(className.indexOf("<") + 1, className.indexOf(">"));
            String innerValName = CaseFormat.UPPER_CAMEL.to(LOWER_CAMEL, innerClassName);
            return new MockerDeconstruction.Builder()
                    .setMockerType(MockerTypeEnum.GENERICS)
                    .setClassName(className)
                    .setValName(valName)
                    .setInnerClassName(innerClassName)
                    .setInnerValName(innerValName)
                    .build();
        }
        //mock 普通的
        //QueryBankTypeParam queryBankParam
        //QueryBankTypeParam queryBankParam = mock(QueryBankTypeParam.class);
        String className = stringList.get(0);
        String valName = stringList.get(1);
        return new MockerDeconstruction.Builder()
                .setMockerType(MockerTypeEnum.NORMAL)
                .setClassName(className)
                .setValName(valName)
                .build();
    }

}
