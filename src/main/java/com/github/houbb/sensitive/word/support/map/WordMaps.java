package com.github.houbb.sensitive.word.support.map;

import com.github.houbb.sensitive.word.api.IWordMap;

/**
 * 敏感词 map
 *
 * @author binbin.hou
 * @since 0.3.0
 */
public final class WordMaps {

    private WordMaps(){}

    /**
     * 默认策略
     * @return 策略
     * @since 0.3.0
     */
    public static IWordMap defaults() {
        return new SensitiveWordMap();
    }

}
