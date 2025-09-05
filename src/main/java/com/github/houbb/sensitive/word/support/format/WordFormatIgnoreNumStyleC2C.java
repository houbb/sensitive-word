package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.collection.Char2CharMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 忽略数字的样式
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class WordFormatIgnoreNumStyleC2C implements IWordFormat {

    private static final IWordFormat INSTANCE = new WordFormatIgnoreNumStyleC2C();

    public static IWordFormat getInstance() {
        return INSTANCE;
    }

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
            "123456789";

    private static final Char2CharMap NUMBER_MAP = new Char2CharMap(NUM_ONE.length());

    static {
        final int size = NUM_ONE.length();
        for(int i = 0; i < size; i++) {
            NUMBER_MAP.put(NUM_ONE.charAt(i), NUM_TWO.charAt(i));
        }
    }

    /**
     * 映射后的 char
     * @param c 待转换的 char
     * @return 结果
     * @since 0.0.4
     */
    private char getMappingChar(final char c) {
        char mc = NUMBER_MAP.get(c);
        return mc == 0 ? c : mc;
    }

    @Override
    public char format(char original, IWordContext context) {
        return getMappingChar(original);
    }

}
