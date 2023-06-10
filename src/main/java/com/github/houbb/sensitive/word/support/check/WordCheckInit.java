package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;

import java.util.List;

/**
 * 检测初始化类
 * @since 0.3.0
 */
public abstract class WordCheckInit implements IWordCheck {

    /**
     * 初始化列表
     *
     * @param pipeline 当前列表泳道
     * @since 0.0.13
     */
    protected abstract void init(final Pipeline<IWordCheck> pipeline);


    @Override
    public WordCheckResult sensitiveCheck(final int beginIndex,
                                          final InnerSensitiveWordContext checkContext) {

        Pipeline<IWordCheck> pipeline = new DefaultPipeline<>();
        this.init(pipeline);
        List<IWordCheck> sensitiveChecks = pipeline.list();

        // 循环调用
        for(IWordCheck sensitiveCheck : sensitiveChecks) {
            WordCheckResult result = sensitiveCheck.sensitiveCheck(beginIndex, checkContext);

            if(result.index() > 0) {
                return result;
            }
        }

        // 这里直接进行正则表达式相关的调用。
        // 默认返回 0
        return WordCheckNone.getNoneResult();
    }

}
