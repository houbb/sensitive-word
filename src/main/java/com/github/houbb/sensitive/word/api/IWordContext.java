package com.github.houbb.sensitive.word.api;

import com.github.houbb.sensitive.word.bs.SensitiveWordContext;

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
     * 是否启用单词
     * @return 是否
     */
    boolean enableWordCheck();

    /**
     * 设置是否启用单词
     * @param enableWordCheck 是否
     * @return 结果
     */
    IWordContext enableWordCheck(boolean enableWordCheck);

    /**
     * 敏感数字检测
     * @return 数字检测
     * @since 0.0.5
     */
    boolean enableNumCheck();

    /**
     * 设置敏感数字检测
     * @param enableNumCheck 数字格式检测
     * @return this
     * @since 0.0.5
     */
    IWordContext enableNumCheck(final boolean enableNumCheck);

    /**
     * 是否进行邮箱检测
     * @return this
     * @since 0.0.9
     */
    boolean enableEmailCheck();

    /**
     * 设置敏感邮箱检测
     * @param enableEmailCheck 是否检测
     * @return this
     * @since 0.0.9
     */
    IWordContext enableEmailCheck(final boolean enableEmailCheck);

    /**
     * 敏感链接检测
     * @return 是否启用
     * @since 0.
     */
    boolean enableUrlCheck();

    /**
     * 设置敏感邮箱检测
     * @param enableUrlCheck 是否检测
     * @return this
     * @since 0.0.9
     */
    IWordContext enableUrlCheck(final boolean enableUrlCheck);

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
    IWordContext sensitiveCheck(final IWordCheck sensitiveCheck);

    /**
     * 获取检测策略
     * @return 检测策略
     * @since 0.3.0
     */
    IWordCheck sensitiveCheck();

    /**
     * 设置敏感词替换策略
     * @param wordReplace 策略
     * @return this
     * @since 0.3.0
     */
    IWordContext wordReplace(final IWordReplace wordReplace);

    /**
     * 敏感词替换策略
     * @return 替换策略
     * @since 0.3.0
     */
     IWordReplace wordReplace();

    /**
     * 设置统一的字符处理
     *
     * @param wordFormat 字符处理
     * @return 结果
     * @since 0.3.0
     */
    IWordContext wordFormat(final IWordFormat wordFormat);

    /**
     * 获取格式化策略
     *
     * @return 策略
     * @since 0.3.0
     */
    IWordFormat wordFormat();

    /**
     * 获取 wordMap 策略
     * @return 策略
     * @since 0.3.2
     */
    IWordData wordData();

    /**
     * 设置 wordMap 策略
     * @param wordMap 策略
     * @return this
     * @since 0.3.2
     */
    IWordContext wordData(IWordData wordMap);

    IWordTag wordTag();

    SensitiveWordContext wordTag(IWordTag wordTag);

    ISensitiveWordCharIgnore charIgnore();

    SensitiveWordContext charIgnore(ISensitiveWordCharIgnore charIgnore);

}
