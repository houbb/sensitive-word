package com.github.houbb.sensitive.word.constant.enums;

/**
 * 单词类别包含类别
 * @since 0.14.0
 */
public enum WordTypeEnum {
    WORD("WORD", "敏感词"),
    EMAIL("EMAIL", "邮箱"),
    URL("URL", "链接"),
    NUM("NUM", "数字"),
    IPV4("IPV4", "IPv4"),

    DEFAULTS("DEFAULTS", "默认"),
    ;

    private final String code;
    private final String desc;

    WordTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
