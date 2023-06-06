package com.github.houbb.sensitive.word.support.replace;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.constant.CharConst;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.sensitive.word.api.ISensitiveWordReplace;
import com.github.houbb.sensitive.word.api.ISensitiveWordReplaceContext;

/**
 * 指定字符的替换策略
 * @author binbin.hou
 * @since 0.2.0
 */
@ThreadSafe
public class SensitiveWordReplaceChar implements ISensitiveWordReplace {

    /**
     * 替换的字符
     * @since 0.3.0
     */
    private final char replaceChar;

    public SensitiveWordReplaceChar(char replaceChar) {
        this.replaceChar = replaceChar;
    }

    public SensitiveWordReplaceChar() {
        this(CharConst.STAR);
    }

    @Override
    public String replace(ISensitiveWordReplaceContext context) {
        int wordLength = context.wordLength();

        return CharUtil.repeat(replaceChar, wordLength);
    }

}
