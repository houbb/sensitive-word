package com.github.houbb.sensitive.word.api;

/**
 * 拒绝出现的数据-返回的内容被当做是敏感词
 *
 * @author binbin.hou
 * @since 0.0.13
 */
public interface IEditableWordDeny extends IWordDeny {
    /**
     * 添加单个词
     *
     * @since 0.15.0
     */
    void add(String word);

    /**
     * 删除单个词
     *
     * @since 0.15.0
     */
    void remove(String word);
}
