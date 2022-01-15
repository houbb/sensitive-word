package com.github.houbb.sensitive.word.core;

import com.github.houbb.sensitive.word.api.ISensitiveWordReplace;
import com.github.houbb.sensitive.word.api.IWordResultHandler;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;

import java.util.List;

/**
 * 敏感词工具类
 *
 * @author binbin.hou
 * @since 0.0.13
 */
public final class SensitiveWordHelper {

    private SensitiveWordHelper() {
    }

    /**
     * 默认的实现
     *
     * @since 0.0.13
     */
    private static final SensitiveWordBs WORD_BS = SensitiveWordBs.newInstance().init();

    /**
     * 是否包含敏感词
     *
     * @param target 目标字符串
     * @return 是否
     * @since 0.0.13
     */
    public static boolean contains(final String target) {
        return WORD_BS.contains(target);
    }

    /**
     * 返回所有的敏感词
     * 1. 这里是默认去重的，且是有序的。
     * 2. 如果不存在，返回空列表
     *
     * @param target 目标字符串
     * @return 敏感词列表
     * @since 0.0.1
     */
    public static List<String> findAll(final String target) {
        return WORD_BS.findAll(target);
    }

    /**
     * 返回第一个敏感词
     * （1）如果不存在，则返回 {@code null}
     *
     * @param target 目标字符串
     * @return 敏感词
     * @since 0.0.13
     */
    public static String findFirst(final String target) {
        return WORD_BS.findFirst(target);
    }

    /**
     * 替换所有内容
     *
     * @param target      目标字符串
     * @param replace 替换策略
     * @return 替换后结果
     * @since 0.2.0
     */
    public static String replace(final String target, final ISensitiveWordReplace replace) {
        return WORD_BS.replace(target, replace);
    }

    /**
     * 替换所有内容
     *
     * @param target      目标字符串
     * @param replaceChar 替换为的 char
     * @return 替换后结果
     * @since 0.0.13
     */
    public static String replace(final String target, final char replaceChar) {
        return WORD_BS.replace(target, replaceChar);
    }

    /**
     * 替换所有内容
     * 1. 默认使用空格替换，避免星号改变 md 的格式。
     *
     * @param target 目标字符串
     * @return 替换后结果
     * @since 0.0.13
     */
    public static String replace(final String target) {
        return WORD_BS.replace(target);
    }

    /**
     * 返回所有的敏感词
     *
     * @param target  目标字符串
     * @param handler 结果处理类
     * @param <R> 泛型
     * @return 敏感词列表
     * @since 0.1.0
     */
    public static <R> List<R> findAll(final String target,
                                      final IWordResultHandler<R> handler) {
        return WORD_BS.findAll(target, handler);
    }

    /**
     * 返回第一个敏感词
     * （1）如果不存在，则返回 {@code null}
     *
     * @param target  目标字符串
     * @param handler 结果处理类
     * @param <R> 泛型
     * @return 敏感词
     * @since 0.1.0
     */
    public static <R> R findFirst(final String target,
                                  final IWordResultHandler<R> handler) {
        return WORD_BS.findFirst(target, handler);
    }

}
