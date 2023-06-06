package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.sensitive.word.api.ICharFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

import java.util.List;

/**
 * 格式化责任链
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public abstract class CharFormatInit implements ICharFormat {

    /**
     * 初始化列表
     *
     * @param pipeline 当前列表泳道
     * @since 0.0.13
     */
    protected abstract void init(final Pipeline<ICharFormat> pipeline);

    @Override
    public char format(char original, IWordContext context) {
        Pipeline<ICharFormat> pipeline = new DefaultPipeline<>();
        init(pipeline);

        char result = original;

        // 循环执行
        List<ICharFormat> charFormats = pipeline.list();
        for(ICharFormat charFormat : charFormats) {
            result = charFormat.format(result, context);
        }

        return result;
    }

}
