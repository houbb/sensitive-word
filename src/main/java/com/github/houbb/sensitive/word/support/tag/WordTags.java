package com.github.houbb.sensitive.word.support.tag;

import com.github.houbb.sensitive.word.api.IWordTag;

/**
 * 单词标签
 *
 * @since 0.10.0
 */
public class WordTags {

    public static IWordTag none() {
        return new NoneWordTag();
    }

    public static IWordTag file(String filePath) {
        return new FileWordTag(filePath);
    }

}
