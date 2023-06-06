package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.api.ICharFormat;
import com.github.houbb.sensitive.word.api.ISensitiveWordReplace;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;

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
    private boolean sensitiveCheckNum;

    /**
     * 是否忽略中文繁简体
     * @since 0.0.6
     */
    private boolean ignoreChineseStyle;

    /**
     * 是否忽略英文的写法
     * @since 0.0.6
     */
    private boolean ignoreEnglishStyle;

    /**
     * 忽略重复词
     * @since 0.0.7
     */
    private boolean ignoreRepeat;

    /**
     * 是否进行邮箱测试
     * @since 0.0.9
     */
    private boolean sensitiveCheckEmail;

    /**
     * 是否进行 url 测试
     * @since 0.0.12
     */
    private boolean sensitiveCheckUrl;

    /**
     * 敏感数字检测对应的长度限制
     * @since 0.2.1
     */
    private int sensitiveCheckNumLen;

    /**
     * 检测策略
     * @since 0.3.0
     */
    private ISensitiveCheck sensitiveCheck;

    /**
     * 替换策略
     * @since 0.3.0
     */
    private ISensitiveWordReplace sensitiveWordReplace;

    /**
     * 格式化
     * @since 0.3.0
     */
    private ICharFormat charFormat;

    @Override
    public ISensitiveWordReplace sensitiveWordReplace() {
        return sensitiveWordReplace;
    }

    public SensitiveWordContext sensitiveWordReplace(ISensitiveWordReplace sensitiveWordReplace) {
        this.sensitiveWordReplace = sensitiveWordReplace;
        return this;
    }

    @Override
    public ISensitiveCheck sensitiveCheck() {
        return sensitiveCheck;
    }

    public SensitiveWordContext sensitiveCheck(ISensitiveCheck sensitiveCheck) {
        this.sensitiveCheck = sensitiveCheck;
        return this;
    }

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
    public boolean sensitiveCheckNum() {
        return sensitiveCheckNum;
    }

    @Override
    public SensitiveWordContext sensitiveCheckNum(boolean sensitiveCheckNum) {
        this.sensitiveCheckNum = sensitiveCheckNum;
        return this;
    }

    @Override
    public boolean ignoreChineseStyle() {
        return ignoreChineseStyle;
    }

    @Override
    public SensitiveWordContext ignoreChineseStyle(boolean ignoreChineseStyle) {
        this.ignoreChineseStyle = ignoreChineseStyle;
        return this;
    }

    @Override
    public boolean ignoreEnglishStyle() {
        return ignoreEnglishStyle;
    }

    @Override
    public SensitiveWordContext ignoreEnglishStyle(boolean ignoreEnglishStyle) {
        this.ignoreEnglishStyle = ignoreEnglishStyle;
        return this;
    }

    @Override
    public boolean ignoreRepeat() {
        return ignoreRepeat;
    }

    @Override
    public SensitiveWordContext ignoreRepeat(boolean ignoreRepeat) {
        this.ignoreRepeat = ignoreRepeat;
        return this;
    }

    @Override
    public boolean sensitiveCheckEmail() {
        return sensitiveCheckEmail;
    }

    @Override
    public SensitiveWordContext sensitiveCheckEmail(boolean sensitiveCheckEmail) {
        this.sensitiveCheckEmail = sensitiveCheckEmail;
        return this;
    }

    @Override
    public boolean sensitiveCheckUrl() {
        return sensitiveCheckUrl;
    }

    @Override
    public SensitiveWordContext sensitiveCheckUrl(boolean sensitiveCheckUrl) {
        this.sensitiveCheckUrl = sensitiveCheckUrl;
        return this;
    }

    @Override
    public int sensitiveCheckNumLen() {
        return sensitiveCheckNumLen;
    }

    @Override
    public SensitiveWordContext sensitiveCheckNumLen(int sensitiveCheckNumLen) {
        this.sensitiveCheckNumLen = sensitiveCheckNumLen;
        return this;
    }

    @Override
    public ICharFormat charFormat() {
        return charFormat;
    }

    public SensitiveWordContext charFormat(ICharFormat charFormat) {
        this.charFormat = charFormat;
        return this;
    }
}
