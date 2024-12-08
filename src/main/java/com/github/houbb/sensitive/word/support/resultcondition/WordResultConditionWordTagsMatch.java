package com.github.houbb.sensitive.word.support.resultcondition;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.api.IWordTag;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;

import java.util.Collection;
import java.util.Set;

/**
 * 结果标签匹配的条件
 *
 * @since 0.23.0
 */
public class WordResultConditionWordTagsMatch extends AbstractWordResultCondition {

    /**
     * 指定标签的集合
     */
    private final Collection<String> tags;

    public WordResultConditionWordTagsMatch(Collection<String> tags) {
        ArgUtil.notEmpty(tags, "tags");

        this.tags = tags;
    }

    @Override
    protected boolean doMatch(IWordResult wordResult, String text, WordValidModeEnum modeEnum, IWordContext context) {
        // 判断对应的标签
        String word = text.substring(wordResult.startIndex(), wordResult.endIndex());
        final IWordTag wordTag = context.wordTag();
        Set<String> wordTags = wordTag.getTag(word);

        // 在指定的 tag 中
        if(CollectionUtil.isEmpty(wordTags)) {
            return false;
        }

        for(String tag : tags) {
            if(wordTags.contains(tag)) {
                return true;
            }
        }

        return false;
    }

}
