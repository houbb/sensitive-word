package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveContext;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
import com.github.houbb.sensitive.word.constant.enums.WordContainsTypeEnum;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;

import java.util.Map;

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
    protected Class<? extends ISensitiveCheck> getSensitiveCheckClass() {
        return SensitiveCheckWord.class;
    }

    @Override
    protected int getActualLength(int beginIndex, InnerSensitiveContext innerContext) {
        final String txt = innerContext.originalText();
        final Map<Character, Character> formatCharMapping = innerContext.formatCharMapping();
        final ValidModeEnum validModeEnum = innerContext.modeEnum();
        final IWordContext context = innerContext.wordContext();

        // 采用 ThreadLocal 应该可以提升性能，减少对象的创建。
        int actualLength = 0;
        final IWordData wordData = context.wordData();

        // 前一个条件
        StringBuilder stringBuilder = new StringBuilder();
        char[] rawChars = txt.toCharArray();
        for(int i = beginIndex; i < rawChars.length; i++) {
            // 映射处理
            final char currentChar = rawChars[i];
            char mappingChar = formatCharMapping.get(currentChar);
            stringBuilder.append(mappingChar);

            // 判断是否存在
            WordContainsTypeEnum wordContainsTypeEnum = wordData.contains(stringBuilder, innerContext);
            if(WordContainsTypeEnum.CONTAINS_END.equals(wordContainsTypeEnum)) {
                actualLength = stringBuilder.length();

                // 是否遍历全部匹配的模式
                if(ValidModeEnum.FAIL_FAST.equals(validModeEnum)) {
                    break;
                }
            }

            // 如果不包含，则直接返回。后续遍历无意义
            if(WordContainsTypeEnum.NOT_FOUND.equals(wordContainsTypeEnum)) {
                break;
            }
        }

        return actualLength;
    }

}
