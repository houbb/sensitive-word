package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

import java.util.List;

/**
 * 格式化责任链
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public abstract class WordFormatInit implements IWordFormat {

    /**
     * 初始化列表
     *
     * @param pipeline 当前列表泳道
     * @since 0.0.13
     */
    protected abstract void init(final Pipeline<IWordFormat> pipeline);

    @Override
    public char format(char original, IWordContext context) {
        Pipeline<IWordFormat> pipeline = new DefaultPipeline<>();
        init(pipeline);

        char result = original;

        // 循环执行
        List<IWordFormat> charFormats = pipeline.list();
        for(IWordFormat charFormat : charFormats) {
            result = charFormat.format(result, context);
        }

        return result;
    }

}
