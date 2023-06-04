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
     * 字典的大小
     * @since 0.0.1
     */
    public static final int DICT_SIZE = 65275;

    /**
     * 英语词典的大小
     * @since 0.0.4
     */
    public static final int DICT_EN_SIZE = 12;

    /**
     * 拒绝的词语
     * @since 0.0.8
     */
    public static final String SENSITIVE_WORD_DENY_PATH = "/sensitive_word_deny.txt";

    /**
     * 用户允许的词语
     * @since 0.0.8
     */
    public static final String SENSITIVE_WORD_ALLOW_PATH = "/sensitive_word_allow.txt";

}
