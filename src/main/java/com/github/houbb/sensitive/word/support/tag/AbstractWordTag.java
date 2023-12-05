package com.github.houbb.sensitive.word.support.tag;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.IWordTag;

import java.util.Collections;
import java.util.Set;

/**
 * 抽象的单词标签
 *
 * @since 0.10.0
 */
public abstract class AbstractWordTag implements IWordTag {


    /**
     * 获取标签
     * @param word 单词
     * @return 结果
     */
    protected abstract Set<String> doGetTag(String word);

    @Override
    public Set<String> getTag(String word) {
        if(StringUtil.isEmpty(word)) {
            return Collections.emptySet();
        }

        return doGetTag(word);
    }

}
