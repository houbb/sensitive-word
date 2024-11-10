package com.github.houbb.sensitive.word.bugs.b54;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.junit.Assert;
import org.junit.Test;

public class Bug84Test {

    @Test
    public void bug84IgnoreNumberStyleTrueTest() {
        // 例如，敏感词“下三滥”。通过
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                // 因为这里默认是 true
                .ignoreNumStyle(true)
                .init();
        sensitiveWordBs.addWord("下三滥");
        String result = sensitiveWordBs.findFirst("花豹用下三滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertEquals("下三滥", result);

        String result2 = sensitiveWordBs.findFirst("花豹用下3滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertEquals("下3滥", result2);

        // 移除
        sensitiveWordBs.removeWord("下三滥");
        String result3 = sensitiveWordBs.findFirst("花豹用下三滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertNull(result3);

        String result4 = sensitiveWordBs.findFirst("花豹用下3滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertNull(result4);
    }

    @Test
    public void bug84IgnoreNumberStyleTrueTest2() {
        // 例如，敏感词“下三滥”。通过
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                // 因为这里默认是 true
                .ignoreNumStyle(true)
                .init();
        sensitiveWordBs.addWord("下3滥");
        String result = sensitiveWordBs.findFirst("花豹用下三滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertEquals("下三滥", result);

        String result2 = sensitiveWordBs.findFirst("花豹用下3滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertEquals("下3滥", result2);

        // 移除
        sensitiveWordBs.removeWord("下3滥");
        String result3 = sensitiveWordBs.findFirst("花豹用下三滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertNull(result3);

        String result4 = sensitiveWordBs.findFirst("花豹用下3滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertNull(result4);
    }

    @Test
    public void bug84IgnoreNumberStyleFalseTest() {
        // 例如，敏感词“下三滥”。通过
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                // 因为这里默认是 true
                .ignoreNumStyle(false)
                .init();
        sensitiveWordBs.addWord("下三滥");
        String result = sensitiveWordBs.findFirst("花豹用下三滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertEquals("下三滥", result);

        String result2 = sensitiveWordBs.findFirst("花豹用下3滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertNull(result2);

        // 移除
        sensitiveWordBs.removeWord("下三滥");
        String result3 = sensitiveWordBs.findFirst("花豹用下三滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertNull(result3);

        String result4 = sensitiveWordBs.findFirst("花豹用下3滥招式对付疣猪，没想到疣猪居然也有绝招");
        Assert.assertNull(result4);
    }

}
