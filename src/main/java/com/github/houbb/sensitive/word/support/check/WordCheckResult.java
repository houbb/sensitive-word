package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.support.result.WordLengthResult;

/**
 * 敏感信息监测接口结果
 *
 * 可以使用责任链的模式，循环调用。
 * @author binbin.hou
 * @since 0.0.12
 */
public class WordCheckResult {

    /**
     * 命中的黑白名单的长度对象
     */
    private WordLengthResult wordLengthResult;

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

    public WordLengthResult wordLengthResult() {
        return wordLengthResult;
    }

    public WordCheckResult wordLengthResult(WordLengthResult wordLengthResult) {
        this.wordLengthResult = wordLengthResult;
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
                "wordLengthResult=" + wordLengthResult +
                ", checkClass=" + checkClass +
                ", type='" + type + '\'' +
                '}';
    }

}
