package com.janglejay;

import com.janglejay.constant.StringConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

import static com.janglejay.utils.ImageUtil.GOOD_BYE;
import static com.janglejay.utils.ImageUtil.LOGO;
import static com.janglejay.utils.MyInputOutput.in;
import static com.janglejay.utils.MyInputOutput.out;

@Slf4j
public class BackScratcher {
    static boolean withComments = false;
    static boolean isMultiInput = false;

    public static void main(String[] args) {
        log.info("START BACK SCRATCHER");
        out.println(LOGO);
        out.flush();

        if (args.length > NumberUtils.INTEGER_ZERO)
            withComments = StringUtils.equalsIgnoreCase(args[0], StringConstants.WITH_COMMENTS);

        if (args.length > NumberUtils.INTEGER_ONE)
            isMultiInput = StringUtils.equalsIgnoreCase(args[1], StringConstants.MULTI_INPUT);

        boolean isRun = true;
        while (isRun) {
            StringBuilder data = new StringBuilder();
            log.info("enter your code");
            try {

                String line = getLine();
                // multi 多行读入
                if (isMultiInput) {
                    while (!StringUtils.equalsIgnoreCase(line, StringConstants.MULTI_INPUT_END) && isMultiInput) {
                        try {
                            if (StringUtils.equalsIgnoreCase(line, StringConstants.EXIT)) {
                                isRun = false;
                                break;
                            }

                            if (checkOption(line)) break;

                            DoProcess.doOneLine(data, withComments, line);
                            line = getLine();
                        } catch (Exception e) {
                            log.error("{}", e);
                            //抛出异常时不打印生成结果而是打印原来语句
                            data.append(StringConstants.CONMMENT_SYMBOL + StringConstants.TABLE + line + StringConstants.LF);
                            line = getLine();
                            continue;
                        }
                    }
                } else {
                    if (StringUtils.equalsIgnoreCase(line, StringConstants.EXIT)) {
                        break;
                    }

                    if (checkOption(line)) continue;

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

    // 排除空行，切换模式，如果是切换模式操作
    private static String getLine() {
        String line = in.nextCodeLine();
        while (StringUtils.isBlank(line)) line = in.nextCodeLine();

        if (checkOption(line)) {
            changeInputModel(line);
            changeOutputModel(line);
            log.info("模式切换完成");
        }


        return line;
    }

    // 判断是否是切换模式操作
    private static boolean checkOption(String line) {
        return StringUtils.equalsAnyIgnoreCase(line, StringConstants.MULTI_INPUT, StringConstants.ONELINE_INPUT, StringConstants.WITH_COMMENTS, StringConstants.WITHOUT_COMMENTS);
    }


    // 切换输入模式
    private static boolean changeInputModel(String line) {
        if (StringUtils.equalsAnyIgnoreCase(line, StringConstants.MULTI_INPUT, StringConstants.ONELINE_INPUT)) {
            isMultiInput = StringUtils.equalsIgnoreCase(line, StringConstants.MULTI_INPUT);
        }
        return isMultiInput;
    }

    // 切换输出模式
    private static boolean changeOutputModel(String line) {
        if (StringUtils.equalsAnyIgnoreCase(line, StringConstants.WITH_COMMENTS, StringConstants.WITHOUT_COMMENTS)) {
            withComments = StringUtils.equalsIgnoreCase(line, StringConstants.WITH_COMMENTS);
        }
        return withComments;
    }
}
