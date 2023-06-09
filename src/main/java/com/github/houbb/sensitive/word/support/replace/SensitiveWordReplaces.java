package com.github.houbb.sensitive.word.support.replace;

import com.github.houbb.sensitive.word.api.ISensitiveWordReplace;

/**
 * 字符替换策略工具类
 *
 * @author binbin.hou
 * @since 0.3.0
 */
public final class SensitiveWordReplaces {

    private SensitiveWordReplaces(){}

    /**
     * 字符
     * @param c 字符
     * @return 结果
     * @since 0.3.0
     */
    public static ISensitiveWordReplace chars(final char c) {
        return new SensitiveWordReplaceChar(c);
    }

    /**
     * 字符，默认为 *
     * @return 结果
     * @since 0.3.0
     */
    public static ISensitiveWordReplace chars() {
        return new SensitiveWordReplaceChar();
    }

    /**
     * 字符，默认为 *
     * @return 结果
     * @since 0.7.0
     */
    public static ISensitiveWordReplace defaults() {
        return chars();
    }

}
