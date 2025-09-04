package com.github.houbb.sensitive.word.api;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;

import java.util.List;

/**
 * 提前预热，触发类加载、JIT 优化等
 * @author binbin.hou
 * @since 0.29.0
 */
public interface IWordWarmUp  {

    /**
     * 预热
     * @param sensitiveWordBs 引导类本身
     * @param wordContext 上下文
     * @param wordDenyList 允许
     * @param wordAllowList 禁止
     */
    void warmUp(final SensitiveWordBs sensitiveWordBs,
                final IWordContext wordContext,
                final List<String> wordAllowList,
                final List<String> wordDenyList);

}
