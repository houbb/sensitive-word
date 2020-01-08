package com.github.houbb.sensitive.word.model;

/**
 * 检测敏感词结果
 *
 * TODO: 这里需要结合 KMP 和 暴力匹配算法。
 *
 * 暂时不使用，后期会使用到。
 * @author binbin.hou
 * @since 0.0.2
 */
@Deprecated
public class CheckSensitiveWordResult {

    /**
     * 是否匹配到了敏感词
     * @since 0.0.2
     */
    private boolean hasMatch;

    /**
     * 敏感词长度
     * @since 0.0.2
     */
    private int sensitiveWordSize;

    /**
     * 普通单词的长度
     * @since 0.0.2
     */
    private int commonWordSize;

    public boolean hasMatch() {
        return hasMatch;
    }

    public CheckSensitiveWordResult hasMatch(boolean hasMatch) {
        this.hasMatch = hasMatch;
        return this;
    }

    public int sentiveWordSize() {
        return sensitiveWordSize;
    }

    public CheckSensitiveWordResult sentiveWordSize(int sentiveWordSize) {
        this.sensitiveWordSize = sentiveWordSize;
        return this;
    }

    public int commonWordSize() {
        return commonWordSize;
    }

    public CheckSensitiveWordResult commonWordSize(int commonWordSize) {
        this.commonWordSize = commonWordSize;
        return this;
    }

    @Override
    public String toString() {
        return "CheckSensitiveWordResult{" +
                "hasMatch=" + hasMatch +
                ", sensitiveWordSize=" + sensitiveWordSize +
                ", commonWordSize=" + commonWordSize +
                '}';
    }

}
