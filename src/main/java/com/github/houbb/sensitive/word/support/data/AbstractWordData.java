package com.github.houbb.sensitive.word.support.data;

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

    @Override
    public void initWordData(Collection<String> collection) {
        //1. 预留

        this.doInitWordData(collection);
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
