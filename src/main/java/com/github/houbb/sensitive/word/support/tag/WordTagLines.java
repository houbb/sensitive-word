package com.github.houbb.sensitive.word.support.tag;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.IWordTag;

import java.util.*;

/**
 * 根据标准的行来处理
 *
 * 行规范：
 *
 * 单词 标签1,标签2
 *
 * @since 0.24.0
 */
public class WordTagLines extends AbstractWordTag {

    private final IWordTag wordTag;

    /**
     * 词和标签的分隔符
     */
    private final String wordSplit;
    /**
     * 标签的分隔符
     */
    private final String tagSplit;

    public WordTagLines(Collection<String> lines,
                        final String wordSplit,
                        final String tagSplit) {
        ArgUtil.notNull(lines, "lines");
        ArgUtil.notEmpty(wordSplit, "wordSplit");
        ArgUtil.notEmpty(tagSplit, "tagSplit");

        this.wordSplit = wordSplit;
        this.tagSplit = tagSplit;

        Map<String, Set<String>> wordTagMap = buildWordTagMap(lines);
        wordTag = WordTags.map(wordTagMap);
    }

    public WordTagLines(Collection<String> lines) {
        this(lines, " ", ",");
    }

    private Map<String, Set<String>> buildWordTagMap(final Collection<String> lines) {
        Map<String, Set<String>> wordTagMap = new HashMap<>();

        for(String line : lines) {
            String[] strings = line.split(wordSplit);
            String key = strings[0];
            Set<String> tags = new HashSet<>(StringUtil.splitToList(strings[1], tagSplit));
            wordTagMap.put(key, tags);
        }
        return wordTagMap;
    }

    @Override
    protected Set<String> doGetTag(String word) {
        return wordTag.getTag(word);
    }

}
