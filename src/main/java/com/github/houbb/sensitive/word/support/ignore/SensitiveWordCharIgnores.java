package com.github.houbb.sensitive.word.support.ignore;

import com.github.houbb.sensitive.word.api.ISensitiveWordCharIgnore;

/**
 * @since 0.11.0
 */
public class SensitiveWordCharIgnores {

    public static ISensitiveWordCharIgnore specialChars() {
        return new SpecialCharSensitiveWordCharIgnore();
    }

    public static ISensitiveWordCharIgnore none() {
        return new NoneSensitiveWordCharIgnore();
    }

    public static ISensitiveWordCharIgnore defaults() {
        return none();
    }

}
