package com.github.houbb.sensitive.word.utils;

/**
 * @since 0.17.0
 */
public class InnerCharUtils {

    /**
     * 转换为半角
     * @param original 原始
     * @return 半角
     * @since 0.29.2
     */
    public static char toHalfWidth(char original) {
        // 全角空格
        if (original == '\u3000') return ' ';
        // 其他可转换全角字符
        if (original >= '\uFF01' && original <= '\uFF5E') {
            return (char) (original - 0xFEE0);
        }
        // 其他字符保持不变
        return original;
    }


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
        for(int i = len-1; i >= 0; i--) {
            int val = getCharInt(text.charAt(i));

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
