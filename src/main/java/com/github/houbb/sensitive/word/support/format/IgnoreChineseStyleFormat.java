package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.opencc4j.core.impl.ZhConvertBootstrap;
import com.github.houbb.opencc4j.support.segment.impl.CharSegment;
import com.github.houbb.sensitive.word.api.ICharFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

/**
 * 忽略中文样式
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class IgnoreChineseStyleFormat implements ICharFormat {

    private static final ICharFormat INSTANCE = new IgnoreChineseStyleFormat();

    public static ICharFormat getInstance() {
        return INSTANCE;
    }

    @Override
    public char format(char original, IWordContext context) {
        String string = String.valueOf(original);
        String simple = ZhConvertBootstrap.newInstance(new CharSegment()).toSimple(string);
        return simple.charAt(0);
    }

}
