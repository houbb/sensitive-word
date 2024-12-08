package com.github.houbb.sensitive.word.support.resultcondition;

import com.github.houbb.sensitive.word.support.tag.AbstractWordTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义单词标签
 * @since 0.23.0
 */
public class MyWordTag extends AbstractWordTag {

    private static Map<String, Set<String>> dataMap;

    static {
        dataMap = new HashMap<>();
        dataMap.put("商品", buildSet("广告", "中文"));
        dataMap.put("AV", buildSet("色情", "单词", "英文"));
    }

    private static Set<String> buildSet(String... tags) {
        Set<String> set = new HashSet<>();
        for(String tag : tags) {
            set.add(tag);
        }
        return set;
    }

    @Override
    protected Set<String> doGetTag(String word) {
        return dataMap.get(word);
    }

}
