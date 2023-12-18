package com.github.houbb.sensitive.word.support.result;

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
        return WordResultHandlerRaw.getInstance();
    }

    /**
     * 只保留单词
     * @return 结果
     * @since 0.1.0
     */
    public static IWordResultHandler<String> word() {
        return WordResultHandlerWord.getInstance();
    }

    /**
     * 单词+标签的处理结果
     * @return 单词+标签的处理结果
     * @since 0.12.0
     */
    public static IWordResultHandler<WordTagsDto> wordTags() {
        return new WordResultHandlerWordTags();
    }

}
