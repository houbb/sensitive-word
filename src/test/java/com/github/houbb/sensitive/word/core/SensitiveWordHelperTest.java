package com.github.houbb.sensitive.word.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.0.13
 */
public class SensitiveWordHelperTest {

    /**
     * 是否包含
     * @since 0.0.1
     */
    @Test
    public void containsTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        Assert.assertTrue(SensitiveWordHelper.contains(text));
    }

    /**
     * 返回所有敏感词
     * @since 0.0.1
     */
    @Test
    public void findAllTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        List<String> wordList = SensitiveWordHelper.findAll(text);
        Assert.assertEquals("[五星红旗, 毛主席, 天安门]", wordList.toString());
    }

    /**
     * 返回所有第一个匹配的敏感词
     * @since 0.0.1
     */
    @Test
    public void findFirstTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        String word = SensitiveWordHelper.findFirst(text);
        Assert.assertEquals("五星红旗", word);
    }

    /**
     * 默认的替换策略
     * @since 0.0.2
     */
    @Test
    public void replaceTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        String result = SensitiveWordHelper.replace(text);
        Assert.assertEquals("****迎风飘扬，***的画像屹立在***前。", result);
    }

    /**
     * 自定义字符的替换策略
     * @since 0.0.2
     */
    @Test
    public void replaceCharTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        String result = SensitiveWordHelper.replace(text, '0');
        Assert.assertEquals("0000迎风飘扬，000的画像屹立在000前。", result);
    }

    /**
     * 忽略大小写
     * @since 0.0.4
     */
    @Test
    public void ignoreCaseTest() {
        final String text = "fuCK the bad words.";

        String word = SensitiveWordHelper.findFirst(text);
        Assert.assertEquals("fuCK", word);
    }

    /**
     * 忽略半角圆角
     * @since 0.0.4
     */
    @Test
    public void ignoreWidthTest() {
        final String text = "ｆｕｃｋ the bad words.";

        String word = SensitiveWordHelper.findFirst(text);
        Assert.assertEquals("ｆｕｃｋ", word);
    }

}
