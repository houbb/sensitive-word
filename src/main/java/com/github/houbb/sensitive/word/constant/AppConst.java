package com.github.houbb.sensitive.word.constant;

/**
 * <p> project: sensitive-word-AppConst </p>
 * <p> create on 2020/1/7 23:39 </p>
 *
 * @author Administrator
 * @since 0.0.1
 */
public final class AppConst {

    private AppConst(){}

    /**
     * 是否为结束标识
     * ps: 某种角度而言，我不是很喜欢这种风格。
     * （1）正常的 char 只會占用一個字符，这里直接给定两个字符即可，降低 Map 的容量。
     * @since 0.0.1
     */
    public static final String IS_END = "ED";

    /**
     * 最长的网址长度
     * @since 0.3.0
     */
    public static final int MAX_WEB_SITE_LEN = 70;

    /**
     * 最大邮箱地址
     * @since 0.4.0
     */
    public static final int MAX_EMAIL_LEN = 64;

}
