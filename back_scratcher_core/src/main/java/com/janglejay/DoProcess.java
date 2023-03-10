package com.janglejay;

import com.janglejay.constant.StringConstants;
import com.janglejay.deconstruction.MixedDeconstruction;
import com.janglejay.handler.MixedHandler;
import com.janglejay.resolver.impl.MixedResolver;

import java.util.List;

public class DoProcess {

    public static void doOneLine(StringBuilder data, boolean withComments, String line) throws Exception {
        List<String> ret = null;
        MixedDeconstruction mixedDeconstruction = (MixedDeconstruction) new MixedResolver().resolve(line);
        ret = MixedHandler.handle(mixedDeconstruction);
        if (ret != null) {
            if (withComments)
                data.append(StringConstants.COMMENT_SYMBOL + StringConstants.TABLE + line + StringConstants.LF);
            for (String s : ret) {
                data.append(s + StringConstants.LF);
            }
        }
        data.append(StringConstants.LF);
    }

}
