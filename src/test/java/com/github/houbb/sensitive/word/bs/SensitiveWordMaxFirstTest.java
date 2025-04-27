package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.api.IWordDeny;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SensitiveWordMaxFirstTest {

    @Test
    public void maxFirstTest() {
        SensitiveWordBs bs = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("我的世界", "我的");
                    }
                }).init();

        String text = "我的世界我的好玩";

        List<String> textList = bs.findAll(text);
//        Assert.assertEquals("", textList.toString());
    }

}
