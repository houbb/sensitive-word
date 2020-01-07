package com.github.houbb.sensitive.word.api;

import com.github.houbb.sensitive.word.model.WordMapEntry;

import java.util.Collection;
import java.util.Map;

/**
 * 敏感词 map
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IWordMap {

    /**
     * 获取单词 map
     * @param collection 集合
     * @return 敏感词 map
     * @since 0.0.1
     */
    Map<String, WordMapEntry> getWordMap(final Collection<String> collection);

}
