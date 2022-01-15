package com.github.houbb.sensitive.word.support.replace;

import com.github.houbb.sensitive.word.api.ISensitiveWordReplaceContext;

/**
 * 敏感词替换上下文
 *
 * @author binbin.hou
 * @since 0.2.0
 */
public class SensitiveWordReplaceContext implements ISensitiveWordReplaceContext {

    public static SensitiveWordReplaceContext newInstance() {
        return new SensitiveWordReplaceContext();
    }

    /**
     * 敏感词
     * @since 0.2.0
     */
    private String sensitiveWord;

    /**
     * 单词长度
     * @since 0.2.0
     */
    private int wordLength;

    @Override
    public String sensitiveWord() {
        return sensitiveWord;
    }

    public SensitiveWordReplaceContext sensitiveWord(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
        return this;
    }

    @Override
    public int wordLength() {
        return wordLength;
    }

    public SensitiveWordReplaceContext wordLength(int wordLength) {
        this.wordLength = wordLength;
        return this;
    }

    @Override
    public String toString() {
        return "SensitiveWordReplaceContext{" +
                "sensitiveWord='" + sensitiveWord + '\'' +
                ", wordLength=" + wordLength +
                '}';
    }

}
