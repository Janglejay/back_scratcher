package com.janglejay;

import com.janglejay.deconstruction.DoReturnDeconstruction;
import com.janglejay.deconstruction.MockerDeconstruction;
import com.janglejay.handler.DoReturnHandler;
import com.janglejay.handler.MockerHandler;
import com.janglejay.resolver.DoReturnResolver;
import com.janglejay.resolver.MockerResolver;
import com.janglejay.resolver.Resolver;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.janglejay.utils.MyInputOutput.in;
import static com.janglejay.utils.MyInputOutput.out;

@Slf4j
public class BackScratcherMock {
    public static void main(String[] args) {
        while (true) {
            log.info("==========START MOCK==========");
            List<String> ret = null;
            Resolver resolver;
            //mock
            String string = in.nextLine();
            resolver = new MockerResolver();
            MockerDeconstruction mockerDeconstruction = (MockerDeconstruction) resolver.resolve(string);
            ret = MockerHandler.doMocker(mockerDeconstruction);
            out.println();
            if (ret != null) {
                for (String s : ret) {
                    out.println(s);
                }
            }
            out.println();
            out.flush();
        }
    }
}
