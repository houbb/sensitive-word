package com.github.houbb.sensitive.word.core;

import com.github.houbb.sensitive.word.api.ISensitiveWord;

/**
 * 策略工具类
 * @since 0.3.2
 */
public final class SensitiveWords {

    private SensitiveWords(){}

    /**
     * 默认策略
     * @return 策略
     */
    public static ISensitiveWord defaults() {
        return SensitiveWord.getInstance();
    }

}
