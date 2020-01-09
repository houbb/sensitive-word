package com.github.houbb.sensitive.word.api;

/**
 * @author binbin.hou
 * @since 0.0.4
 */
public interface IWordContext {

    /**
     * 是否忽略大小写
     * @return 是否
     * @since 0.0.4
     */
    boolean ignoreCase();

    /**
     * 是否忽略半角圆角
     * @return 是否
     * @since 0.0.4
     */
    boolean ignoreWidth();

    /**
     * 设置是否忽略大小写
     * @param ignoreCase 是否忽略大小写
     * @return this
     * @since 0.0.4
     */
    IWordContext ignoreCase(boolean ignoreCase);

    /**
     * 设置是否忽略半角圆角
     * @param ignoreWidth 是否忽略半角圆角
     * @return this
     * @since 0.0.4
     */
    IWordContext ignoreWidth(boolean ignoreWidth);

}
