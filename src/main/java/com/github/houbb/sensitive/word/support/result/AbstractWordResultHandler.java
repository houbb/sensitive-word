package com.github.houbb.sensitive.word.support.result;

import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.api.IWordResultHandler;

/**
 * 抽象的处理结果
 *
 * @since 0.12.0
 * @param <R> 泛型
 */
public abstract class AbstractWordResultHandler<R> implements IWordResultHandler<R> {

    protected abstract R doHandle(IWordResult wordResult, IWordContext wordContext, String originalText);

    @Override
    public R handle(IWordResult wordResult, IWordContext wordContext, String originalText) {
        if(wordResult == null) {
            return null;
        }

        return doHandle(wordResult, wordContext, originalText);
    }

}
