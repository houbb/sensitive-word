package com.github.houbb.sensitive.word.support.ignore;

import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;

/**
 * 特殊字符忽略
 * @since 0.11.0
 */
public class NoneSensitiveWordCharIgnore extends AbstractSensitiveWordCharIgnore {

    @Override
    protected boolean doIgnore(int ix, char[] chars, InnerSensitiveWordContext innerContext) {
        return false;
    }

}
