package com.janglejay;

import com.janglejay.deconstruction.Deconstruction;
import com.janglejay.deconstruction.DoReturnDeconstruction;
import com.janglejay.deconstruction.MixedDeconstruction;
import com.janglejay.deconstruction.MockerDeconstruction;
import com.janglejay.handler.DoReturnHandler;
import com.janglejay.handler.MixedHandler;
import com.janglejay.handler.MockerHandler;
import com.janglejay.resolver.impl.DoReturnResolver;
import com.janglejay.resolver.impl.MixedResolver;
import com.janglejay.resolver.impl.MockerResolver;
import com.janglejay.resolver.Resolver;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import static com.janglejay.utils.MyInputOutput.in;
import static com.janglejay.utils.MyInputOutput.out;

@Slf4j
public class BackScratcher {
    public static void main(String[] args) throws Exception {
        log.info("START BACK SCRATCHER");
        out.println("\n" +
                "██████╗  █████╗  ██████╗██╗  ██╗    ███████╗ ██████╗██████╗  █████╗ ████████╗ ██████╗██╗  ██╗███████╗██████╗ \n" +
                "██╔══██╗██╔══██╗██╔════╝██║ ██╔╝    ██╔════╝██╔════╝██╔══██╗██╔══██╗╚══██╔══╝██╔════╝██║  ██║██╔════╝██╔══██╗\n" +
                "██████╔╝███████║██║     █████╔╝     ███████╗██║     ██████╔╝███████║   ██║   ██║     ███████║█████╗  ██████╔╝\n" +
                "██╔══██╗██╔══██║██║     ██╔═██╗     ╚════██║██║     ██╔══██╗██╔══██║   ██║   ██║     ██╔══██║██╔══╝  ██╔══██╗\n" +
                "██████╔╝██║  ██║╚██████╗██║  ██╗    ███████║╚██████╗██║  ██║██║  ██║   ██║   ╚██████╗██║  ██║███████╗██║  ██║\n" +
                "╚═════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝    ╚══════╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝    ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝\n" +
                "                                                                                                             \n");
        out.flush();
        while (true) {
//            out.println("\n" +
//                    "           __                                             __   \n" +
//                    " ___ ___  / /____ ____  __ _____  __ ______  _______  ___/ /__ \n" +
//                    "/ -_) _ \\/ __/ -_) __/ / // / _ \\/ // / __/ / __/ _ \\/ _  / -_)\n" +
//                    "\\__/_//_/\\__/\\__/_/    \\_, /\\___/\\_,_/_/    \\__/\\___/\\_,_/\\__/ \n" +
//                    "                      /___/                                    \n");
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
        out.println("\n" +
                " ██████╗  ██████╗  ██████╗ ██████╗ ██████╗ ██╗   ██╗███████╗\n" +
                "██╔════╝ ██╔═══██╗██╔═══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝██╔════╝\n" +
                "██║  ███╗██║   ██║██║   ██║██║  ██║██████╔╝ ╚████╔╝ █████╗  \n" +
                "██║   ██║██║   ██║██║   ██║██║  ██║██╔══██╗  ╚██╔╝  ██╔══╝  \n" +
                "╚██████╔╝╚██████╔╝╚██████╔╝██████╔╝██████╔╝   ██║   ███████╗\n" +
                " ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝    ╚═╝   ╚══════╝\n" +
                "                                                            \n");
        out.flush();
        out.close();
    }
}
