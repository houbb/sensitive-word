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

    /**
     * 黑名单匹配词
     * @since 0.25.1
     */
    private String wordDeny;

    /**
     * 白名单实际匹配值
     * @since 0.25.1
     */
    private String wordAllow;

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

    public String wordDeny() {
        return wordDeny;
    }

    public WordLengthResult wordDeny(String wordDeny) {
        this.wordDeny = wordDeny;
        return this;
    }

    public String wordAllow() {
        return wordAllow;
    }

    public WordLengthResult wordAllow(String wordAllow) {
        this.wordAllow = wordAllow;
        return this;
    }

    @Override
    public String toString() {
        return "WordLengthResult{" +
                "wordAllowLen=" + wordAllowLen +
                ", wordDenyLen=" + wordDenyLen +
                ", wordDeny='" + wordDeny + '\'' +
                ", wordAllow='" + wordAllow + '\'' +
                '}';
    }

}
