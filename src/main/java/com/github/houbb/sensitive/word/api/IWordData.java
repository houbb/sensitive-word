package com.github.houbb.sensitive.word.api;

import java.util.List;

/**
 * 数据词接口
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IWordData {

    /**
     * 获取对应的敏感词
     * @return 结果
     * @since 0.0.1
     */
    List<String> getWordData();

}
