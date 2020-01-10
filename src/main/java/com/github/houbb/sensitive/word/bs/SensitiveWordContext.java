package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.api.IWordContext;

import java.util.Map;

/**
 * 上下文
 * @author binbin.hou
 * @since 0.0.4
 */
public class SensitiveWordContext implements IWordContext {

    /**
     * 忽略大小写
     * @since 0.0.4
     */
    private boolean ignoreCase;

    /**
     * 忽略半角全角
     * @since 0.0.4
     */
    private boolean ignoreWidth;

    /**
     * 是否忽略数字格式
     * @since 0.0.5
     */
    private boolean ignoreNumStyle;

    /**
     * 敏感词信息
     * @since 0.0.5
     */
    private Map sensitiveWordMap;

    /**
     * 是否进行敏感数字检测
     * @since 0.0.6
     */
    private boolean sensitiveNumCheck;
    /**
     * 私有化构造器
     * @since 0.0.4
     */
    private SensitiveWordContext() {
    }

    /**
     * 新建一个对象实例
     * @return 对象实例
     * @since 0.0.4
     */
    public static SensitiveWordContext newInstance() {
        return new SensitiveWordContext();
    }

    @Override
    public boolean ignoreCase() {
        return ignoreCase;
    }

    @Override
    public SensitiveWordContext ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    @Override
    public boolean ignoreWidth() {
        return ignoreWidth;
    }

    @Override
    public SensitiveWordContext ignoreWidth(boolean ignoreWidth) {
        this.ignoreWidth = ignoreWidth;
        return this;
    }

    @Override
    public boolean ignoreNumStyle() {
        return ignoreNumStyle;
    }

    @Override
    public SensitiveWordContext ignoreNumStyle(boolean ignoreNumStyle) {
        this.ignoreNumStyle = ignoreNumStyle;
        return this;
    }

    @Override
    public Map sensitiveWordMap() {
        return sensitiveWordMap;
    }

    @Override
    public SensitiveWordContext sensitiveWordMap(Map sensitiveWordMap) {
        this.sensitiveWordMap = sensitiveWordMap;
        return this;
    }

    @Override
    public boolean sensitiveNumCheck() {
        return sensitiveNumCheck;
    }

    @Override
    public SensitiveWordContext sensitiveNumCheck(boolean sensitiveNumCheck) {
        this.sensitiveNumCheck = sensitiveNumCheck;
        return this;
    }

    @Override
    public String toString() {
        return "SensitiveWordContext{" +
                "ignoreCase=" + ignoreCase +
                ", ignoreWidth=" + ignoreWidth +
                ", ignoreNumStyle=" + ignoreNumStyle +
                ", sensitiveNumCheck=" + sensitiveNumCheck +
                '}';
    }

}
