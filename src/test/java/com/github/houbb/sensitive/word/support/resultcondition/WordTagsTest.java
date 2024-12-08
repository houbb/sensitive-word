package com.github.houbb.sensitive.word.support.resultcondition;

import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class WordTagsTest {


    /**
     * 是否包含
     *
     * @since 0.23.0
     */
    @Test
    public void wordTagsTest() {
        // 只关心SE情
        SensitiveWordBs sensitiveWordBsYellow = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("商品", "AV");
                    }
                })
                .wordAllow(WordAllows.empty())
                .wordTag(new MyWordTag())
                .wordResultCondition(WordResultConditions.wordTags(Arrays.asList("色情")))
                .init();

        // 只关心广告
        SensitiveWordBs sensitiveWordBsAd = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("商品", "AV");
                    }
                })
                .wordAllow(WordAllows.empty())
                .wordTag(new MyWordTag())
                .wordResultCondition(WordResultConditions.wordTags(Arrays.asList("广告")))
                .init();

        final String text = "这些 AV 商品什么价格？";
        Assert.assertEquals("[AV]", sensitiveWordBsYellow.findAll(text).toString());
        Assert.assertEquals("[商品]", sensitiveWordBsAd.findAll(text).toString());
    }

}
