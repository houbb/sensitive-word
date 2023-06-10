package com.github.houbb.sensitive.word.api.context;

import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;

import java.util.Map;

/**
 * 内部信息上下文
 *
 * @author binbin.hou
 * @since 0.6.0
 */
public class InnerSensitiveWordContext {

    /**
     * 原始文本
     */
    private String originalText;
    /**
     * 格式化后的字符
     */
    private Map<Character, Character> formatCharMapping;
    /**
     * 校验模式
     */
    private WordValidModeEnum modeEnum;
    /**
     * 原始上下文
     */
    private IWordContext wordContext;

    public static InnerSensitiveWordContext newInstance() {
        return new InnerSensitiveWordContext();
    }

    public String originalText() {
        return originalText;
    }

    public InnerSensitiveWordContext originalText(String text) {
        this.originalText = text;
        return this;
    }

    public Map<Character, Character> formatCharMapping() {
        return formatCharMapping;
    }

    public InnerSensitiveWordContext formatCharMapping(Map<Character, Character> formatCharMapping) {
        this.formatCharMapping = formatCharMapping;
        return this;
    }

    public WordValidModeEnum modeEnum() {
        return modeEnum;
    }

    public InnerSensitiveWordContext modeEnum(WordValidModeEnum modeEnum) {
        this.modeEnum = modeEnum;
        return this;
    }

    public IWordContext wordContext() {
        return wordContext;
    }

    public InnerSensitiveWordContext wordContext(IWordContext context) {
        this.wordContext = context;
        return this;
    }
}
