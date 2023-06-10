package com.github.houbb.sensitive.word.support.combine.format;

import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.combine.IWordFormatCombine;
import com.github.houbb.sensitive.word.support.format.WordFormats;

import java.util.List;

/**
 * @author d
 * @since 0.8.0
 */
public abstract class AbstractWordFormatCombine implements IWordFormatCombine {

    protected abstract List<IWordFormat> getWordFormatList(IWordContext context);

    @Override
    public IWordFormat initWordFormat(IWordContext context) {
        List<IWordFormat> list = getWordFormatList(context);
        return WordFormats.chains(list);
    }

}
