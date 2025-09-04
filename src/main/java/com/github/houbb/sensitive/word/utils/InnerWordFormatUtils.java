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
     * @param map 映射集合
     * @param c 原始
     * @return 结果
     * @since 0.28.0
     */
    public static char getMappingChar(final Map<Character, Character> map, char c) {
        Character mc = map.get(c);
        if(mc != null) {
            return mc;
        }
        return c;
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
