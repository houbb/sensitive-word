package com.github.houbb.sensitive.word.utils;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.ICharFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 内部格式化工具类
 * @since 0.1.1
 */
public final class InnerFormatUtils {

    private InnerFormatUtils(){}

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
        ICharFormat charFormat = context.charFormat();
        char[] chars = original.toCharArray();
        for(char c : chars) {
            char cf = charFormat.format(c, context);
            stringBuilder.append(cf);
        }

        return stringBuilder.toString();
    }

    /**
     * 格式化列表
     * @param list 列表
     * @param context 上下文
     * @return 结果
     * @since 0。3.0
     */
    public static List<String> formatWordList(List<String> list,
                                              final IWordContext context) {
        if(CollectionUtil.isEmpty(list)) {
            return list;
        }

        List<String> resultList = new ArrayList<>(list.size());
        for(String word : list) {
            String formatWord = InnerFormatUtils.format(word, context);
            resultList.add(formatWord);
        }

        return resultList;
    }

}
