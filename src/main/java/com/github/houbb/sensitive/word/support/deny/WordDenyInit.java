package com.github.houbb.sensitive.word.support.deny;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.sensitive.word.api.IEditableWordDeny;
import com.github.houbb.sensitive.word.api.IWordDeny;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化类
 *
 * @author binbin.hou
 * @since 0.0.13
 */
@ThreadSafe
public abstract class WordDenyInit implements IEditableWordDeny {

    /**
     * 初始化列表
     *
     * @param pipeline 当前列表泳道
     * @since 0.0.13
     */
    protected abstract void init(final Pipeline<IWordDeny> pipeline);

    @Override
    public List<String> deny() {
        Pipeline<IWordDeny> pipeline = new DefaultPipeline<>();
        this.init(pipeline);

        List<String> results = new ArrayList<>();
        List<IWordDeny> wordDenies = pipeline.list();
        for (IWordDeny wordDeny : wordDenies) {
            List<String> denyList = wordDeny.deny();
            results.addAll(denyList);
        }

        return results;
    }

}
