package com.github.houbb.sensitive.word.support.tag;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordTag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 抽象的单词标签初始化引导类
 *
 * @since 0.24.0
 */
public abstract class AbstractWordTagInit extends AbstractWordTag {

    /**
     * 初始化列表
     *
     * @param pipeline 当前列表泳道
     * @since 0.24.0
     */
    protected abstract void init(final Pipeline<IWordTag> pipeline);

    @Override
    public Set<String> doGetTag(String word) {
        Pipeline<IWordTag> pipeline = new DefaultPipeline<>();
        this.init(pipeline);

        Set<String> resultSet = new HashSet<>();
        List<IWordTag> wordTagList = pipeline.list();
        for (IWordTag wordTag : wordTagList) {
            Set<String> tempTagSet = wordTag.getTag(word);
            if(CollectionUtil.isNotEmpty(tempTagSet)) {
                resultSet.addAll(tempTagSet);
            }
        }

        return resultSet;
    }

}
