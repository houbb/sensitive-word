package com.github.houbb.sensitive.word.api;

import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;

import java.util.List;

/**
 * 核心方法
 * @since 0.3.2
 */
public interface ISensitiveWord {

    /**
     * 返回所有对应的敏感词
     * @param string 原始字符串
     * @param context 上下文
     * @return 结果
     * @since 0.0.1
     * @see WordValidModeEnum#FAIL_OVER 建议使用全部检测返回模式
     */
    List<IWordResult> findAll(final String string,
                              final IWordContext context);

    /**
     * 返回第一个对应的敏感词
     * @param string 原始字符串
     * @param context 上下文
     * @return 结果
     * @since 0.3.2
     */
    IWordResult findFirst(final String string,
                          final IWordContext context);

    /**
     * 替换所有敏感词内容
     *
     * ps: 这里可以添加优化。
     *
     * @param target 目标字符串
     * @param context 上下文
     * @param wordReplace 替换策略
     * @return 替换后结果
     * @since 0.3.2
     */
    String replace(final String target,
                   final IWordContext context,
                   final IWordReplace replace);

    /**
     * 包含
     * @param string 字符串
     * @param context 上下文
     * @return 结果
     * @since 0.3.2
     */
    boolean contains(final String string,
             final IWordContext context);

}
