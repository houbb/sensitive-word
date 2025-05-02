package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.ISensitiveWordCharIgnore;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordTypeEnum;
import com.github.houbb.sensitive.word.constant.enums.WordContainsTypeEnum;
import com.github.houbb.sensitive.word.support.result.WordLengthResult;

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
    protected WordLengthResult getActualLength(int beginIndex, InnerSensitiveWordContext innerContext) {
        final String txt = innerContext.originalText();
        final Map<Character, Character> formatCharMapping = innerContext.formatCharMapping();
        final IWordContext context = innerContext.wordContext();
        final IWordData wordData = context.wordData();
        final IWordData wordDataAllow = context.wordDataAllow();
        final ISensitiveWordCharIgnore wordCharIgnore = context.charIgnore();
        final boolean failFast = context.wordFailFast();

        StringBuilder stringBuilder = new StringBuilder();
        char[] rawChars = txt.toCharArray();
        int tempLen = 0;
        int maxWhite = 0;
        int maxBlack = 0;
        int skipLen = 0;

        for (int i = beginIndex; i < rawChars.length; i++) {
            if (wordCharIgnore.ignore(i, rawChars, innerContext) && tempLen != 0) {
                tempLen++;
                skipLen++;
                continue;
            }
            char mappingChar = formatCharMapping.get(rawChars[i]);
            stringBuilder.append(mappingChar);
            tempLen++;

            WordContainsTypeEnum wordContainsTypeEnumAllow = wordDataAllow.contains(stringBuilder, innerContext);
            WordContainsTypeEnum wordContainsTypeEnumDeny = wordData.contains(stringBuilder, innerContext);

            if (WordContainsTypeEnum.CONTAINS_END.equals(wordContainsTypeEnumAllow)) {
                maxWhite = tempLen;
                if (failFast) {
                    //为falFast模式，主动设为notFound退出循环
                    wordContainsTypeEnumAllow = WordContainsTypeEnum.NOT_FOUND;
                }
            }

            if (WordContainsTypeEnum.CONTAINS_END.equals(wordContainsTypeEnumDeny)) {
                maxBlack = tempLen;
                if (failFast) {
                    //为falFast模式，主动设为notFound退出循环
                    wordContainsTypeEnumDeny = WordContainsTypeEnum.NOT_FOUND;
                }
            }

            if (WordContainsTypeEnum.NOT_FOUND.equals(wordContainsTypeEnumAllow) &&
                    WordContainsTypeEnum.NOT_FOUND.equals(wordContainsTypeEnumDeny)) {
                break;
            }
        }

        String string = stringBuilder.toString();
        String wordAllow = string.substring(0, Math.max(0, maxWhite - skipLen));
        String wordDeny = string.substring(0, Math.max(0, maxBlack - skipLen));


        return WordLengthResult.newInstance()
                .wordAllowLen(maxWhite)
                .wordDenyLen(maxBlack)
                .wordAllow(wordAllow)
                .wordDeny(wordDeny);
    }


    @Override
    protected String getType() {
        return WordTypeEnum.WORD.getCode();
    }

}
