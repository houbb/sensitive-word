package com.github.houbb.sensitive.word.api;

import java.util.Map;

/**
 * @author binbin.hou
 * @since 0.0.4
 */
public interface IWordContext {

    /**
     * 是否忽略大小写
     * @return 是否
     * @since 0.0.4
     */
    boolean ignoreCase();

    /**
     * 是否忽略半角圆角
     * @return 是否
     * @since 0.0.4
     */
    boolean ignoreWidth();

    /**
     * 是否忽略数字格式
     * @return 是否
     * @since 0.0.5
     */
    boolean ignoreNumStyle();

    /**
     * 设置是否忽略大小写
     * @param ignoreCase 是否忽略大小写
     * @return this
     * @since 0.0.4
     */
    IWordContext ignoreCase(boolean ignoreCase);

    /**
     * 设置是否忽略半角圆角
     * @param ignoreWidth 是否忽略半角圆角
     * @return this
     * @since 0.0.4
     */
    IWordContext ignoreWidth(boolean ignoreWidth);

    /**
     * 设置是否忽略半角圆角
     * @param ignoreNumStyle 是否忽略半角圆角
     * @return this
     * @since 0.0.5
     */
    IWordContext ignoreNumStyle(boolean ignoreNumStyle);

    /**
     * 忽略中文繁简体格式
     * @return 是否
     * @since 0.0.6
     */
    boolean ignoreChineseStyle();

    /**
     * 设置是否忽略中文繁简体格式
     * @param ignoreChineseStyle 是否忽略
     * @return this
     * @since 0.0.6
     */
    IWordContext ignoreChineseStyle(final boolean ignoreChineseStyle);

    /**
     * 获取敏感词信息
     * @return 敏感词
     * @since 0.0.5
     */
    Map sensitiveWordMap();

    /**
     * 敏感词信息
     * @param map map 信息
     * @return this
     * @since 0.0.5
     */
    IWordContext sensitiveWordMap(final Map map);

    /**
     * 敏感数字检测
     * @return 数字检测
     * @since 0.0.5
     */
    boolean sensitiveNumCheck();

    /**
     * 设置敏感数字检测
     * @param sensitiveNumCheck 数字格式检测
     * @return this
     * @since 0.0.5
     */
    IWordContext sensitiveNumCheck(final boolean sensitiveNumCheck);

    /**
     * 忽略英文的写法
     * @return 数字检测
     * @since 0.0.6
     */
    boolean ignoreEnglishStyle();

    /**
     * 设置忽略英文的写法
     * @param ignoreEnglishStyle 是否忽略
     * @return this
     * @since 0.0.6
     */
    IWordContext ignoreEnglishStyle(final boolean ignoreEnglishStyle);

    /**
     * 忽略重复词
     * @return 是否忽略
     * @since 0.0.7
     */
    boolean ignoreRepeat();

    /**
     * 设置忽略重复词
     * @param ignoreRepeat 是否忽略
     * @return this
     * @since 0.0.7
     */
    IWordContext ignoreRepeat(final boolean ignoreRepeat);

}
