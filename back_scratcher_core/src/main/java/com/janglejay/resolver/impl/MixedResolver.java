package com.janglejay.resolver.impl;

import com.janglejay.deconstruction.Deconstruction;
import com.janglejay.deconstruction.DoReturnDeconstruction;
import com.janglejay.deconstruction.MixedDeconstruction;
import com.janglejay.deconstruction.MockerDeconstruction;
import com.janglejay.enums.SentenceTypeEnum;
import com.janglejay.resolver.Resolver;

public class MixedResolver implements Resolver {
    @Override
    public Deconstruction resolve(String line) throws Exception {
        MixedDeconstruction mixedDeconstruction = new MixedDeconstruction();
        MockerDeconstruction mockerDeconstruction = null;
        DoReturnDeconstruction doReturnDeconstruction = null;
        String[] strings = line.split("=");
        if (strings.length == 1) {
            if (strings[0].indexOf("(") < strings[0].indexOf(")")) {
                doReturnDeconstruction = new DoReturnResolver().resolve(strings[0]);
                mixedDeconstruction.setSentenceTypeEnum(SentenceTypeEnum.DO_RETURN_ONLY);
            }else {
                mockerDeconstruction = new MockerResolver().resolve(strings[0]);
                mixedDeconstruction.setSentenceTypeEnum(SentenceTypeEnum.MOCK_ONLY);
            }
        }

        if (strings.length == 2) {
            mockerDeconstruction = new MockerResolver().resolve(strings[0]);
            doReturnDeconstruction = new DoReturnResolver().resolve(line);
            mixedDeconstruction.setSentenceTypeEnum(SentenceTypeEnum.MIXED);
        }

        mixedDeconstruction.setMockerDeconstruction(mockerDeconstruction);
        mixedDeconstruction.setDoReturnDeconstruction(doReturnDeconstruction);

        return mixedDeconstruction;
    }
}
