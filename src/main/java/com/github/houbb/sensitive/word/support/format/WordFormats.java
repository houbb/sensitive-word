package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

import java.util.Collection;
import java.util.List;

/**
 * 格式化工具类
 * @author binbin.hou
 * @since 0.3.5
 */
public final class WordFormats {

    private WordFormats(){}

    /**
     * 链式
     * @param charFormats 列表
     * @return 结果
     */
    public static IWordFormat chains(final IWordFormat... charFormats) {
        if(ArrayUtil.isEmpty(charFormats)) {
            return none();
        }

        return new WordFormatInit() {
            @Override
            protected void init(Pipeline<IWordFormat> pipeline) {
                for(IWordFormat charFormat : charFormats) {
                    pipeline.addLast(charFormat);
                }
            }
        };
    }

    /**
     * 链式
     * @param charFormats 列表
     * @return 结果
     */
    public static IWordFormat chains(final Collection<IWordFormat> charFormats) {
        if(CollectionUtil.isEmpty(charFormats)) {
            return none();
        }

        return new WordFormatInit() {
            @Override
            protected void init(Pipeline<IWordFormat> pipeline) {
                for(IWordFormat charFormat : charFormats) {
                    pipeline.addLast(charFormat);
                }
            }
        };
    }

    public static IWordFormat none() {
        return WordFormatNone.getInstance();
    }
    public static IWordFormat ignoreCase() {
        return WordFormatIgnoreCase.getInstance();
    }

    public static IWordFormat ignoreEnglishStyle() {
        return WordFormatIgnoreEnglishStyle.getInstance();
    }

    public static IWordFormat ignoreChineseStyle() {
        return WordFormatIgnoreChineseStyle.getInstance();
    }

    public static IWordFormat ignoreNumStyle() {
        return WordFormatIgnoreNumStyle.getInstance();
    }

    public static IWordFormat ignoreWidth() {
        return WordFormatIgnoreWidth.getInstance();
    }

}
