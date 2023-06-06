package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.ICharFormat;

/**
 * 格式化字宽度
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class IgnoreWidthCharFormat implements ICharFormat {

    private static final ICharFormat INSTANCE = new IgnoreWidthCharFormat();

    public static ICharFormat getInstance() {
        return INSTANCE;
    }

    @Override
    public char format(char original, IWordContext context) {
        return CharUtil.toHalfWidth(original);
    }

}
