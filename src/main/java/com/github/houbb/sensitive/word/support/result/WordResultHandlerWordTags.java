package com.github.houbb.sensitive.word.support.result;

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
        // 截取
        String word = InnerWordCharUtils.getString(originalText.toCharArray(), wordResult);

        // 创建 DTO 并设置原始单词
        WordTagsDto dto = new WordTagsDto();
        dto.setWord(word);

        // 如果启用了字符忽略功能，清理单词后再查找标签
        StringBuilder wordForTagLookup = new StringBuilder();
        char[] chars = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            // 如果字符不被忽略，则保留
            // TODO: 此处innercontext 被设为null，是否合理？
            if (!wordContext.charIgnore().ignore(i, chars, null)) {
                wordForTagLookup.append(chars[i]);
            }
        }
        // 获取 tags (使用清理后的单词查找标签)
        Set<String> wordTags = InnerWordTagUtils.tags(wordForTagLookup.toString(), wordContext);
        dto.setTags(wordTags);

        return dto;
    }

}
