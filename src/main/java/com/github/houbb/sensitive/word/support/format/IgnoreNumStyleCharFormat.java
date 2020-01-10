package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.ICharFormat;
import com.github.houbb.sensitive.word.utils.NumUtils;

/**
 * 忽略数字的样式
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class IgnoreNumStyleCharFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        return NumUtils.getMappingChar(original);
    }

}
