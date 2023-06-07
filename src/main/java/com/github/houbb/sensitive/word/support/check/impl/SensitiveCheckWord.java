package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;

/**
 * 敏感词监测实现
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class SensitiveCheckWord extends AbstractSensitiveCheck {

    /**
     * @since 0.3.0
     */
    private static final ISensitiveCheck INSTANCE = new SensitiveCheckWord();

    public static ISensitiveCheck getInstance() {
        return INSTANCE;
    }

    @Override
    protected boolean isCharCondition(char mappingChar, int index, String rawText, IWordContext context) {
        return true;
    }

    @Override
    protected boolean isStringCondition(int index, String rawText, StringBuilder stringBuilder, IWordContext context) {
        return context.wordMap().contains(stringBuilder.toString(), context);
    }

    @Override
    protected Class<? extends ISensitiveCheck> getSensitiveCheckClass() {
        return SensitiveCheckWord.class;
    }

}
