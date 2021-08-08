package com.github.houbb.sensitive.word.api;

/**
 * 敏感词的结果
 * @author binbin.hou
 * @since 0.1.0
 */
public interface IWordResult {

    /**
     * 敏感词
     * @return 敏感词
     * @since 0.1.0
     */
    String word();

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

}
