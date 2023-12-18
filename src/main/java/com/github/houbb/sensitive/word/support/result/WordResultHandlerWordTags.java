package com.github.houbb.sensitive.word.support.result;

import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.utils.InnerWordCharUtils;

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
        // 截取
        String word = InnerWordCharUtils.getString(originalText.toCharArray(), wordResult);
        // 标签

        WordTagsDto dto = new WordTagsDto();
        dto.setWord(word);
        // 获取 tags
        Set<String> wordTags = wordContext.wordTag().getTag(word);
        dto.setTags(wordTags);
        return dto;
    }

}
