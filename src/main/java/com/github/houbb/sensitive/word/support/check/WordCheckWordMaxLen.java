package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.ISensitiveWordCharIgnore;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordContainsTypeEnum;
import com.github.houbb.sensitive.word.constant.enums.WordTypeEnum;
import com.github.houbb.sensitive.word.support.result.WordLengthResult;

import java.util.Map;

/**
 * 敏感词监测实现
 * @author binbin.hou
 * @since 0.26.0
 */
@Deprecated
public class WordCheckWordMaxLen extends AbstractWordCheck {

    @Override
    protected Class<? extends IWordCheck> getSensitiveCheckClass() {
        return WordCheckWordMaxLen.class;
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

            // 黑名单命中
            if (firstCheck || !WordContainsTypeEnum.NOT_FOUND.equals(wordContainsTypeEnumDeny)) {
                wordContainsTypeEnumDeny = wordData.contains(stringBuilder, innerContext);
                if (WordContainsTypeEnum.CONTAINS_END.equals(wordContainsTypeEnumDeny)) {
                    maxBlack += tempLen;
                    wordContainsTypeEnumDeny = WordContainsTypeEnum.NOT_FOUND;
                }
            }

            // 不再是第一次检测
            firstCheck = false;

            // 黑白名单都未匹配
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
