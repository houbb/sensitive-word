package com.github.houbb.sensitive.word.utils;

import com.github.houbb.sensitive.word.api.IWordResult;

/**
 * <p> project: sensitive-word-NumUtils </p>
 * <p> create on 2020/1/8 22:18 </p>
 *
 * @author Administrator
 * @since 0.0.4
 */
public final class InnerWordCharUtils {

    private InnerWordCharUtils() {
    }

    /**
     * 构建字符串
     * @param text 字符串
     * @param startIndex 开始位置
     * @param endIndex 结束位置
     * @return 结果
     * @since 0.29.0
     */
    public static String getString(final String text,
                                   final int startIndex,
                                   final int endIndex) {
        return text.substring(startIndex, endIndex);
    }
    /**
     * 构建字符串
     * @param text 字符串
     * @param wordResult 结果
     * @return 结果
     * @since 0.29.0
     */
    public static String getString(final String text,
                                   final IWordResult wordResult) {
        return getString(text, wordResult.startIndex(), wordResult.endIndex());
    }

}
