package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.opencc4j.util.ZhSlimUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordFormat;

/**
 * 忽略中文样式
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class WordFormatIgnoreChineseStyle implements IWordFormat {

    private static final IWordFormat INSTANCE = new WordFormatIgnoreChineseStyle();

    public static IWordFormat getInstance() {
        return INSTANCE;
    }

    @Override
    public char format(char original, IWordContext context) {
        return ZhSlimUtil.toSimple(original);
    }

}
