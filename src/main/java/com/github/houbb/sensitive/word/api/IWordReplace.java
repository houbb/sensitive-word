package com.github.houbb.sensitive.word.api;

/**
 * 敏感词替换策略
 *
 * @author binbin.hou
 * @since 0.2.0
 */
public interface IWordReplace {

    /**
     * 替换
     * <p>
     * 説明：废弃以前的字符串返回，减少对象创建，提升性能。
     *
     * @param stringBuilder 字符串连接器
     * @param rawText      原始字符串
     * @param wordResult    当前的敏感词结果
     * @param wordContext   上下文
     * @since 0.4.0
     */
    void replace(final StringBuilder stringBuilder, final String rawText, final IWordResult wordResult, final IWordContext wordContext);

}
