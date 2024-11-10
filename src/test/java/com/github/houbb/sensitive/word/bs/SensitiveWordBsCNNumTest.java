package com.github.houbb.sensitive.word.bs;


import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SensitiveWordBsCNNumTest {

    /**
     * 返回所有敏感词
     * @since 0.0.5
     */
    @Test
    public void ignoreNumStyleTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .enableNumCheck(true)
                .init();
        sensitiveWordBs.addWord("下三滥");
        List<String> res1 = sensitiveWordBs.findAll("花豹用下三滥招式对付疣猪，没想到疣猪居然也有绝招");
        System.out.println("敏感词："+res1);

        sensitiveWordBs.addWord("下3滥");
        List<String> res2 = sensitiveWordBs.findAll("花豹用下三滥招式对付疣猪，没想到疣猪居然也有绝招");
        System.out.println("敏感词："+res2);

        sensitiveWordBs.addWord("下三滥");
        List<String> res3 = sensitiveWordBs.findAll("花豹用下3滥招式对付疣猪，没想到疣猪居然也有绝招");
        System.out.println("敏感词："+res3);

        sensitiveWordBs.addWord("下3滥");
        List<String> res4 = sensitiveWordBs.findAll("花豹用下3滥招式对付疣猪，没想到疣猪居然也有绝招");
        System.out.println("敏感词："+res4);
    }
}

