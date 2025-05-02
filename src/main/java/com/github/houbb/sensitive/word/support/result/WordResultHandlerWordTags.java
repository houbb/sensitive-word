package com.github.houbb.sensitive.word.support.result;

import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.utils.InnerWordCharUtils;
import com.github.houbb.sensitive.word.utils.InnerWordTagUtils;

import java.util.Set;

/**
 * 单词+对应的标签信息
 *
 * @author binbin.hou
 * @since 0.12.0
 */
public class WordResultHandlerWordTags extends AbstractWordResultHandler<WordTagsDto> {

    @Override
    protected WordTagsDto doHandle(IWordResult wordResult, IWordContext wordContext, String originalText) {
        WordTagsDto dto = new WordTagsDto();

        // 截取
        String word = InnerWordCharUtils.getString(originalText.toCharArray(), wordResult);

        // 获取 tags (使用清理后的单词查找标签)
        Set<String> wordTags = InnerWordTagUtils.tags(word, wordContext);

        // 如果为空，则尝试使用命中的敏感词匹配 v0.25.1 bug105
        if(CollectionUtil.isEmpty(wordTags)) {
            wordTags = InnerWordTagUtils.tags(wordResult.word(), wordContext);
        }

        dto.setWord(word);
        dto.setTags(wordTags);

        return dto;
    }

}
