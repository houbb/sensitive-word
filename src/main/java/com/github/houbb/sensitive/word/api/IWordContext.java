package com.github.houbb.sensitive.word.api;

import com.github.houbb.sensitive.word.core.NodeTree;


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
     * @return 敏感词树
     * @since 0.0.5
     */
    NodeTree sensitiveWordInfo();

    /**
     * 敏感词信息
     * @param nodeTree 敏感词树
     * @return this
     * @since 0.0.5
     */
    IWordContext sensitiveWordInfo(final NodeTree nodeTree);

    /**
     * 敏感数字检测
     * @return 数字检测
     * @since 0.0.5
     */
    boolean sensitiveCheckNum();

    /**
     * 设置敏感数字检测
     * @param sensitiveCheckNum 数字格式检测
     * @return this
     * @since 0.0.5
     */
    IWordContext sensitiveCheckNum(final boolean sensitiveCheckNum);

    /**
     * 是否进行邮箱检测
     * @return this
     * @since 0.0.9
     */
    boolean sensitiveCheckEmail();

    /**
     * 设置敏感邮箱检测
     * @param sensitiveCheckEmail 是否检测
     * @return this
     * @since 0.0.9
     */
    IWordContext sensitiveCheckEmail(final boolean sensitiveCheckEmail);

    /**
     * 敏感链接检测
     * @return 是否启用
     * @since 0.
     */
    boolean sensitiveCheckUrl();

    /**
     * 设置敏感邮箱检测
     * @param sensitiveCheckUrl 是否检测
     * @return this
     * @since 0.0.9
     */
    IWordContext sensitiveCheckUrl(final boolean sensitiveCheckUrl);

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

    /**
     * 敏感数字检测
     * @return 数字检测
     * @since 0.2.1
     */
    int sensitiveCheckNumLen();

    /**
     * 设置敏感数字检测长度
     * @param sensitiveCheckNumLen 数字格式检测长度
     * @return this
     * @since 0.2.1
     */
    IWordContext sensitiveCheckNumLen(final int sensitiveCheckNumLen);

}
