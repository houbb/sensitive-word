package com.github.houbb.sensitive.word.model;

import java.util.List;

/**
 * 所有的敏感词，第一个字都是 key
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public class WordMapEntry {

    /**
     * 单个单词
     * @since 0.0.1
     */
    private String word;

    /**
     * 是否为结束
     * @since 0.0.1
     */
    private boolean isEnd;

    /**
     * 下一层的信息列表
     * @since 0.0.1
     */
    private List<WordMapEntry> nextEntryList;

    public String word() {
        return word;
    }

    public WordMapEntry word(String word) {
        this.word = word;
        return this;
    }

    public boolean end() {
        return isEnd;
    }

    public WordMapEntry end(boolean end) {
        isEnd = end;
        return this;
    }

    public List<WordMapEntry> nextEntryList() {
        return nextEntryList;
    }

    public WordMapEntry nextEntryList(List<WordMapEntry> nextEntryList) {
        this.nextEntryList = nextEntryList;
        return this;
    }

}
