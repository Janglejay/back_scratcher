package com.janglejay.com;

import com.janglejay.deconstruction.Deconstruction;
import com.janglejay.deconstruction.MockerDeconstruction;
import com.janglejay.handler.MockerHandler;
import com.janglejay.resolver.impl.MockerResolver;
import com.janglejay.resolver.Resolver;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
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
            try {
                MockerDeconstruction mockerDeconstruction = (MockerDeconstruction) resolver.resolve(string);
                ret = MockerHandler.handle(mockerDeconstruction);
            }catch (Exception e) {
                log.error("{}", e);
                continue;

            }
            out.println();
            if (ret != null) {
                StringBuilder data = new StringBuilder();
                for (String s : ret) {
                    out.println(s);
                    data.append(s + "\n");
                }
                StringSelection stringSelection = new StringSelection(data.toString());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            }
            out.println();
            out.flush();
        }
    }
}
