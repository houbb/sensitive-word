package com.github.houbb.sensitive.word.support.result;

import com.github.houbb.sensitive.word.api.IWordResult;

/**
 * @author binbin.hou
 * @since 0.1.0
 */
public class WordResult implements IWordResult {

    private int startIndex;

    private int endIndex;

    /**
     * 词类别
     * @since 0.14.0
     */
    private String type;

    private WordResult(){}

    public static WordResult newInstance() {
        return new WordResult();
    }

    @Override
    public int startIndex() {
        return startIndex;
    }

    public WordResult startIndex(int startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    @Override
    public int endIndex() {
        return endIndex;
    }

    public WordResult endIndex(int endIndex) {
        this.endIndex = endIndex;
        return this;
    }

    @Override
    public String type() {
        return type;
    }

    public WordResult type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "WordResult{" +
                "startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", type='" + type + '\'' +
                '}';
    }

}
