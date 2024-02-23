package com.github.houbb.sensitive.word.support.resultcondition;

import com.github.houbb.sensitive.word.api.IWordResultCondition;

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

}
