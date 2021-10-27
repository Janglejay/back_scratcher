package com.janglejay;

import com.janglejay.deconstruction.MixedDeconstruction;
import com.janglejay.handler.MixedHandler;
import com.janglejay.resolver.impl.MixedResolver;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import static com.janglejay.utils.MyInputOutput.in;
import static com.janglejay.utils.MyInputOutput.out;
import static com.janglejay.utils.ImageUtil.*;
@Slf4j
public class BackScratcher {
    public static void main(String[] args) throws Exception {
        log.info("START BACK SCRATCHER");
        out.println(LOGO);
        out.flush();
        while (true) {
            log.info("enter your code");
            String line = in.nextLine();
            if (line.trim().equals("exit")) {
                break;
            }
            if (line.trim().startsWith("//")) {
                line = line.trim().substring(2);
            }
            List<String> ret = null;
            try {
                MixedDeconstruction mixedDeconstruction = (MixedDeconstruction) new MixedResolver().resolve(line);
                ret = MixedHandler.handle(mixedDeconstruction);
            } catch (Exception e) {
                log.error("{}", e);
                continue;
            }
            out.println();
            if (ret != null) {
                StringBuilder data = new StringBuilder();
                if (args[0].equals("y"))
                    data.append("//\t" + line + "\n");
                for (String s : ret) {
                    data.append(s + "\n");
                }
                out.println(data);
                StringSelection stringSelection = new StringSelection(data.toString());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            }
            out.println();
            out.flush();
        }
        out.println(GOOD_BYE);
        out.flush();
        out.close();
    }
}
