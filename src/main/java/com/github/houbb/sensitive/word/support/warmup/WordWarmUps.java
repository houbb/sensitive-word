package com.github.houbb.sensitive.word.support.warmup;

import com.github.houbb.sensitive.word.api.IWordWarmUp;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;

/**
 * 预热策略
 * @since 0.29.0
 */
public final class WordWarmUps  {

    public static IWordWarmUp defaults() {
        return new WordWarmUpDefault();
    }

}
