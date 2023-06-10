package com.github.houbb.sensitive.word.api.combine;

import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.IWordContext;

/**
 * @author d
 * @since 0.8.0
 */
public interface IWordCheckCombine {

    /**
     * 初始化敏感检测策略
     * @param context 上下文
     *
     * @return 实现
     * @since 0.8.0
     */
    IWordCheck initWordCheck(final IWordContext context);

}
