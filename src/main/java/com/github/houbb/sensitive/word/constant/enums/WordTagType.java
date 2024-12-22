package com.github.houbb.sensitive.word.constant.enums;

/**
 * 单词标签类别
 *
 * @since 0.24.0
 */
public enum WordTagType {
    ZHENGZHI("0", "政治"),
    DUPIN("1", "毒品"),
    SEQING("2", "色情"),
    DUBO("3", "赌博"),
    FANZUI("4", "违法犯罪"),
    ;

    private final String code;
    private final String desc;

    WordTagType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode(final String code) {
        for(WordTagType tagType : WordTagType.values()) {
            if(tagType.code.equals(code)) {
                return tagType.desc;
            }
        }
        return code;
    }

}
