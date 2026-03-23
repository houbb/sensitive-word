package com.github.houbb.sensitive.word.bugs.b20260323;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.junit.Assert;
import org.junit.Test;

/**
 * 测试英文敏感词全词匹配功能
 */
public class EnglishWordMatchTest {

    /**
     * 测试英文敏感词全词匹配（使用默认策略）
     */
    @Test
    public void testEnglishWordMatchWithDefaultStrategy() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                // 使用默认策略 englishWordMatch()
                .init();

        sensitiveWordBs.addWord("av");
        sensitiveWordBs.addWord("sex");

        // 直接匹配
        Assert.assertTrue(sensitiveWordBs.contains("av"));
        Assert.assertTrue(sensitiveWordBs.contains("sex"));

        // 英文敏感词全词匹配
        Assert.assertTrue(sensitiveWordBs.contains("av is bad"));
        Assert.assertTrue(sensitiveWordBs.contains("test sex test"));

        // 英文敏感词在其他英文单词中不应该匹配
        Assert.assertFalse(sensitiveWordBs.contains("have"));  // "av" 在 "have" 中
        Assert.assertFalse(sensitiveWordBs.contains("Middlesex"));  // "sex" 在 "Middlesex" 中

        // 英文敏感词在非英文字符前后应该能匹配
        Assert.assertTrue(sensitiveWordBs.contains("1av2"));
        Assert.assertTrue(sensitiveWordBs.contains("av测试"));
        Assert.assertTrue(sensitiveWordBs.contains("测试sex"));
    }

    /**
     * 测试中文敏感词在英文前后能被检测到（使用默认策略）
     */
    @Test
    public void testChineseWordWithEnglishAround() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                // 使用默认策略 englishWordMatch()
                .init();

        sensitiveWordBs.addWord("弹药");
        sensitiveWordBs.addWord("微信");

        // 中文敏感词直接匹配
        Assert.assertTrue(sensitiveWordBs.contains("弹药"));

        // 中文敏感词在英文前后应该能被检测到（这是 bug 修复的核心）
        Assert.assertTrue(sensitiveWordBs.contains("a弹药b"));
        Assert.assertTrue(sensitiveWordBs.contains("test微信test"));
    }

}
