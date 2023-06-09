package com.github.houbb.sensitive.word.define;

import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class SensitiveWordBsDefineTest {

    @Test
    public void defineDenyTest() {
        String text = "这是一个测试，我的自定义敏感词。";

        SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
                .wordDeny(new MyWordDeny())
                .wordAllow(new MyWordAllow())
                .init();

        Assert.assertEquals("[我的自定义敏感词]", wordBs.findAll(text).toString());
    }

    @Test
    public void defineChainsTest() {
        String text = "这是一个测试。我的自定义敏感词。";

        IWordDeny wordDeny = WordDenys.chains(WordDenys.defaults(), new MyWordDeny());
        IWordAllow wordAllow = WordAllows.chains(WordAllows.defaults(), new MyWordAllow());

        SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
                .wordDeny(wordDeny)
                .wordAllow(wordAllow)
                .init();

        Assert.assertEquals("[我的自定义敏感词]", wordBs.findAll(text).toString());
    }

}
