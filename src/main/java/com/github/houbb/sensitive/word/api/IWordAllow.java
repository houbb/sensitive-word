package com.github.houbb.sensitive.word.api;

import java.util.List;

/**
 * 允许的内容-返回的内容不被当做敏感词
 * @author binbin.hou
 * @since 0.0.13
 */
public interface IWordAllow {

    /**
     * 获取结果
     * @return 结果
     * @since 0.0.13
     */
    List<String> allow();

}
