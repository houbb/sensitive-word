package com.github.houbb.sensitive.word.api;

import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;
import com.github.houbb.sensitive.word.constant.enums.WordContainsTypeEnum;

import java.util.Collection;

/**
 * 敏感词 map
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IWordData extends ISensitiveWordDestroy {

    /**
     * 初始化敏感词 map
     * @param collection 集合信息
     * @since 0.0.1
     */
    void initWordData(Collection<String> collection);

    /**
     * 删除敏感词
     * @param word 单词
     * @since 0.19.0
     */
    void removeWord(String word);

    /**
     * 新增敏感词
     * @param collection 敏感词集合
     * @since 0.19.0
     */
     void addWord(Collection<String> collection);

    /**
     * 是否包含敏感词
     * @param stringBuilder 缓冲
     * @param innerContext 上下文
     * @return 是否包含
     * @since 0.5.0
     * @see WordValidModeEnum#FAIL_FAST 建议使用快速返回模式
     */
    WordContainsTypeEnum contains(final StringBuilder stringBuilder,
                                  final InnerSensitiveWordContext innerContext);

}
