package com.github.houbb.sensitive.word.support.combine.format;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.support.format.WordFormats;

import java.util.List;

/**
 * @author d
 * @since 0.8.0
 */
public class WordFormatCombine extends AbstractWordFormatCombine {

    @Override
    protected List<IWordFormat> getWordFormatList(IWordContext context) {
        List<IWordFormat> charFormats = Guavas.newArrayList();
        if(context.ignoreEnglishStyle()) {
            charFormats.add(WordFormats.ignoreEnglishStyle());
        }
        if(context.ignoreCase()) {
            charFormats.add(WordFormats.ignoreCase());
        }
        if(context.ignoreWidth()) {
            charFormats.add(WordFormats.ignoreWidth());
        }
        if(context.ignoreNumStyle()) {
            charFormats.add(WordFormats.ignoreNumStyle());
        }
        if(context.ignoreChineseStyle()) {
            charFormats.add(WordFormats.ignoreChineseStyle());
        }

        return charFormats;
    }

}
