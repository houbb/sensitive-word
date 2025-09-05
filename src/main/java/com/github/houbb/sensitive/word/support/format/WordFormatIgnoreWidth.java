package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.utils.InnerCharUtils;

/**
 * 格式化字宽度
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class WordFormatIgnoreWidth implements IWordFormat {

    private static final IWordFormat INSTANCE = new WordFormatIgnoreWidth();

    public static IWordFormat getInstance() {
        return INSTANCE;
    }

    @Override
    public char format(char original, IWordContext context) {
        return InnerCharUtils.toHalfWidth(original);
    }

}
