package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordMap;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
import com.github.houbb.sensitive.word.constant.enums.WordContainsTypeEnum;
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
    protected Class<? extends ISensitiveCheck> getSensitiveCheckClass() {
        return SensitiveCheckWord.class;
    }

    @Override
    protected int doGetActualLength(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        // 采用 ThreadLocal 应该可以提升性能，减少对象的创建。
        int actualLength = 0;
        final IWordMap wordMap = context.wordMap();

        // 前一个条件
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = beginIndex; i < txt.length(); i++) {
            char currentChar = txt.charAt(i);

            // 映射处理
            char mappingChar = context.charFormat().format(currentChar, context);
            stringBuilder.append(mappingChar);

            // 判断是否存在
            WordContainsTypeEnum wordContainsTypeEnum = wordMap.contains(stringBuilder.toString(), context);
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
