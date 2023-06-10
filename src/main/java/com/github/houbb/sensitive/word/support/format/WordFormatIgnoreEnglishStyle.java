package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.utils.InnerWordCharUtils;

/**
 * 忽略英文的各种格式
 * @author binbin.hou
 * @since 0.0.6
 */
@ThreadSafe
public class WordFormatIgnoreEnglishStyle implements IWordFormat {

    private static final IWordFormat INSTANCE = new WordFormatIgnoreEnglishStyle();

    public static IWordFormat getInstance() {
        return INSTANCE;
    }

    @Override
    public char format(char original, IWordContext context) {
        return InnerWordCharUtils.getMappingChar(original);
    }

}
