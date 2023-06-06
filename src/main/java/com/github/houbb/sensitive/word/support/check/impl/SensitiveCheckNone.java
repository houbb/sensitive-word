package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;
import com.github.houbb.sensitive.word.support.check.SensitiveCheckResult;

/**
 * 未匹配
 *
 * @author binbin.hou
 * @since 0.3.0
 */
@ThreadSafe
public class SensitiveCheckNone implements ISensitiveCheck {

    /**
     * @since 0.3.0
     */
    private static final ISensitiveCheck INSTANCE = new SensitiveCheckNone();

    public static ISensitiveCheck getInstance() {
        return INSTANCE;
    }

    /**
     * 只有一个未匹配
     */
    private static final SensitiveCheckResult NONE_RESULT = SensitiveCheckResult.of(0, SensitiveCheckNone.class);

    @Override
    public SensitiveCheckResult sensitiveCheck(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        return NONE_RESULT;
    }

    public static SensitiveCheckResult getNoneResult() {
        return NONE_RESULT;
    }

}
