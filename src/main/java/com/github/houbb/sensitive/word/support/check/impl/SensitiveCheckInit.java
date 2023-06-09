package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveContext;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;
import com.github.houbb.sensitive.word.support.check.SensitiveCheckResult;

import java.util.List;

/**
 * 检测初始化类
 * @since 0.3.0
 */
public abstract class SensitiveCheckInit implements ISensitiveCheck {

    /**
     * 初始化列表
     *
     * @param pipeline 当前列表泳道
     * @since 0.0.13
     */
    protected abstract void init(final Pipeline<ISensitiveCheck> pipeline);


    @Override
    public SensitiveCheckResult sensitiveCheck(final int beginIndex,
                                               final InnerSensitiveContext checkContext) {

        Pipeline<ISensitiveCheck> pipeline = new DefaultPipeline<>();
        this.init(pipeline);
        List<ISensitiveCheck> sensitiveChecks = pipeline.list();

        // 循环调用
        for(ISensitiveCheck sensitiveCheck : sensitiveChecks) {
            SensitiveCheckResult result = sensitiveCheck.sensitiveCheck(beginIndex, checkContext);

            if(result.index() > 0) {
                return result;
            }
        }

        // 这里直接进行正则表达式相关的调用。
        // 默认返回 0
        return SensitiveCheckNone.getNoneResult();
    }

}
