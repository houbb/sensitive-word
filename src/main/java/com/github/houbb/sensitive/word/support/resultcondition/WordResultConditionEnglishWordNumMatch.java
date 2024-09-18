package com.github.houbb.sensitive.word.support.resultcondition;

import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;

/**
 * 英文单词和数字必须要全词匹配
 *
 * https://github.com/houbb/sensitive-word/issues/77
 *
 * @since 0.20.0
 */
public class WordResultConditionEnglishWordNumMatch extends AbstractWordResultCondition {

    @Override
    protected boolean doMatch(IWordResult wordResult, String text, WordValidModeEnum modeEnum, IWordContext context) {
        final int startIndex = wordResult.startIndex();
        final int endIndex = wordResult.endIndex();
        // 判断处理，判断前一个字符是否为英文。如果是，则不满足
        if(startIndex > 0) {
            char preC = text.charAt(startIndex-1);
            if(CharUtil.isDigitOrLetter(preC)) {
                return false;
            }
        }

        // 判断后一个字符是否为英文
        // v0.19.1 修正 cp cpm 单个字符错误命中问题
        if(endIndex < text.length()) {
            char afterC = text.charAt(endIndex);
            if(CharUtil.isDigitOrLetter(afterC)) {
                return false;
            }
        }

        // 判断当前是否为英文单词
        for(int i = startIndex; i < endIndex; i++) {
            char c = text.charAt(i);
            if(!CharUtil.isDigitOrLetter(c)) {
                return true;
            }
        }

        return true;
    }


}
