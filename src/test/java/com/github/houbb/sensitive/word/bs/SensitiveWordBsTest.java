package com.github.houbb.sensitive.word.bs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.0.1
 */
public class SensitiveWordBsTest {

    @Test
    public void containsTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。。";

        Assert.assertTrue(SensitiveWordBs.getInstance().contains(text));
    }

    @Test
    public void findAllTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        List<String> wordList = SensitiveWordBs.getInstance().findAll(text);
        Assert.assertEquals("[五星红旗, 毛主席, 天安门]", wordList.toString());
    }

    @Test
    public void findFirstTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        String word = SensitiveWordBs.getInstance().findFirst(text);
        Assert.assertEquals("五星红旗", word);
    }

}
