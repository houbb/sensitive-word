package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.sensitive.word.api.IWordCheck;

/**
 * 敏感信息监测接口结果
 *
 * 可以使用责任链的模式，循环调用。
 * @author binbin.hou
 * @since 0.0.12
 */
public class WordCheckResult {

    /**
     * 下标
     * @since 0.0.12
     */
    private int index;

    /**
     * 检测类
     * @since 0.0.12
     */
    private Class<? extends IWordCheck> checkClass;

    /**
     * 单词类别
     * @since 0.14.0
     */
    private String type;

    private WordCheckResult(){}

    public static WordCheckResult newInstance() {
        return new WordCheckResult();
    }

    public int index() {
        return index;
    }

    public WordCheckResult index(int index) {
        this.index = index;
        return this;
    }

    public Class<? extends IWordCheck> checkClass() {
        return checkClass;
    }

    public WordCheckResult checkClass(Class<? extends IWordCheck> checkClass) {
        this.checkClass = checkClass;
        return this;
    }

    public String type() {
        return type;
    }

    public WordCheckResult type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "WordCheckResult{" +
                "index=" + index +
                ", checkClass=" + checkClass +
                ", type='" + type + '\'' +
                '}';
    }

}
