package com.github.houbb.sensitive.word.api;

import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;

/**
 * 是否忽略某一个字符
 * @since 0.11.0
 */
public interface ISensitiveWordCharIgnore {

    /**
     * 是否忽略当前字符
     * @param ix 下标志
     * @param chars 字符数组
     * @param innerContext 上下文
     * @return 结果
     */
    boolean ignore(final int ix,
                   final char[] chars,
                   InnerSensitiveWordContext innerContext);

}
