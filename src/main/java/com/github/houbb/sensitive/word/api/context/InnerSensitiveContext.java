package com.github.houbb.sensitive.word.api.context;

import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;

import java.util.Map;

/**
 * 内部信息上下文
 *
 * @author binbin.hou
 * @since 0.6.0
 */
public class InnerSensitiveContext {

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
    private ValidModeEnum modeEnum;
    /**
     * 原始上下文
     */
    private IWordContext wordContext;

    public static InnerSensitiveContext newInstance() {
        return new InnerSensitiveContext();
    }

    public String originalText() {
        return originalText;
    }

    public InnerSensitiveContext originalText(String text) {
        this.originalText = text;
        return this;
    }

    public Map<Character, Character> formatCharMapping() {
        return formatCharMapping;
    }

    public InnerSensitiveContext formatCharMapping(Map<Character, Character> formatCharMapping) {
        this.formatCharMapping = formatCharMapping;
        return this;
    }

    public ValidModeEnum modeEnum() {
        return modeEnum;
    }

    public InnerSensitiveContext modeEnum(ValidModeEnum modeEnum) {
        this.modeEnum = modeEnum;
        return this;
    }

    public IWordContext wordContext() {
        return wordContext;
    }

    public InnerSensitiveContext wordContext(IWordContext context) {
        this.wordContext = context;
        return this;
    }
}
