package com.github.houbb.sensitive.word.support.resultcondition;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.api.IWordResultCondition;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;

import java.util.List;

/**
 * 结果条件的的初始化类
 *
 * @since 0.23.0
 */
public abstract class WordResultConditionInit extends AbstractWordResultCondition {

    /**
     * 初始化列表
     *
     * @param pipeline 当前列表泳道
     * @since 0.0.13
     */
    protected abstract void init(final Pipeline<IWordResultCondition> pipeline);

    @Override
    protected boolean doMatch(IWordResult wordResult, String text, WordValidModeEnum modeEnum, IWordContext context) {
        Pipeline<IWordResultCondition> pipeline = new DefaultPipeline<>();
        this.init(pipeline);
        List<IWordResultCondition> conditionList = pipeline.list();

        // 必须满足所有
        for(IWordResultCondition wordResultCondition : conditionList) {
            if(!wordResultCondition.match(wordResult, text, modeEnum, context)) {
                return false;
            }
        }

        return true;
    }

}
