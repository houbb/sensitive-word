package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.ICharFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

/**
 * 忽略大小写
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class IgnoreCaseCharFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        return Character.toLowerCase(original);
    }

}
