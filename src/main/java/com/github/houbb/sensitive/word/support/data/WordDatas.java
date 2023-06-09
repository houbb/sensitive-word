package com.github.houbb.sensitive.word.support.data;

import com.github.houbb.sensitive.word.api.IWordData;

/**
 * 敏感词 map
 *
 * @author binbin.hou
 * @since 0.3.0
 */
public final class WordDatas {

    private WordDatas(){}

    /**
     * 默认策略
     * @return 策略
     * @since 0.3.0
     */
    public static IWordData defaults() {
        return tree();
    }

    /**
     * 树模式
     * @return 树
     * @since 0.7.0
     */
    public static IWordData tree() {
        return new WordDataTree();
    }

    /**
     * 树模式
     * @return 树
     * @since 0.7.0
     */
    public static IWordData hashMap() {
        return new WordDataHashMap();
    }


}
