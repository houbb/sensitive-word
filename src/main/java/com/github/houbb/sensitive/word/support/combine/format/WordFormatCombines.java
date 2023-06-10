package com.github.houbb.sensitive.word.support.combine.format;

import com.github.houbb.sensitive.word.api.combine.IWordFormatCombine;

/**
 * @author d
 * @since 0.8.0
 */
public final class WordFormatCombines {

    /**
     * 默认策略
     * @return 策略
     * @since 0.8.0
     */
    public static IWordFormatCombine defaults() {
        return new WordFormatCombine();
    }

}
