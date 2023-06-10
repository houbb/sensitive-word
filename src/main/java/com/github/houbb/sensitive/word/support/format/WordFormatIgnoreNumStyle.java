package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.utils.InnerWordNumUtils;

/**
 * 忽略数字的样式
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class WordFormatIgnoreNumStyle implements IWordFormat {

    private static final IWordFormat INSTANCE = new WordFormatIgnoreNumStyle();

    public static IWordFormat getInstance() {
        return INSTANCE;
    }

    @Override
    public char format(char original, IWordContext context) {
        return InnerWordNumUtils.getMappingChar(original);
    }

}
