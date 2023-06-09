package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveContext;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;

/**
 * 敏感词监测实现
 *
 * 这里可以提供一个公共的父类。
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class SensitiveCheckNum extends AbstractConditionSensitiveCheck {

    /**
     * @since 0.3.0
     */
    private static final ISensitiveCheck INSTANCE = new SensitiveCheckNum();

    public static ISensitiveCheck getInstance() {
        return INSTANCE;
    }

    @Override
    protected Class<? extends ISensitiveCheck> getSensitiveCheckClass() {
        return SensitiveCheckNum.class;
    }

    @Override
    protected boolean isCharCondition(char mappingChar, int index, InnerSensitiveContext checkContext) {
        return Character.isDigit(mappingChar);
    }

    @Override
    protected boolean isStringCondition(int index, StringBuilder stringBuilder, InnerSensitiveContext checkContext) {
        int bufferLen = stringBuilder.length();
        return bufferLen >= checkContext.wordContext().sensitiveCheckNumLen();
    }

}
