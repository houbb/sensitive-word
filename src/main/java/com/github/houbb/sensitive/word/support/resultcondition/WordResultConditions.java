package com.github.houbb.sensitive.word.support.resultcondition;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.sensitive.word.api.IWordResultCondition;

import java.util.List;

/**
 * 匹配结果工具类
 *
 * @since 0.13.0
 */
public final class WordResultConditions {

    /**
     * 恒为真
     * @return 结果
     */
    public static IWordResultCondition alwaysTrue() {
        return new WordResultConditionAlwaysTrue();
    }

    /**
     * 如果是英文，则必须全词匹匹配
     * @return 结果
     * @since 0.13.0
     */
    public static IWordResultCondition englishWordMatch() {
        return new WordResultConditionEnglishWordMatch();
    }

    /**
     * 如果是英文或者数字，则必须全词匹匹配
     * @return 结果
     * @since 0.20.0
     */
    public static IWordResultCondition englishWordNumMatch() {
        return new WordResultConditionEnglishWordNumMatch();
    }

    /**
     * 单词标签
     * @param tags 标签列表
     * @return 结果
     * @since 0.23.0
     */
    public static IWordResultCondition wordTags(List<String> tags) {
        ArgUtil.notEmpty(tags, "tags");

        return new WordResultConditionWordTagsMatch(tags);
    }

    /**
     * 链式调用，支持同时满足多个条件
     *
     * @since 0.23.0
     * @param condition 条件
     * @param others 其他条件
     * @return 结果
     */
    public static IWordResultCondition chains(final IWordResultCondition condition, final IWordResultCondition ... others) {
        return new WordResultConditionInit() {
            @Override
            protected void init(Pipeline<IWordResultCondition> pipeline) {
                pipeline.addLast(condition);
                if(ArrayUtil.isNotEmpty(others)) {
                    for(IWordResultCondition other : others) {
                        pipeline.addLast(other);
                    }
                }
            }
        };
    }


}
