package com.github.houbb.sensitive.word.utils;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;

import java.util.Map;

/**
 * <p> project: sensitive-word-NumUtils </p>
 * <p> create on 2020/1/8 22:18 </p>
 *
 * @author Administrator
 * @since 0.0.4
 */
public final class InnerNumUtils {

    private InnerNumUtils(){}

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

    /**
     * 检查敏感词数量
     * <p>
     * （1）如果未命中敏感词，直接返回 0
     * （2）命中敏感词，则返回敏感词的长度。
     *
     * ps: 这里结果进行优化，
     * 1. 是否包含敏感词。
     * 2. 敏感词的长度
     * 3. 正常走过字段的长度（便于后期替换优化，避免不必要的循环重复）
     *
     * @param txt           文本信息
     * @param beginIndex    开始下标
     * @param validModeEnum 验证模式
     * @param context 执行上下文
     * @return 敏感数字对应的长度
     * @since 0.0.5
     */
    private int getSensitiveNumber(final String txt, final int beginIndex,
                                   final ValidModeEnum validModeEnum,
                                   final IWordContext context) {
        return 0;
    }

}
