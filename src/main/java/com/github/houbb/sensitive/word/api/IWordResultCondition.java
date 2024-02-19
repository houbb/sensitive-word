package com.github.houbb.sensitive.word.api;

import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;

/**
 * 敏感词的结果是否匹配
 * @author binbin.hou
 * @since 0.13.0
 */
public interface IWordResultCondition {

    /**
     * 是否匹配
     * @param wordResult 根据词匹配的结果
     * @param text 原始文本
     * @param modeEnum 枚举类别
     * @param context 上下文
     * @return 是否匹配
     * @since 0.13.0
     */
    boolean match(final IWordResult wordResult,
                  final String text,
                  final WordValidModeEnum modeEnum,
                  final IWordContext context);

}
