package com.github.houbb.sensitive.word.support.allow;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.sensitive.word.api.IWordAllow;

/**
 * 所有允许的结果
 * @author binbin.hou
 * @since 0.0.13
 */
public final class WordAllows {

    private WordAllows(){}

    /**
     * 责任链
     * @param wordAllow 允许
     * @param others 其他
     * @return 结果
     * @since 0.0.13
     */
    public static IWordAllow chains(final IWordAllow wordAllow,
                                    final IWordAllow... others) {
        return new WordAllowInit() {
            @Override
            protected void init(Pipeline<IWordAllow> pipeline) {
                pipeline.addLast(wordAllow);

                if(ArrayUtil.isNotEmpty(others)) {
                    for(IWordAllow other : others) {
                        pipeline.addLast(other);
                    }
                }
            }
        };
    }

    /**
     * 系统实现
     * @return 结果
     * @since 0.0.13
     */
    public static IWordAllow defaults() {
        return WordAllowSystem.getInstance();
    }

}
