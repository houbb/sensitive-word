package com.github.houbb.sensitive.word.bugs.b20211211;

import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.junit.Test;

public class MySensitiveTest {


    @Test
    public void test() {
        IWordDeny wordDeny = WordDenys.chains(WordDenys.defaults(), new MyWordDeny());
        IWordAllow wordAllow = WordAllows.chains(WordAllows.defaults(), new MyWordAllow());
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(wordAllow)
                .wordDeny(wordDeny)// 各种其他配置
                .init();// init() 初始化敏感词字典

        final String text = "五星红旗 我的自定义敏感词尼玛";
        //输出测试结果
        System.out.println("敏感词："+sensitiveWordBs.findAll(text).toString());
    }

}
