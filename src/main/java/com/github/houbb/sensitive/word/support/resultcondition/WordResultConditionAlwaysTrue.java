package com.github.houbb.sensitive.word.support.resultcondition;

import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;

/**
 * 恒为真
 * 
 * @since 0.13.0
 */
public class WordResultConditionAlwaysTrue extends AbstractWordResultCondition {

    @Override
    protected boolean doMatch(IWordResult wordResult, String text, WordValidModeEnum modeEnum, IWordContext context) {
        return true;
    }

}
