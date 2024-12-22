package com.github.houbb.sensitive.word.support.tag;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.sensitive.word.api.IWordTag;

import java.util.List;
import java.util.Set;

/**
 * 基于文件的标签
 *
 * word tag1,tag2
 * @since 0.10.0
 */
public class FileWordTag extends AbstractWordTag {

    /**
     * 词和标签的分隔符
     */
    protected final IWordTag wordTag;

    public FileWordTag(String filePath) {
        this(filePath, " ", ",");
    }

    public FileWordTag(String filePath, String wordSplit, String tagSplit) {
        ArgUtil.notEmpty(filePath, "filePath");
        ArgUtil.notEmpty(wordSplit, "wordSplit");
        ArgUtil.notEmpty(tagSplit, "tagSplit");

        List<String> lines = FileUtil.readAllLines(filePath);
        wordTag = WordTags.lines(lines, wordSplit, tagSplit);
    }

    @Override
    protected Set<String> doGetTag(String word) {
        return wordTag.getTag(word);
    }

}
