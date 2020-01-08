package com.github.houbb.sensitive.word.utils;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;

import java.util.Map;

/**
 * <p> project: sensitive-word-NumUtils </p>
 * <p> create on 2020/1/8 22:18 </p>
 *
 * @author Administrator
 * @since 0.0.4
 */
public final class NumUtils {

    private NumUtils(){}

    private static final String NUM_ONE = "⓪０零º₀⓿○" +
            "１２３４５６７８９" +
            "一二三四五六七八九" +
            "壹贰叁肆伍陆柒捌玖" +
            "¹²³⁴⁵⁶⁷⁸⁹" +
            "₁₂₃₄₅₆₇₈₉" +
            "①②③④⑤⑥⑦⑧⑨" +
            "⑴⑵⑶⑷⑸⑹⑺⑻⑼" +
            "⒈⒉⒊⒋⒌⒍⒎⒏⒐" +
            "❶❷❸❹❺❻❼❽❾" +
            "➀➁➂➃➄➅➆➇➈" +
            "➊➋➌➍➎➏➐➑➒" +
            "㈠㈡㈢㈣㈤㈥㈦㈧㈨" +
            "⓵⓶⓷⓸⓹⓺⓻⓼⓽" +
            "㊀㊁㊂㊃㊄㊅㊆㊇㊈" +
            "ⅰⅱⅲⅳⅴⅵⅶⅷⅸ" +
            "ⅠⅡⅢⅣⅤⅥⅦⅧⅨ";

    private static final String NUM_TWO = "0000000"+
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789" +
            "123456789";

    /**
     * 英文字母 map
     * @since 0.0.4
     */
    private static final Map<Character, Character> NUMBER_MAP = Guavas.newHashMap(NUM_ONE.length());

    static {
        final int size = NUM_ONE.length();

        for(int i = 0; i < size; i++) {
            NUMBER_MAP.put(NUM_ONE.charAt(i), NUM_TWO.charAt(i));
        }
    }

    /**
     * 映射后的 char
     * @param character 待转换的 char
     * @return 结果
     * @since 0.0.4
     */
    public static Character getMappingChar(final Character character) {
        final Character mapChar = NUMBER_MAP.get(character);
        if(ObjectUtil.isNotNull(mapChar)) {
            return mapChar;
        }

        return character;
    }

    public static String getMappingString(final String string) {
        if(StringUtil.isEmpty(string)) {
            return string;
        }

        char[] chars = string.toCharArray();
        StringBuilder stringBuilder = new StringBuilder(chars.length);
        for(char c : chars) {
            char mapChar = getMappingChar(c);

            //TODO: stop word 的处理
            stringBuilder.append(mapChar);
        }

        return stringBuilder.toString();
    }

}
