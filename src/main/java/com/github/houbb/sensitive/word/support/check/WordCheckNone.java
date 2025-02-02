package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordTypeEnum;
import com.github.houbb.sensitive.word.support.result.WordLengthResult;

/**
 * 未匹配
 *
 * @author binbin.hou
 * @since 0.3.0
 */
@ThreadSafe
public class WordCheckNone implements IWordCheck {

    /**
     * @since 0.3.0
     */
    private static final IWordCheck INSTANCE = new WordCheckNone();

    public static IWordCheck getInstance() {
        return INSTANCE;
    }

    /**
     * 只有一个未匹配
     */
    private static final WordCheckResult NONE_RESULT = WordCheckResult.newInstance()
            .type(WordTypeEnum.DEFAULTS.getCode())
            .wordLengthResult(WordLengthResult.newInstance())
            .checkClass(WordCheckNone.class);

    public static WordCheckResult getNoneResult() {
        return NONE_RESULT;
    }

    @Override
    public WordCheckResult sensitiveCheck(int beginIndex, InnerSensitiveWordContext context) {
        return NONE_RESULT;
    }
}
