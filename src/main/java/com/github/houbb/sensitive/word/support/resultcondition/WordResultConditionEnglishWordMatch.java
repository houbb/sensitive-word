package com.github.houbb.sensitive.word.support.resultcondition;

import com.github.houbb.heaven.util.lang.CharUtil;
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

        // 检查匹配的文本是否全为英文（可以包含空格）
        // 如果包含中文、数字等非英文字符（除了空格），直接返回 true（不需要检查边界）
        for (int i = startIndex; i < endIndex; i++) {
            char c = text.charAt(i);
            if (!CharUtil.isEnglish(c) && !CharUtil.isSpace(c)) {
                // 包含非英文字符（如中文、数字），直接返回 true
                return true;
            }
        }

        // 全英文敏感词（可能包含空格）：检查前后字符是否为英文（需要全词匹配）
        if (startIndex > 0 && CharUtil.isEnglish(text.charAt(startIndex - 1))) {
            return false;
        }
        if (endIndex < text.length() && CharUtil.isEnglish(text.charAt(endIndex))) {
            return false;
        }

        return true;
    }

}
