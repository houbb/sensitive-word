package com.github.houbb.sensitive.word.api;

/**
 * 敏感词替换策略上下文
 *
 * @author binbin.hou
 * @since 0.2.0
 */
public interface ISensitiveWordReplaceContext {

    /**
     * 敏感词
     * @return 敏感词
     * @since 0.2.0
     */
    String sensitiveWord();

    /**
     * 单词长度
     * @return 单词长度
     * @since 0.2.0
     */
    int wordLength();

}
