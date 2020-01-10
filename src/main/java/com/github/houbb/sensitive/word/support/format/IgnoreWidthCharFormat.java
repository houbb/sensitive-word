package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.ICharFormat;

/**
 * 格式化责任链
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class IgnoreWidthCharFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        return CharUtil.toHalfWidth(original);
    }

}
