package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.api.IWordDeny;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SensitiveWordFailFastTest {

    @Test
    public void failFastTest() {
        SensitiveWordBs bs = SensitiveWordBs.newInstance()
                .failFastWordPattern(true)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("我的世界", "我的");
                    }
                }).init();

        String text = "我在我的家里玩我的世界";

        List<String> textList = bs.findAll(text);
        Assert.assertEquals(Arrays.asList("我的", "我的"), textList);

    }
    @Test
    public void fallOverTest() {
        SensitiveWordBs bs = SensitiveWordBs.newInstance()
                .failFastWordPattern(false)
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("我的世界", "我的");
                    }
                }).init();

        String text = "我在我的家里玩我的世界";

        List<String> textList = bs.findAll(text);
        Assert.assertEquals(Arrays.asList("我的", "我的世界"), textList);

    }

}
