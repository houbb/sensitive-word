package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.ICharFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

import java.util.Collection;
import java.util.List;

/**
 * 格式化工具类
 * @author binbin.hou
 * @since 0.3.5
 */
public final class CharFormats {

    private CharFormats(){}

    /**
     * 初始化格式化
     * @param context 上下文
     * @return 结果
     * @since 0.3.0
     */
    public static ICharFormat initCharFormat(final IWordContext context) {
        List<ICharFormat> charFormats = Guavas.newArrayList();
        if(context.ignoreEnglishStyle()) {
            charFormats.add(ignoreEnglishStyle());
        }
        if(context.ignoreCase()) {
            charFormats.add(ignoreCase());
        }
        if(context.ignoreWidth()) {
            charFormats.add(ignoreWidth());
        }
        if(context.ignoreNumStyle()) {
            charFormats.add(ignoreNumStyle());
        }
        if(context.ignoreChineseStyle()) {
            charFormats.add(ignoreChineseStyle());
        }

        return chains(charFormats);
    }

    /**
     * 链式
     * @param charFormats 列表
     * @return 结果
     */
    public static ICharFormat chains(final ICharFormat ... charFormats) {
        if(ArrayUtil.isEmpty(charFormats)) {
            return none();
        }

        return new CharFormatInit() {
            @Override
            protected void init(Pipeline<ICharFormat> pipeline) {
                for(ICharFormat charFormat : charFormats) {
                    pipeline.addLast(charFormat);
                }
            }
        };
    }

    /**
     * 链式
     * @param charFormats 列表
     * @return 结果
     */
    public static ICharFormat chains(final Collection<ICharFormat> charFormats) {
        if(CollectionUtil.isEmpty(charFormats)) {
            return none();
        }

        return new CharFormatInit() {
            @Override
            protected void init(Pipeline<ICharFormat> pipeline) {
                for(ICharFormat charFormat : charFormats) {
                    pipeline.addLast(charFormat);
                }
            }
        };
    }

    public static ICharFormat none() {
        return NoneCharFormat.getInstance();
    }
    public static ICharFormat ignoreCase() {
        return IgnoreCaseCharFormat.getInstance();
    }

    public static ICharFormat ignoreEnglishStyle() {
        return IgnoreEnglishStyleFormat.getInstance();
    }

    public static ICharFormat ignoreChineseStyle() {
        return IgnoreChineseStyleFormat.getInstance();
    }

    public static ICharFormat ignoreNumStyle() {
        return IgnoreNumStyleCharFormat.getInstance();
    }

    public static ICharFormat ignoreWidth() {
        return IgnoreWidthCharFormat.getInstance();
    }

}
