package com.github.houbb.sensitive.word.api;

import java.util.Map;

/**
 * 单词整体格式化
 *
 * @author binbin.hou
 * @since 0.28.0
 */
public interface IWordFormatText {

    /**
     * 针对 text 格式化映射，提升对整体的控制力
     *
     * @param text 原始 文本
     * @param context 上下文
     * @return 格式化后的 char
     * @since 0.28.0
     */
    Map<Character, Character> format(final String text, final IWordContext context);

}
