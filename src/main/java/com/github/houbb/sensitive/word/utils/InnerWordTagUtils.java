package com.github.houbb.sensitive.word.utils;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordTag;

import java.util.Collections;
import java.util.Set;

/**
 * 内部的单词标签工具类
 *
 * @since 0.24.0
 */
public class InnerWordTagUtils {

    /**
     * 获取敏感词的标签
     *
     * @param word 敏感词
     * @param wordContext 上下文
     * @return 结果
     * @since 0.24.0
     */
    public static Set<String> tags(final String word,
                            final IWordContext wordContext) {
        if(StringUtil.isEmpty(word)) {
            return null;
        }

        final IWordTag wordTag = wordContext.wordTag();
        // 直接获取
        Set<String> actualSet = wordTag.getTag(word);
        if(CollectionUtil.isNotEmpty(actualSet)) {
            return actualSet;
        }

        // 格式化处理后的信息
        String formatWord = InnerWordFormatUtils.format(word, wordContext);
        return wordContext.wordTag().getTag(formatWord);
    }

}
