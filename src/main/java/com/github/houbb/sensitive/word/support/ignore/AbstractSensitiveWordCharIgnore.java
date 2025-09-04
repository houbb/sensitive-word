package com.github.houbb.sensitive.word.support.ignore;

import com.github.houbb.sensitive.word.api.ISensitiveWordCharIgnore;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;

/**
 * 抽象实现
 * @since 0.11.0
 */
public abstract class AbstractSensitiveWordCharIgnore implements ISensitiveWordCharIgnore {

    protected abstract boolean doIgnore(int ix, String text, InnerSensitiveWordContext innerContext);

    @Override
    public boolean ignore(int ix, String text, InnerSensitiveWordContext innerContext) {
        return doIgnore(ix, text, innerContext);
    }

}
