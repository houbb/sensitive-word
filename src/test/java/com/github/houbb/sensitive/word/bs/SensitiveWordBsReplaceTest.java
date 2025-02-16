package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.api.IWordReplace;
import com.github.houbb.sensitive.word.replace.MyWordReplace;
import org.junit.Assert;
import org.junit.Test;

public class SensitiveWordBsReplaceTest {

    @Test
    public void defineReplaceTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance().init();

        IWordReplace replace = new MyWordReplace();
        String result = sensitiveWordBs.replace(text, replace);

        Assert.assertEquals("国家旗帜迎风飘扬，教员的画像屹立在***前。", result);
    }
}
