package com.github.houbb.sensitive.word.bugs.b32;

import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.junit.Assert;
import org.junit.Test;

public class MyWordDenyChineseTest  {

    @Test
    public void test() {
        IWordDeny wordDeny = WordDenys.chains(WordDenys.defaults(), new MyWordDenyChineseNum());
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordDeny(wordDeny)// 各种其他配置
                .init();// init() 初始化敏感词字典

        final String text = "和我练习三三九乘元功、一军两策";

        //输出测试结果
        Assert.assertEquals("[三三九乘元功, 一军两策]", sensitiveWordBs.findAll(text).toString());
        Assert.assertTrue(sensitiveWordBs.contains("三三九乘元功"));
        Assert.assertTrue(sensitiveWordBs.contains("一军两策"));
    }

}
