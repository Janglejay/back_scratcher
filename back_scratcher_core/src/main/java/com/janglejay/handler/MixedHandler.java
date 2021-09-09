package com.janglejay.handler;

import com.janglejay.deconstruction.MixedDeconstruction;
import com.janglejay.enums.SentenceTypeEnum;

import java.util.List;

public class MixedHandler {

    public static List<String> handle(MixedDeconstruction mixedDeconstruction) {
        if (mixedDeconstruction.getSentenceTypeEnum().equals(SentenceTypeEnum.MIXED)) {
            return buildMixed(mixedDeconstruction);
        }

        if (mixedDeconstruction.getSentenceTypeEnum().equals(SentenceTypeEnum.MOCK_ONLY)) {
            return buildMockOnly(mixedDeconstruction);
        }

        if (mixedDeconstruction.getSentenceTypeEnum().equals(SentenceTypeEnum.DO_RETURN_ONLY)) {
            return buildDoReturnOnly(mixedDeconstruction);
        }
        return null;
    }

    private static List<String> buildMixed(MixedDeconstruction mixedDeconstruction) {
        List<String> mockLine = MockerHandler.handle(mixedDeconstruction.getMockerDeconstruction());
        List<String> doReturnLine = DoReturnHandler.handle(mixedDeconstruction.getDoReturnDeconstruction());
        mockLine.addAll(doReturnLine);
        return mockLine;
    }

    private static List<String> buildMockOnly(MixedDeconstruction mixedDeconstruction) {
        return MockerHandler.handle(mixedDeconstruction.getMockerDeconstruction());
    }

    private static List<String> buildDoReturnOnly(MixedDeconstruction mixedDeconstruction) {
        return DoReturnHandler.handle(mixedDeconstruction.getDoReturnDeconstruction());
    }
}
