package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.api.IWordContext;

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

    private static class ContextHolder {
        private static final SensitiveWordContext INSTANCE = new SensitiveWordContext();

        static {
            INSTANCE.ignoreCase(true);
            INSTANCE.ignoreWidth(true);
        }
    }

    /**
     * 默认配置
     * @return 结果
     * @since 0.0.4
     */
    private static SensitiveWordContext defaultContext() {
        return ContextHolder.INSTANCE;
    }

    @Override
    public String toString() {
        return "SensitiveWordContext{" +
                "ignoreCase=" + ignoreCase +
                ", ignoreWidth=" + ignoreWidth +
                '}';
    }

}
