package com.github.houbb.sensitive.word.utils;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

import java.util.*;

/**
 * 内部格式化工具类
 * @since 0.1.1
 */
public final class InnerWordFormatUtils {

    private InnerWordFormatUtils(){}

    /**
     * 空字符数组
     * @since 0.6.0
     */
    private static final char[] EMPTY_CHARS = new char[0];

    /**
     * 格式化
     * @param original 原始
     * @param context 上下文
     * @return 结果
     * @since 0.1.1
     */
    public static String format(final String original, final IWordContext context) {
        if(StringUtil.isEmpty(original)) {
            return original;
        }

        StringBuilder stringBuilder = new StringBuilder();
        IWordFormat charFormat = context.wordFormat();
        char[] chars = original.toCharArray();
        for(char c : chars) {
            char cf = charFormat.format(c, context);
            stringBuilder.append(cf);
        }

        return stringBuilder.toString();
    }

    /**
     * 字符串统一的格式化处理
     * @param original 原始文本
     * @param context 上下文
     * @return 结果
     * @since 0.6.0
     */
    public static Map<Character, Character> formatCharsMapping(final String original, final IWordContext context) {
        if(StringUtil.isEmpty(original)) {
            return Collections.emptyMap();
        }

        final int len = original.length();

        char[] rawChars = original.toCharArray();
        Map<Character, Character> map = new HashMap<>(rawChars.length);

        IWordFormat charFormat = context.wordFormat();
        for(int i = 0; i < len; i++) {
            final char currentChar = rawChars[i];
            char formatChar = charFormat.format(currentChar, context);
            map.put(currentChar, formatChar);
        }

        return map;
    }

    /**
     * 格式化列表
     * @param list 列表
     * @param context 上下文
     * @return 结果
     * @since 0。3.0
     */
    public static List<String> formatWordList(Collection<String> list,
                                              final IWordContext context) {
        if(CollectionUtil.isEmpty(list)) {
            return new ArrayList<>();
        }

        List<String> resultList = new ArrayList<>(list.size());
        for(String word : list) {
            String formatWord = InnerWordFormatUtils.format(word, context);
            resultList.add(formatWord);
        }

        return resultList;
    }

}
