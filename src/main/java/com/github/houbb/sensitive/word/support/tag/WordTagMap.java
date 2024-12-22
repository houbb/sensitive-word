package com.github.houbb.sensitive.word.support.tag;

import com.github.houbb.heaven.util.common.ArgUtil;

import java.util.Map;
import java.util.Set;

/**
 * 根据 map 构建初始化
 *
 * key:单词
 * value: 标签 set
 *
 * @since 0.24.0
 */
public class WordTagMap extends AbstractWordTag {

    private final Map<String, Set<String>> wordTagMap;

    public WordTagMap(Map<String, Set<String>> wordTagMap) {
        ArgUtil.notNull(wordTagMap, "wordTagMap");
        this.wordTagMap = wordTagMap;
    }

    @Override
    protected Set<String> doGetTag(String word) {
        return wordTagMap.get(word);
    }

}
