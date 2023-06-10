package com.github.houbb.sensitive.word.support.replace;

import com.github.houbb.sensitive.word.api.IWordReplace;

/**
 * 字符替换策略工具类
 *
 * @author binbin.hou
 * @since 0.3.0
 */
public final class WordReplaces {

    private WordReplaces(){}

    /**
     * 字符
     * @param c 字符
     * @return 结果
     * @since 0.3.0
     */
    public static IWordReplace chars(final char c) {
        return new WordReplaceChar(c);
    }

    /**
     * 字符，默认为 *
     * @return 结果
     * @since 0.3.0
     */
    public static IWordReplace chars() {
        return new WordReplaceChar();
    }

    /**
     * 字符，默认为 *
     * @return 结果
     * @since 0.7.0
     */
    public static IWordReplace defaults() {
        return chars();
    }

}
