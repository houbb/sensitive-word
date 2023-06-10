package com.github.houbb.sensitive.word.support.combine.check;

import com.github.houbb.sensitive.word.api.combine.IWordCheckCombine;

/**
 * @author d
 * @since 1.0.0
 */
public final class WordCheckCombines {

    private WordCheckCombines(){}

    public static IWordCheckCombine defaults() {
        return new WordCheckCombine();
    }

}
