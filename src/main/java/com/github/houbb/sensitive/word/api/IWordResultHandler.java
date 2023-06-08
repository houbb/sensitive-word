package com.github.houbb.sensitive.word.api;

/**
 * 敏感词的结果处理
 * @author binbin.hou
 * @since 0.1.0
 */
public interface IWordResultHandler<R> {

    /**
     * 对于结果的处理
     * @param wordResult 结果
     * @param wordContext 上下文
     * @param originalText 原始文本
     * @return 处理结果
     * @since 0.1.0
     */
    R handle(final IWordResult wordResult,
             final IWordContext wordContext,
             final String originalText);

}
