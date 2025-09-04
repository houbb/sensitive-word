package com.github.houbb.sensitive.word.support.format.mapping;

import com.github.houbb.sensitive.word.api.IWordFormatText;

/**
 * 格式化工具类
 * @author binbin.hou
 * @since 0.28.0
 */
public final class WordFormatTexts {

    private WordFormatTexts(){}

    public static IWordFormatText defaults() {
        return new WordFormatTextDefault();
    }

}
