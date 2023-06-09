package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveContext;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;
import com.github.houbb.sensitive.word.support.check.SensitiveCheckResult;

/**
 * 抽象实现策略
 *
 * @author binbin.hou
 * @since 0.4.0
 */
@ThreadSafe
public abstract class AbstractSensitiveCheck implements ISensitiveCheck {

    /**
     * 获取校验类
     * @return 类
     * @since 0.3.2
     */
    protected abstract Class<? extends ISensitiveCheck> getSensitiveCheckClass();

    /**
     * 获取确切的长度
     * @param beginIndex 开始
     * @param checkContext 上下文
     * @return 长度
     * @since 0.4.0
     */
    protected abstract int getActualLength(int beginIndex, final InnerSensitiveContext checkContext);

    @Override
    public SensitiveCheckResult sensitiveCheck(int beginIndex,
                                               final InnerSensitiveContext checkContext) {
        Class<? extends ISensitiveCheck> clazz = getSensitiveCheckClass();
        final String txt = checkContext.originalText();
        if(StringUtil.isEmpty(txt)) {
            return SensitiveCheckResult.of(0, clazz);
        }

        int actualLength = getActualLength(beginIndex, checkContext);

        return SensitiveCheckResult.of(actualLength, clazz);
    }

}
