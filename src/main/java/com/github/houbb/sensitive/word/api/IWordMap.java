package com.github.houbb.sensitive.word.api;

import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;

import java.util.Collection;
import java.util.List;

/**
 * 敏感词 map
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IWordMap {


    /**
     * 初始化敏感词 map
     * @param collection 集合信息
     * @since 0.0.1
     */
    void initWordMap(Collection<String> collection);

    /**
     * 是否包含敏感词
     * @param string 字符串
     * @param context 上下文
     * @return 是否包含
     * @since 0.0.1
     * @see ValidModeEnum#FAIL_FAST 建议使用快速返回模式
     */
    boolean contains(final String string,
                     final IWordContext context);

    /**
     * 返回所有对应的敏感词
     * @param string 原始字符串
     * @param context 上下文
     * @return 结果
     * @since 0.0.1
     * @see ValidModeEnum#FAIL_OVER 建议使用全部检测返回模式
     */
    List<String> findAll(final String string,
                         final IWordContext context);

    /**
     * 返回第一个对应的敏感词
     * @param string 原始字符串
     * @param context 上下文
     * @return 结果
     * @since 0.0.1
     */
    String findFirst(final String string,
                     final IWordContext context);

    /**
     * 替换所有敏感词内容
     *
     * ps: 这里可以添加优化。
     *
     * @param target 目标字符串
     * @param replaceChar 替换为的 char
     * @param context 上下文
     * @return 替换后结果
     * @since 0.0.2
     */
    String replace(final String target, final char replaceChar,
                   final IWordContext context);

}
