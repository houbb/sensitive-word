package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.collection.Char2CharMap;

/**
 * 忽略英文的各种格式
 * @author binbin.hou
 * @since 0.0.6
 */
@ThreadSafe
public class WordFormatIgnoreEnglishStyleC2C implements IWordFormat {

    private static final IWordFormat INSTANCE = new WordFormatIgnoreEnglishStyleC2C();

    public static IWordFormat getInstance() {
        return INSTANCE;
    }

    /**
     * 英文字母1
     * @since 0.0.4
     */
    private static final String LETTERS_ONE =
            "ⒶⒷⒸⒹⒺⒻⒼⒽⒾⒿⓀⓁⓂⓃⓄⓅⓆⓇⓈⓉⓊⓋⓌⓍⓎⓏ" +
                    "ⓐⓑⓒⓓⓔⓕⓖⓗⓘⓙⓚⓛⓜⓝⓞⓟⓠⓡⓢⓣⓤⓥⓦⓧⓨⓩ" +
                    "⒜⒝⒞⒟⒠⒡⒢⒣⒤⒥⒦⒧⒨⒩⒪⒫⒬⒭⒮⒯⒰⒱⒲⒳⒴⒵";

    /**
     * 英文字母2
     * @since 0.0.4
     */
    private static final String LETTERS_TWO =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "abcdefghijklmnopqrstuvwxyz";


    /**
     * 字母映射表
     */
    private static final Char2CharMap LETTER_MAP = new Char2CharMap(LETTERS_ONE.length());

    static {
        final int size = LETTERS_ONE.length();
        for(int i = 0; i < size; i++) {
            LETTER_MAP.put(LETTERS_ONE.charAt(i), LETTERS_TWO.charAt(i));
        }
    }

    /**
     * 映射后的 char
     * @param c 待转换的 char
     * @return 转换结果
     * @since 0.29.x
     */
    private char getMappingChar(final char c) {
        char mc = LETTER_MAP.get(c);
        return mc == 0 ? c : mc;
    }

    @Override
    public char format(char original, IWordContext context) {
        return getMappingChar(original);
    }

}
