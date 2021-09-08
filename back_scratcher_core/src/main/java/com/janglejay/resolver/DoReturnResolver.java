package com.janglejay.resolver;

import com.janglejay.deconstruction.Deconstruction;
import com.janglejay.deconstruction.DoReturnDeconstruction;
import com.janglejay.enums.MethodTypeEnum;
import com.janglejay.enums.ReturnValueTypeEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DoReturnResolver implements Resolver {
    @Override
    public DoReturnDeconstruction resolve(String line) {
        String[] sides = line.split("=");
        String left = null;
        String right = null;
        ReturnValueTypeEnum returnValueType = resolveReturnValueType(sides);
        if (returnValueType.equals(ReturnValueTypeEnum.DONOTHING)) {
            //doNothing
            right = sides[0].trim();

            return new DoReturnDeconstruction.Builder()
                    .setReturnValueType(ReturnValueTypeEnum.DONOTHING)
                    .buildRight(right)
                    .build();
        }

        if (returnValueType.equals(ReturnValueTypeEnum.DORETURN)) {
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
                    .setReturnValueType(ReturnValueTypeEnum.DORETURN)
                    .setReturnValue(list.get(1))
                    .buildRight(right)
                    .build();
        }
        return null;
    }

    private ReturnValueTypeEnum resolveReturnValueType(String[] sides) {
        if (sides.length == 1) return ReturnValueTypeEnum.DONOTHING;
        return ReturnValueTypeEnum.DORETURN;
    }


}
