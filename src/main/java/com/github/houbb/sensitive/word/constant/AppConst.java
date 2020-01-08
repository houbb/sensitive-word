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
     * 字典的大小
     * @since 0.0.1
     */
    public static final int DICT_SIZE = 66337;

}
