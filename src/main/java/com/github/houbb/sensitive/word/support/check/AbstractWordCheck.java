package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.support.result.WordLengthResult;

/**
 * 抽象实现策略
 *
 * @author binbin.hou
 * @since 0.4.0
 */
@ThreadSafe
public abstract class AbstractWordCheck implements IWordCheck {

    /**
     * 获取校验类
     * @return 类
     * @since 0.3.2
     */
    protected abstract Class<? extends IWordCheck> getSensitiveCheckClass();

    /**
     * 获取确切的长度
     * @param beginIndex 开始
     * @param checkContext 上下文
     * @return 长度
     * @since 0.4.0
     */
    protected abstract WordLengthResult getActualLength(int beginIndex, final InnerSensitiveWordContext checkContext);

    /**
     * 获取类别
     * @return 类别
     * @since 0.14.0
     */
    protected abstract String getType();

    @Override
    public WordCheckResult sensitiveCheck(int beginIndex,
                                          final InnerSensitiveWordContext checkContext) {
        Class<? extends IWordCheck> clazz = getSensitiveCheckClass();
        final String txt = checkContext.originalText();
        WordLengthResult wordLengthResult = WordLengthResult.newInstance()
                .wordAllowLen(0)
                .wordDenyLen(0);

        if(StringUtil.isEmpty(txt)) {
            return WordCheckResult.newInstance()
                    .wordLengthResult(wordLengthResult)
                    .type(getType())
                    .checkClass(clazz);
        }

        wordLengthResult  = getActualLength(beginIndex, checkContext);

        return WordCheckResult.newInstance()
                .wordLengthResult(wordLengthResult)
                .type(getType())
                .checkClass(clazz);
    }

}
