package com.github.houbb.sensitive.word.support.data;

import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordContainsTypeEnum;

import java.util.Collection;

/**
 * 抽象数据
 *
 * @since 0.7.0
 */
public abstract class AbstractWordData implements IWordData {

    /**
     * 是否包含
     * @param stringBuilder 字符
     * @param innerContext 上下文
     * @return 结果
     */
    protected abstract WordContainsTypeEnum doContains(StringBuilder stringBuilder, InnerSensitiveWordContext innerContext);

    /**
     * 初始化
     * @param collection 数据
     */
    protected abstract void doInitWordData(Collection<String> collection);

    /**
     * 删除敏感词
     * @param collection
     */
    protected abstract void doRemoveWord(Collection<String> collection);

    /**
     * 新增敏感词
     * @param collection 敏感词
     */
    protected abstract void doAddWord(Collection<String> collection);

    @Override
    public void initWordData(Collection<String> collection) {
        //1. 预留

        this.doInitWordData(collection);
    }

    @Override
    public void removeWord(Collection<String> collection) {
        if(CollectionUtil.isEmpty(collection)) {
            return;
        }

        doRemoveWord(collection);
    }

    @Override
    public void addWord(Collection<String> collection) {
        if(CollectionUtil.isEmpty(collection)) {
            return;
        }

        doAddWord(collection);
    }

    @Override
    public WordContainsTypeEnum contains(StringBuilder stringBuilder, InnerSensitiveWordContext innerContext) {
        if(stringBuilder == null
            || stringBuilder.length() <= 0) {
            return WordContainsTypeEnum.NOT_FOUND;
        }

        return doContains(stringBuilder, innerContext);
    }

}
