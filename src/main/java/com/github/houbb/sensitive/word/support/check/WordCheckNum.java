package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordTypeEnum;

/**
 * 敏感词监测实现
 *
 * 这里可以提供一个公共的父类。
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class WordCheckNum extends AbstractConditionWordCheck {

    /**
     * @since 0.3.0
     */
    private static final IWordCheck INSTANCE = new WordCheckNum();

    public static IWordCheck getInstance() {
        return INSTANCE;
    }

    @Override
    protected Class<? extends IWordCheck> getSensitiveCheckClass() {
        return WordCheckNum.class;
    }

    @Override
    protected String getType() {
        return WordTypeEnum.NUM.getCode();
    }

    @Override
    protected boolean isCharCondition(char mappingChar, int index, InnerSensitiveWordContext checkContext) {
        return Character.isDigit(mappingChar);
    }

    @Override
    protected boolean isStringCondition(int index, StringBuilder stringBuilder, InnerSensitiveWordContext checkContext) {
        int bufferLen = stringBuilder.length();
        return bufferLen >= checkContext.wordContext().sensitiveCheckNumLen();
    }

}
