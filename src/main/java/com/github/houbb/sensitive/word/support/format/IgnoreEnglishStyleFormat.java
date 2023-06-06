package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.ICharFormat;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.utils.InnerCharUtils;

/**
 * 忽略英文的各种格式
 * @author binbin.hou
 * @since 0.0.6
 */
@ThreadSafe
public class IgnoreEnglishStyleFormat implements ICharFormat {

    private static final ICharFormat INSTANCE = new IgnoreEnglishStyleFormat();

    public static ICharFormat getInstance() {
        return INSTANCE;
    }

    @Override
    public char format(char original, IWordContext context) {
        return InnerCharUtils.getMappingChar(original);
    }

}
