package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordFormat;

import java.util.ArrayList;
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

        List<IWordFormat> wordFormats = new ArrayList<>(charFormats.length);
        return array(wordFormats);
    }

    /**
     * 链式
     * @param charFormats 列表
     * @return 结果
     */
    public static IWordFormat chains(final List<IWordFormat> charFormats) {
        if(CollectionUtil.isEmpty(charFormats)) {
            return none();
        }

        return array(charFormats);
    }

    public static IWordFormat none() {
        return WordFormatNone.getInstance();
    }
    public static IWordFormat ignoreCase() {
        return WordFormatIgnoreCase.getInstance();
    }

    public static IWordFormat ignoreEnglishStyle() {
        return WordFormatIgnoreEnglishStyleC2C.getInstance();
    }

    public static IWordFormat ignoreChineseStyle() {
        return WordFormatIgnoreChineseStyle.getInstance();
    }

    public static IWordFormat ignoreNumStyle() {
        return WordFormatIgnoreNumStyleC2C.getInstance();
    }

    public static IWordFormat ignoreWidth() {
        return WordFormatIgnoreWidth.getInstance();
    }

    public static IWordFormat array(final List<IWordFormat> wordFormats) {
        return new WordFormatArray(wordFormats);
    }

}
