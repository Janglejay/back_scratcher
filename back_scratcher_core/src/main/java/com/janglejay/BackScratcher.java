package com.janglejay;

import com.janglejay.constant.StringConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

import static com.janglejay.utils.ImageUtil.GOOD_BYE;
import static com.janglejay.utils.ImageUtil.LOGO;
import static com.janglejay.utils.MyInputOutput.in;
import static com.janglejay.utils.MyInputOutput.out;

@Slf4j
public class BackScratcher {
    public static void main(String[] args) {
        log.info("START BACK SCRATCHER");
        out.println(LOGO);
        out.flush();
        final boolean withComments = StringUtils.equalsIgnoreCase(args[0], StringConstants.WITH_COMMENTS);

        final boolean isMultiInput = StringUtils.equalsIgnoreCase(args[1], StringConstants.MULTI_INPUT);

        boolean isRun = true;
        while (isRun) {
            StringBuilder data = new StringBuilder();
            log.info("enter your code");
            try {
                String line = in.nextCodeLine();
                while (StringUtils.isBlank(line)) line = in.nextCodeLine();
                // multi 多行读入
                if (isMultiInput) {
                    while (!StringUtils.equalsIgnoreCase(line, StringConstants.MULTI_INPUT_END)) {
                        if (StringUtils.equalsIgnoreCase(line, StringConstants.EXIT)) {
                            isRun = false;
                            break;
                        }
                        DoProcess.doOneLine(data, withComments, line);
                        line = in.nextCodeLine();
                        while (StringUtils.isBlank(line)) line = in.nextCodeLine();
                    }
                } else {
                    if (StringUtils.equalsIgnoreCase(line, StringConstants.EXIT)) {
                        break;
                    }
                    //oneLine 单行读入
                    DoProcess.doOneLine(data, withComments, line);
                }
                out.println(data);
                StringSelection stringSelection = new StringSelection(data.toString());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            } catch (Exception e) {
                log.error("{}", e);
                continue;
            }
            out.flush();
        }
        out.println(GOOD_BYE);
        out.flush();
        out.close();
    }
}
