package com.github.houbb.sensitive.word.utils;

/**
 * @since 0.17.0
 */
public class InnerCharUtils {

    /**
     * 转换为整数
     * @param text 文本
     * @return 整数
     * @since 1.18.0
     */
    public static int parseInt(String text) {
        int len = text.length();

        int sum = 0;

        int weight = 1;
        char[] chars = text.toCharArray();
        for(int i = len-1; i >= 0; i--) {
            int val = getCharInt(chars[i]);

            sum += weight * val;

            weight *= 10;
        }
        return sum;
    }

    /**
     * 获取 int char 对应的真实值
     * @param c 字符
     * @return 结果
     * @since 1.18.0
     */
    public static int getCharInt(final char c) {
        return c - '0';
    }

}
