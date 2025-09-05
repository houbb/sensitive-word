package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordFormat;

import java.util.List;

/**
 * 直接列表调用
 * @author binbin.hou
 * @since 0.30.0
 */
public class WordFormatArray implements IWordFormat {

    private final IWordFormat[] wordFormats;
    private final int size;
    public WordFormatArray(List<IWordFormat> wordFormats) {
        ArgUtil.notEmpty(wordFormats, "wordFormats");

        this.size = wordFormats.size();
        this.wordFormats = new IWordFormat[size];
        for(int i = 0; i < size; i++) {
            this.wordFormats[i] = wordFormats.get(i);
        }
    }

    @Override
    public char format(char original, IWordContext context) {
        char c = original;
        for(int i = 0; i < size; i++) {
            IWordFormat charFormat = wordFormats[i];
            c = charFormat.format(c, context);
        }

        return c;
    }

}
