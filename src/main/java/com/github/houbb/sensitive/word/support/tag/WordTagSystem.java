package com.github.houbb.sensitive.word.support.tag;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.sensitive.word.api.IWordTag;

import java.util.List;
import java.util.Set;

/**
 * 系统内置策略，根据文件默认处理
 *
 * @since 0.24.0
 */
public class WordTagSystem extends AbstractWordTag {

    private final IWordTag wordTag;

    public WordTagSystem() {
        List<String> lines = StreamUtil.readAllLines("/sensitive_word_tags.txt");
        this.wordTag = WordTags.lines(lines);
    }

    @Override
    protected Set<String> doGetTag(String word) {
        return wordTag.getTag(word);
    }

}
