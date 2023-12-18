package com.github.houbb.sensitive.word.support.result;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;

/**
 * 不做任何处理
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class WordResultHandlerRaw extends AbstractWordResultHandler<IWordResult> {

    /**
     * @since 0.3.0
     */
    private static final WordResultHandlerRaw INSTANCE = new WordResultHandlerRaw();

    public static WordResultHandlerRaw getInstance() {
        return INSTANCE;
    }

    @Override
    protected IWordResult doHandle(IWordResult wordResult, IWordContext wordContext, String originalText) {
        return wordResult;
    }

}
