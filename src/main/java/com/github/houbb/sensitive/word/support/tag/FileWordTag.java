package com.github.houbb.sensitive.word.support.tag;

import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.util.*;

/**
 * 基于文件的标签
 *
 * word tag1,tag2
 * @since 0.10.0
 */
public class FileWordTag extends AbstractWordTag {

    /**
     * 文件路径
     */
    protected final String filePath;
    /**
     * 词和标签的分隔符
     */
    protected final String wordSplit;
    /**
     * 标签的分隔符
     */
    protected final String tagSplit;

    protected Map<String, Set<String>> wordTagMap = new HashMap<>();

    public FileWordTag(String filePath) {
        this(filePath, " ", ",");
    }

    public FileWordTag(String filePath, String wordSplit, String tagSplit) {
        ArgUtil.notEmpty(filePath, "filePath");
        ArgUtil.notEmpty(wordSplit, "wordSplit");
        ArgUtil.notEmpty(tagSplit, "tagSplit");

        this.wordSplit = wordSplit;
        this.tagSplit = tagSplit;
        this.filePath = filePath;

        this.initWordTagMap();
    }


    /**
     * 初始化
     */
    protected synchronized void initWordTagMap() {
        List<String> lines = FileUtil.readAllLines(filePath);
        if(CollectionUtil.isEmpty(lines)) {
            return;
        }

        for(String line : lines) {
            if(StringUtil.isEmpty(line)) {
                continue;
            }

            // 处理每一行
            handleInitLine(line);
        }
    }

    protected synchronized void handleInitLine(String line) {
        String[] strings = line.split(wordSplit);
        if(strings.length < 2) {
            return;
        }

        String word = strings[0];
        String tagText = strings[1];


        String[] tags = tagText.split(tagSplit);
        Set<String> tagSet = new HashSet<>(Arrays.asList(tags));
        wordTagMap.put(word, tagSet);
    }

    @Override
    protected Set<String> doGetTag(String word) {
        return wordTagMap.get(word);
    }

}
