package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.ISensitiveWordCharIgnore;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordTypeEnum;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;
import com.github.houbb.sensitive.word.constant.enums.WordContainsTypeEnum;

import java.util.Map;

/**
 * 敏感词监测实现
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class WordCheckWord extends AbstractWordCheck {

    /**
     * @since 0.3.0
     */
    private static final IWordCheck INSTANCE = new WordCheckWord();

    public static IWordCheck getInstance() {
        return INSTANCE;
    }

    @Override
    protected Class<? extends IWordCheck> getSensitiveCheckClass() {
        return WordCheckWord.class;
    }

    @Override
    protected int getActualLength(int beginIndex, InnerSensitiveWordContext innerContext) {
        final String txt = innerContext.originalText();
        final Map<Character, Character> formatCharMapping = innerContext.formatCharMapping();
        final WordValidModeEnum wordValidModeEnum = innerContext.modeEnum();
        final IWordContext context = innerContext.wordContext();

        // 采用 ThreadLocal 应该可以提升性能，减少对象的创建。
        int actualLength = 0;
        final IWordData wordData = context.wordData();

        // 前一个条件
        StringBuilder stringBuilder = new StringBuilder();
        char[] rawChars = txt.toCharArray();

        final ISensitiveWordCharIgnore wordCharIgnore = context.charIgnore();
        int tempLen = 0;
        for(int i = beginIndex; i < rawChars.length; i++) {
            // 判断是否跳过？
            if(wordCharIgnore.ignore(i, rawChars, innerContext)) {
                tempLen++;
                continue;
            }

            // 映射处理
            final char currentChar = rawChars[i];
            char mappingChar = formatCharMapping.get(currentChar);
            stringBuilder.append(mappingChar);
            tempLen++;

            // 判断是否存在
            WordContainsTypeEnum wordContainsTypeEnum = wordData.contains(stringBuilder, innerContext);
            if(WordContainsTypeEnum.CONTAINS_END.equals(wordContainsTypeEnum)) {
                actualLength = tempLen;

                // 是否遍历全部匹配的模式
                if(WordValidModeEnum.FAIL_FAST.equals(wordValidModeEnum)) {
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

    @Override
    protected String getType() {
        return WordTypeEnum.WORD.getCode();
    }

}
