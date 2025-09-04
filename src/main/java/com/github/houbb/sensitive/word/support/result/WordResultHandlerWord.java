package com.github.houbb.sensitive.word.support.result;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.utils.InnerWordCharUtils;

/**
 * 只保留单词
 *
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class WordResultHandlerWord extends AbstractWordResultHandler<String> {

    /**
     * @since 0.3.0
     */
    private static final WordResultHandlerWord INSTANCE = new WordResultHandlerWord();

    public static WordResultHandlerWord getInstance() {
        return INSTANCE;
    }

    @Override
    protected String doHandle(IWordResult wordResult, IWordContext wordContext, String originalText) {
        // 截取
        return InnerWordCharUtils.getString(originalText, wordResult);
    }

}
