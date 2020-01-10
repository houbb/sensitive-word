package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.ICharFormat;

import java.util.List;

/**
 * 格式化责任链
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class CharFormatChain implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        char result = original;

        List<ICharFormat> charFormats = Guavas.newArrayList();
        if(context.ignoreEnglishStyle()) {
            charFormats.add(Instances.singleton(IgnoreEnglishStyleFormat.class));
        }
        if(context.ignoreCase()) {
            charFormats.add(Instances.singleton(IgnoreCaseCharFormat.class));
        }
        if(context.ignoreWidth()) {
            charFormats.add(Instances.singleton(IgnoreWidthCharFormat.class));
        }
        if(context.ignoreNumStyle()) {
            charFormats.add(Instances.singleton(IgnoreNumStyleCharFormat.class));
        }
        if(context.ignoreChineseStyle()) {
            charFormats.add(Instances.singleton(IgnoreChineseStyleFormat.class));
        }

        // 循环执行
        for(ICharFormat charFormat : charFormats) {
            result = charFormat.format(result, context);
        }

        return result;
    }

}
