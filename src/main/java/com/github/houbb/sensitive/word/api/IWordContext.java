package com.github.houbb.sensitive.word.api;

import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;

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

    /**
     * 设置检测策略
     * @param sensitiveCheck 检测策略
     * @return this
     * @since 0.3.0
     */
    IWordContext sensitiveCheck(final ISensitiveCheck sensitiveCheck);

    /**
     * 获取检测策略
     * @return 检测策略
     * @since 0.3.0
     */
    ISensitiveCheck sensitiveCheck();

    /**
     * 设置敏感词替换策略
     * @param sensitiveWordReplace 策略
     * @return this
     * @since 0.3.0
     */
    IWordContext sensitiveWordReplace(final ISensitiveWordReplace sensitiveWordReplace);

    /**
     * 敏感词替换策略
     * @return 替换策略
     * @since 0.3.0
     */
     ISensitiveWordReplace sensitiveWordReplace();

    /**
     * 设置统一的字符处理
     *
     * @param charFormat 字符处理
     * @return 结果
     * @since 0.3.0
     */
    IWordContext charFormat(final ICharFormat charFormat);

    /**
     * 获取格式化策略
     *
     * @return 策略
     * @since 0.3.0
     */
    ICharFormat charFormat();

}
