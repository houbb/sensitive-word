package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.ICharFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

/**
 * 无处理
 *
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class NoneCharFormat implements ICharFormat {

    private static final ICharFormat INSTANCE = new NoneCharFormat();

    public static ICharFormat getInstance() {
        return INSTANCE;
    }

    @Override
    public char format(char original, IWordContext context) {
        return original;
    }

}
