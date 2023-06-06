package com.github.houbb.sensitive.word.support.deny;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.sensitive.word.api.IWordDeny;

/**
 * 所有拒绝的结果
 * @author binbin.hou
 * @since 0.0.13
 */
public final class WordDenys {

    private WordDenys(){}

    /**
     * 责任链
     * @param wordDeny 拒绝
     * @param others 其他
     * @return 结果
     * @since 0.0.13
     */
    public static IWordDeny chains(final IWordDeny wordDeny,
                                   final IWordDeny... others) {
        return new WordDenyInit() {
            @Override
            protected void init(Pipeline<IWordDeny> pipeline) {
                pipeline.addLast(wordDeny);

                if(ArrayUtil.isNotEmpty(others)) {
                    for(IWordDeny other : others) {
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
    public static IWordDeny system() {
        return WordDenySystem.getInstance();
    }

}
