package com.janglejay.resolver.impl;

import com.janglejay.deconstruction.DoReturnDeconstruction;
import com.janglejay.enums.ReturnValueTypeEnum;
import com.janglejay.resolver.Resolver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DoReturnResolver implements Resolver {
    @Override
    public DoReturnDeconstruction resolve(String line) throws Exception{
        String[] sides = line.split("=");
        String left = null;
        String right = null;
        ReturnValueTypeEnum returnValueType = resolveReturnValueType(sides);
        if (returnValueType.equals(ReturnValueTypeEnum.DO_NOTHING)) {
            //doNothing
            right = sides[0].trim();

            return new DoReturnDeconstruction.Builder()
                    .setReturnValueType(ReturnValueTypeEnum.DO_NOTHING)
                    .buildRight(right)
                    .build();
        }

        if (returnValueType.equals(ReturnValueTypeEnum.DO_RETURN)) {
            //doReturn

//        QueryBankTypeResultOpResult queryBankTypeResultOpResult = queryBankDelegate.getCardBinInfoByCardNo(queryBankParam)
//        doReturn(queryBankTypeResultOpResult).when(queryBankDelegate).getCardBinInfoByCardNo(queryBankParam);

//        String userSourceStr = CookieUtils.getCookieValue(request, USER_SOURCE);
//        doReturn("dx").when(CookieUtils.class, "getCookieValue", any(), any());
            left = sides[0].trim();
            right = sides[1].trim();
            List<String> list = Arrays.stream(left.split(" ")).filter(
                    x -> !x.trim().equals("")
            ).collect(Collectors.toList());


            return new DoReturnDeconstruction.Builder()
                    .setReturnValueType(ReturnValueTypeEnum.DO_RETURN)
                    .setReturnValue(list.get(list.size() - 1))
                    .buildRight(right)
                    .build();
        }
        return null;
    }

    private ReturnValueTypeEnum resolveReturnValueType(String[] sides) {
        if (sides.length == 1) return ReturnValueTypeEnum.DO_NOTHING;
        return ReturnValueTypeEnum.DO_RETURN;
    }


}
