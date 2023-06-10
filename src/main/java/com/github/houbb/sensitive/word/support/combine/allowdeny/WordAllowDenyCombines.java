package com.github.houbb.sensitive.word.support.combine.allowdeny;

import com.github.houbb.sensitive.word.api.combine.IWordAllowDenyCombine;

/**
 * @author d
 * @since 1.0.0
 */
public final class WordAllowDenyCombines {

    private WordAllowDenyCombines(){}

    public static IWordAllowDenyCombine defaults() {
        return new WordAllowDenyCombine();
    }

}
