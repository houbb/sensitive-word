package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

/**
 * 无处理
 *
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class WordFormatNone implements IWordFormat {

    private static final IWordFormat INSTANCE = new WordFormatNone();

    public static IWordFormat getInstance() {
        return INSTANCE;
    }

    @Override
    public char format(char original, IWordContext context) {
        return original;
    }

}
