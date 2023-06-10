package com.github.houbb.sensitive.word.api.combine;

import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

/**
 * @author d
 * @since 0.8.0
 */
public interface IWordFormatCombine {

    /**
     * 初始化 charFormat
     * @param context 上下文
     * @return 结果
     * @since 0.8.0
     */
    IWordFormat initWordFormat(final IWordContext context);

}
