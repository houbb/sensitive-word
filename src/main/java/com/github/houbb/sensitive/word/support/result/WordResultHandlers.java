package com.github.houbb.sensitive.word.support.result;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.api.IWordResultHandler;

/**
 * 敏感词的结果处理
 * @author binbin.hou
 * @since 0.1.0
 */
public final class WordResultHandlers {

    private WordResultHandlers(){}

    /**
     * 不做任何处理
     * @return 结果
     * @since 0.1.0
     */
    public static IWordResultHandler<IWordResult> raw() {
        return Instances.singleton(WordResultHandlerRaw.class);
    }

    /**
     * 只保留单词
     * @return 结果
     * @since 0.1.0
     */
    public static IWordResultHandler<String> word() {
        return Instances.singleton(WordResultHandlerWord.class);
    }

}
