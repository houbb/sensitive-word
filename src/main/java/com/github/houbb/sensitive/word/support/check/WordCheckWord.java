package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.ISensitiveWordCharIgnore;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordTypeEnum;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;
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

        // 前一个条件
        StringBuilder stringBuilder = new StringBuilder();
        char[] rawChars = txt.toCharArray();

        int tempLen = 0;
        int maxWhite = 0;
        int maxBlack = 0;
        boolean firstCheck = true;

        WordContainsTypeEnum wordContainsTypeEnumAllow = wordDataAllow.contains(stringBuilder, innerContext);
        WordContainsTypeEnum wordContainsTypeEnumDeny = wordData.contains(stringBuilder, innerContext);

        for (int i = beginIndex; i < rawChars.length; i++) {
            if (wordCharIgnore.ignore(i, rawChars, innerContext) && tempLen != 0) {
                tempLen++;
                continue;
            }

            char mappingChar = formatCharMapping.get(rawChars[i]);
            stringBuilder.append(mappingChar);
            tempLen++;

            if (firstCheck || !WordContainsTypeEnum.NOT_FOUND.equals(wordContainsTypeEnumAllow)) {
                wordContainsTypeEnumAllow = wordDataAllow.contains(stringBuilder, innerContext);
                if (WordContainsTypeEnum.CONTAINS_END.equals(wordContainsTypeEnumAllow)) {
                    maxWhite += tempLen;
                    wordContainsTypeEnumAllow = WordContainsTypeEnum.NOT_FOUND;
                }
            }

            if (firstCheck || !WordContainsTypeEnum.NOT_FOUND.equals(wordContainsTypeEnumDeny)) {
                wordContainsTypeEnumDeny = wordData.contains(stringBuilder, innerContext);
                if (WordContainsTypeEnum.CONTAINS_END.equals(wordContainsTypeEnumDeny)) {
                    maxBlack += tempLen;
                    wordContainsTypeEnumDeny = WordContainsTypeEnum.NOT_FOUND;
                }
            }

            firstCheck = false;

            if (WordContainsTypeEnum.NOT_FOUND.equals(wordContainsTypeEnumAllow) &&
                    WordContainsTypeEnum.NOT_FOUND.equals(wordContainsTypeEnumDeny)) {
                break;
            }
        }

        return WordLengthResult.newInstance()
                .wordAllowLen(maxWhite)
                .wordDenyLen(maxBlack);
    }

    @Override
    protected String getType() {
        return WordTypeEnum.WORD.getCode();
    }

}
