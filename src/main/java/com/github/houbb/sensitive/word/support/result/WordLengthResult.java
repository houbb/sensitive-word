package com.github.houbb.sensitive.word.support.result;

/**
 * 说明：统一让黑白名单一次遍历，性能优化
 *
 * @since 0.24.2
 */
public class WordLengthResult {
    /**
     * 白名单长度
     */
    private int wordAllowLen;
    /**
     * 黑名单长度
     */
    private int wordDenyLen;

    public static WordLengthResult newInstance() {
        return new WordLengthResult();
    }

    public int wordAllowLen() {
        return this.wordAllowLen;
    }

    public WordLengthResult wordAllowLen(int wordAllowLen) {
        this.wordAllowLen = wordAllowLen;
        return this;
    }

    public int wordDenyLen() {
        return this.wordDenyLen;
    }

    public WordLengthResult wordDenyLen(int wordDenyLen) {
        this.wordDenyLen = wordDenyLen;
        return this;
    }

    @Override
    public String toString() {
        return "WordLengthResult{" +
                "wordAllowLen=" + wordAllowLen +
                ", wordDenyLen=" + wordDenyLen +
                '}';
    }

}
