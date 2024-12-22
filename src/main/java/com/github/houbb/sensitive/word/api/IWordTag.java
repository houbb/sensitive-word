package com.github.houbb.sensitive.word.api;

import java.util.Set;

/**
 * 获取脏词的标签，便于分类
 *
 * @author dh
 * @since 0.10.0
 */
public interface IWordTag {

    /**
     * 查询标签列表
     * @param word 脏词
     * @return 结果
     */
    Set<String> getTag(final String word);

}
