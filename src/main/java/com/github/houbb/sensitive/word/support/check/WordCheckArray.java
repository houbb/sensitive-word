package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.support.result.WordLengthResult;

import java.util.List;

/**
 * 集合
 * @author binbin.hou
 * @since 0.30.0
 */
public class WordCheckArray implements IWordCheck {

    private final IWordCheck[] sensitiveChecks;
    private final int size;
    public WordCheckArray(List<IWordCheck> sensitiveChecks) {
        ArgUtil.notEmpty(sensitiveChecks, "sensitiveChecks");

        this.size = sensitiveChecks.size();
        this.sensitiveChecks = new IWordCheck[size];
        for(int i = 0; i < size; i++) {
            this.sensitiveChecks[i] = sensitiveChecks.get(i);
        }
    }

    @Override
    public WordCheckResult sensitiveCheck(int beginIndex, InnerSensitiveWordContext checkContext) {
        // 循环调用
        for(int i = 0; i < size; i++) {
            IWordCheck sensitiveCheck = sensitiveChecks[i];
            WordCheckResult result = sensitiveCheck.sensitiveCheck(beginIndex, checkContext);

            WordLengthResult wordLengthResult = result.wordLengthResult();
            if(wordLengthResult.wordAllowLen() > 0 || wordLengthResult.wordDenyLen()> 0) {
                return result;
            }
        }

        // 这里直接进行正则表达式相关的调用。
        // 默认返回 0
        return WordCheckNone.getNoneResult();
    }

}
