package com.github.houbb.sensitive.word.support.resultcondition;

import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.util.CharsetUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;

/**
 * 英文单词必须要全词匹配
 *
 * https://github.com/houbb/sensitive-word/issues/45
 *
 * @since 0.13.0
 */
public class WordResultConditionEnglishWordMatch extends AbstractWordResultCondition {

    @Override
    protected boolean doMatch(IWordResult wordResult, String text, WordValidModeEnum modeEnum, IWordContext context) {
        final int startIndex = wordResult.startIndex();
        final int endIndex = wordResult.endIndex();
        // 判断处理，判断前一个字符是否为英文。如果是，则不满足
        if(startIndex > 0) {
            char preC = text.charAt(startIndex-1);
            if(CharUtil.isEnglish(preC)) {
                return false;
            }
        }

        // 判断后一个字符是否为英文
        if(endIndex < text.length() - 1) {
            char afterC = text.charAt(endIndex);
            if(CharUtil.isEnglish(afterC)) {
                return false;
            }
        }
        // 判断当前是否为英文单词
        for(int i = startIndex; i < endIndex; i++) {
            char c = text.charAt(i);
            if(!CharUtil.isEnglish(c)) {
                return true;
            }
        }

        

        return true;
    }


}
