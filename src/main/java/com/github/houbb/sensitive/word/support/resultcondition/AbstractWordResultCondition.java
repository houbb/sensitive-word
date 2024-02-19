package com.github.houbb.sensitive.word.support.resultcondition;

import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.api.IWordResultCondition;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;

/**
 * 抽象实现
 *
 * @since 0.13.0
 */
public abstract class AbstractWordResultCondition implements IWordResultCondition {

    protected abstract boolean doMatch(IWordResult wordResult, String text, WordValidModeEnum modeEnum, IWordContext context);

    @Override
    public boolean match(IWordResult wordResult, String text, WordValidModeEnum modeEnum, IWordContext context) {
        return doMatch(wordResult, text, modeEnum, context);
    }

}
