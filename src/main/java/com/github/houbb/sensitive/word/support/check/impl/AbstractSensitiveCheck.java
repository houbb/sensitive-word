package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
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
     * @param txt 文本
     * @param beginIndex 开始
     * @param validModeEnum 校验枚举
     * @param context 上下文
     * @return 长度
     * @since 0.4.0
     */
    protected abstract int doGetActualLength(String txt, int beginIndex,
                                             ValidModeEnum validModeEnum,
                                             IWordContext context);

    /**
     * 获取确切的长度
     * @param txt 文本
     * @param beginIndex 开始
     * @param validModeEnum 校验枚举
     * @param context 上下文
     * @return 长度
     * @since 0.4.0
     */
    protected int getActualLength(String txt, int beginIndex,
                                           ValidModeEnum validModeEnum,
                                           IWordContext context) {
        if(StringUtil.isEmpty(txt)) {
            return 0;
        }

        return doGetActualLength(txt, beginIndex, validModeEnum, context);
    }

    @Override
    public SensitiveCheckResult sensitiveCheck(String txt, int beginIndex,
                                               ValidModeEnum validModeEnum,
                                               IWordContext context) {
        Class<? extends ISensitiveCheck> clazz = getSensitiveCheckClass();
        if(StringUtil.isEmpty(txt)) {
            return SensitiveCheckResult.of(0, clazz);
        }
        int actualLength = getActualLength(txt, beginIndex, validModeEnum, context);

        return SensitiveCheckResult.of(actualLength, clazz);
    }

}
