package com.github.houbb.sensitive.word.support.result;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.api.IWordResultHandler;
import com.github.houbb.sensitive.word.utils.InnerCharUtils;

/**
 * 只保留单词
 *
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class WordResultHandlerWord implements IWordResultHandler<String> {

    /**
     * @since 0.3.0
     */
    private static final WordResultHandlerWord INSTANCE = new WordResultHandlerWord();

    public static WordResultHandlerWord getInstance() {
        return INSTANCE;
    }

    @Override
    public String handle(IWordResult wordResult, IWordContext wordContext, String originalText) {
        if(wordResult == null) {
            return null;
        }

        // 截取
        return InnerCharUtils.getString(originalText.toCharArray(), wordResult);
    }

}
