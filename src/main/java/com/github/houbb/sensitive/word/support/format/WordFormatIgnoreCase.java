package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

/**
 * 忽略大小写
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class WordFormatIgnoreCase implements IWordFormat {

    private static final IWordFormat INSTANCE = new WordFormatIgnoreCase();

    public static IWordFormat getInstance() {
        return INSTANCE;
    }

    @Override
    public char format(char original, IWordContext context) {
        return Character.toLowerCase(original);
    }

}
