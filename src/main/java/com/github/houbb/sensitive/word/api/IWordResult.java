package com.github.houbb.sensitive.word.api;

/**
 * 敏感词的结果
 * @author binbin.hou
 * @since 0.1.0
 */
public interface IWordResult {

    /**
     * 开始下标
     * @return 开始下标
     * @since 0.1.0
     */
    int startIndex();

    /**
     * 结束下标
     * @return 结束下标
     * @since 0.1.0
     */
    int endIndex();

    /**
     * 类别
     * @return 类别
     * @since 0.14.0
     */
    String type();

}
