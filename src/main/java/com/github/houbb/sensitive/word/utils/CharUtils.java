package com.github.houbb.sensitive.word.utils;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;

import java.util.Map;

/**
 * <p> project: sensitive-word-NumUtils </p>
 * <p> create on 2020/1/8 22:18 </p>
 *
 * @author Administrator
 * @since 0.0.4
 */
public final class CharUtils {

    private CharUtils() {
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
     * 英文字母 map
     * @since 0.0.4
     */
    private static final Map<Character, Character> LETTER_MAP = Guavas.newHashMap(LETTERS_ONE.length());

    static {
        final int size = LETTERS_ONE.length();

        for(int i = 0; i < size; i++) {
            LETTER_MAP.put(LETTERS_ONE.charAt(i), LETTERS_TWO.charAt(i));
        }
    }

    /**
     * 映射后的 char
     * @param character 待转换的 char
     * @return 结果
     * @since 0.0.4
     */
    public static Character getMappingChar(final Character character) {
        final Character mapChar = LETTER_MAP.get(character);
        if(ObjectUtil.isNotNull(mapChar)) {
            return mapChar;
        }

        return character;
    }

}
