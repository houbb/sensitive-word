package com.github.houbb.sensitive.word.api;

import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
import com.github.houbb.sensitive.word.constant.enums.WordContainsTypeEnum;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;

import java.util.Collection;
import java.util.List;

/**
 * 敏感词 map
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IWordMap {


    /**
     * 初始化敏感词 map
     * @param collection 集合信息
     * @since 0.0.1
     */
    void initWordMap(Collection<String> collection);

    /**
     * 是否包含敏感词
     * @param string 字符串
     * @param context 上下文
     * @return 是否包含
     * @since 0.0.1
     * @see ValidModeEnum#FAIL_FAST 建议使用快速返回模式
     */
    WordContainsTypeEnum contains(final String string,
                                  final IWordContext context);

}
