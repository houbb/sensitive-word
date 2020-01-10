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
        if(context.ignoreCase()) {
            charFormats.add(Instances.singleton(IgnoreCaseCharFormat.class));
            charFormats.add(Instances.singleton(IgnoreWidthCharFormat.class));
            charFormats.add(Instances.singleton(IgnoreNumStyleCharFormat.class));
        }

        // 循环执行
        for(ICharFormat charFormat : charFormats) {
            result = charFormat.format(result, context);
        }

        return result;
    }

}
