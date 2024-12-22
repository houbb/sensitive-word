package com.github.houbb.sensitive.word.support.tag;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.sensitive.word.api.IWordTag;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 单词标签
 *
 * @since 0.10.0
 */
public class WordTags {

    /**
     * 空实现
     * @return 空实现
     * @since 0.10.0
     */
    public static IWordTag none() {
        return new NoneWordTag();
    }

    /**
     * 文件实现
     * @param filePath 文件路径
     * @return 文件实现
     * @since 0.10.0
     */
    public static IWordTag file(String filePath) {
        return new FileWordTag(filePath);
    }

    /**
     * 文件实现
     *
     * @param filePath 文件路径
     * @param wordSplit 单词分割
     * @param tagSplit 标签分割
     * @return 实现
     * @since 0.24.0
     */
    public static IWordTag file(String filePath, final String wordSplit, final String tagSplit) {
        return new FileWordTag(filePath, wordSplit, tagSplit);
    }

    /**
     * MAP 策略
     * @param wordTagMap map
     * @return 实现
     * @since 0.24.0
     */
    public static IWordTag map(final Map<String, Set<String>> wordTagMap) {
        return new WordTagMap(wordTagMap);
    }

    /**
     * 根据标准的约定行处理
     * @param lines 行信息
     * @return 结果
     * @since 0.24.0
     */
    public static IWordTag lines(final Collection<String> lines) {
        return new WordTagLines(lines);
    }

    /**
     * 根据标准的约定行处理
     * @param lines 行信息
     * @return 结果
     */
    public static IWordTag lines(final Collection<String> lines, final String wordSplit, final String tagSplit) {
        return new WordTagLines(lines, wordSplit, tagSplit);
    }

    /**
     * 系统文件策略
     * @return 标准策略
     * @since 0.24.0
     */
    public static IWordTag system() {
        return new WordTagSystem();
    }

    /**
     * 默认策略
     * @return 标准策略
     * @since 0.24.0
     */
    public static IWordTag defaults() {
        return system();
    }

    /**
     * 链式调用
     *
     * @param wordTag 标签策略
     * @param others 其他
     * @return 结果
     * @since 0.24.0
     */
    public static IWordTag chains(final IWordTag wordTag,
                                   final IWordTag... others) {
        ArgUtil.notNull(wordTag, "wordTag");

        return new AbstractWordTagInit() {
            @Override
            protected void init(Pipeline<IWordTag> pipeline) {
                pipeline.addLast(wordTag);

                if(ArrayUtil.isNotEmpty(others)) {
                    for(IWordTag other : others) {
                        pipeline.addLast(other);
                    }
                }
            }
        };
    }

}
