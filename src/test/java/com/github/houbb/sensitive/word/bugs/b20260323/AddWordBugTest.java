package com.github.houbb.sensitive.word.bugs.b20260323;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import com.github.houbb.sensitive.word.support.resultcondition.WordResultConditions;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Bug 修复测试：中文敏感词在英文前后无法被检测到
 *
 * 问题原因：WordResultConditionEnglishWordMatch 对所有敏感词都检查前后字符是否为英文
 * 修复方案：只有敏感词本身全是英文时，才检查前后字符
 */
public class AddWordBugTest {

    // ==================== 基础测试 ====================

    /**
     * 测试中文敏感词在英文前后能被检测到（核心 bug 修复）
     */
    @Test
    public void addTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        sensitiveWordBs.addWord("微信");
        sensitiveWordBs.addWord("弹药");
        sensitiveWordBs.addWord("DP");

        Assert.assertTrue(sensitiveWordBs.contains("弹药"));
        Assert.assertTrue(sensitiveWordBs.contains("a弹药b"));
    }

    /**
     * 使用 alwaysTrue() 策略测试（不做任何过滤）
     */
    @Test
    public void addTest2() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .wordResultCondition(WordResultConditions.alwaysTrue())
                .init();

        sensitiveWordBs.addWord("微信");
        sensitiveWordBs.addWord("弹药");
        sensitiveWordBs.addWord("DP");

        Assert.assertTrue(sensitiveWordBs.contains("弹药"));
        Assert.assertTrue(sensitiveWordBs.contains("a弹药b"));
    }

    // ==================== 英文敏感词全词匹配测试 ====================

    /**
     * 测试英文敏感词必须全词匹配
     */
    @Test
    public void addTest3() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        sensitiveWordBs.addWord("av");
        sensitiveWordBs.addWord("cp");

        // 英文敏感词在其他英文单词中不应该匹配
        Assert.assertFalse(sensitiveWordBs.contains("cpm"));
        Assert.assertFalse(sensitiveWordBs.contains("have"));

        // 英文敏感词直接匹配
        Assert.assertTrue(sensitiveWordBs.contains("av"));

        // 英文敏感词在中文前后应该能匹配
        Assert.assertTrue(sensitiveWordBs.contains("av他"));

        // 英文敏感词在数字前后应该能匹配
        Assert.assertTrue(sensitiveWordBs.contains("1av2"));

        // 英文敏感词后跟中文不应该匹配前一个英文
        Assert.assertFalse(sensitiveWordBs.contains("have他"));
    }

    // ==================== 边界情况测试 ====================

    /**
     * 测试边界情况：空字符串、开头、结尾
     */
    @Test
    public void boundaryTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        sensitiveWordBs.addWord("弹药");
        sensitiveWordBs.addWord("test");

        // 空字符串
        Assert.assertFalse(sensitiveWordBs.contains(""));

        // 敏感词在开头
        Assert.assertTrue(sensitiveWordBs.contains("弹药abc"));
        Assert.assertTrue(sensitiveWordBs.contains("test abc"));

        // 敏感词在结尾
        Assert.assertTrue(sensitiveWordBs.contains("abc弹药"));
        Assert.assertTrue(sensitiveWordBs.contains("abc test"));

        // 只有敏感词
        Assert.assertTrue(sensitiveWordBs.contains("弹药"));
        Assert.assertTrue(sensitiveWordBs.contains("test"));
    }

    // ==================== 中英文混合场景测试 ====================

    /**
     * 测试中英文混合场景
     */
    @Test
    public void mixedChineseEnglishTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        sensitiveWordBs.addWord("微信");
        sensitiveWordBs.addWord("av");

        // 中文敏感词在英文前后
        Assert.assertTrue(sensitiveWordBs.contains("test微信test"));
        Assert.assertTrue(sensitiveWordBs.contains("a微信b"));

        // 英文敏感词在中文前后
        Assert.assertTrue(sensitiveWordBs.contains("测试av测试"));
        Assert.assertTrue(sensitiveWordBs.contains("测av试"));

        // 中文敏感词在中文前后
        Assert.assertTrue(sensitiveWordBs.contains("测试微信测试"));

        // 英文敏感词在英文前后不应该匹配
        Assert.assertFalse(sensitiveWordBs.contains("haveavtest"));
    }

    // ==================== 数字和特殊字符场景测试 ====================

    /**
     * 测试数字和特殊字符场景
     */
    @Test
    public void numberAndSpecialCharTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        sensitiveWordBs.addWord("弹药");
        sensitiveWordBs.addWord("av");
        sensitiveWordBs.addWord("123");

        // 中文敏感词与数字混合
        Assert.assertTrue(sensitiveWordBs.contains("1弹药2"));
        Assert.assertTrue(sensitiveWordBs.contains("123弹药456"));

        // 英文敏感词与数字混合
        Assert.assertTrue(sensitiveWordBs.contains("1av2"));
        Assert.assertTrue(sensitiveWordBs.contains("123av456"));

        // 纯数字敏感词
        Assert.assertTrue(sensitiveWordBs.contains("123"));
        Assert.assertTrue(sensitiveWordBs.contains("a123b"));

        // 特殊字符（下划线等）
        Assert.assertTrue(sensitiveWordBs.contains("_弹药_"));
        Assert.assertTrue(sensitiveWordBs.contains("_av_"));
    }

    // ==================== 多个敏感词场景测试 ====================

    /**
     * 测试多个敏感词同时出现的场景
     */
    @Test
    public void multipleWordsTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        sensitiveWordBs.addWord("微信");
        sensitiveWordBs.addWord("弹药");
        sensitiveWordBs.addWord("av");

        // 多个中文敏感词
        Assert.assertTrue(sensitiveWordBs.contains("微信弹药"));
        List<String> words1 = sensitiveWordBs.findAll("微信弹药");
        Assert.assertEquals(2, words1.size());

        // 多个英文敏感词
        Assert.assertTrue(sensitiveWordBs.contains("test av test"));
        List<String> words2 = sensitiveWordBs.findAll("av av av");
        Assert.assertEquals(3, words2.size());

        // 中英文混合
        Assert.assertTrue(sensitiveWordBs.contains("test微信test av弹药test"));
        List<String> words3 = sensitiveWordBs.findAll("test微信test av弹药test");
        Assert.assertEquals(3, words3.size());
    }

    // ==================== findAll 和 findFirst 测试 ====================

    /**
     * 测试 findAll 和 findFirst 方法
     */
    @Test
    public void findAllAndFirstTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        sensitiveWordBs.addWord("弹药");
        sensitiveWordBs.addWord("av");

        // findAll - 中文敏感词在英文前后
        List<String> words1 = sensitiveWordBs.findAll("a弹药b");
        Assert.assertEquals(1, words1.size());
        Assert.assertEquals("弹药", words1.get(0));

        // findFirst - 中文敏感词在英文前后
        String first1 = sensitiveWordBs.findFirst("a弹药b");
        Assert.assertEquals("弹药", first1);

        // findAll - 英文敏感词
        List<String> words2 = sensitiveWordBs.findAll("test av test");
        Assert.assertEquals(1, words2.size());
        Assert.assertEquals("av", words2.get(0));

        // findFirst - 英文敏感词
        String first2 = sensitiveWordBs.findFirst("test av test");
        Assert.assertEquals("av", first2);
    }

    // ==================== 长敏感词场景测试 ====================

    /**
     * 测试长敏感词场景
     */
    @Test
    public void longWordTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        sensitiveWordBs.addWord("这是一个非常长的敏感词");
        sensitiveWordBs.addWord("thisisaverylongword");

        // 长中文敏感词在英文前后
        Assert.assertTrue(sensitiveWordBs.contains("test这是一个非常长的敏感词test"));

        // 长英文敏感词
        Assert.assertTrue(sensitiveWordBs.contains("test thisisaverylongword test"));
        Assert.assertFalse(sensitiveWordBs.contains("thisisaverylongwordtest"));
    }

    // ==================== 单字符敏感词测试 ====================

    /**
     * 测试单字符敏感词
     */
    @Test
    public void singleCharWordTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        sensitiveWordBs.addWord("坏");
        sensitiveWordBs.addWord("x");

        // 单字符中文敏感词
        Assert.assertTrue(sensitiveWordBs.contains("坏"));
        Assert.assertTrue(sensitiveWordBs.contains("a坏b"));
        Assert.assertTrue(sensitiveWordBs.contains("这是一个坏词"));

        // 单字符英文敏感词
        Assert.assertTrue(sensitiveWordBs.contains("x"));
        Assert.assertTrue(sensitiveWordBs.contains("1x2"));
        Assert.assertTrue(sensitiveWordBs.contains("x测试"));

        // 单字符英文敏感词不应该在其他英文中匹配
        Assert.assertFalse(sensitiveWordBs.contains("example"));
    }

    // ==================== 连续添加敏感词测试 ====================

    /**
     * 测试连续添加敏感词
     */
    @Test
    public void continuousAddWordTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        // 连续添加
        sensitiveWordBs.addWord("词1");
        Assert.assertTrue(sensitiveWordBs.contains("a词1b"));

        sensitiveWordBs.addWord("词2");
        Assert.assertTrue(sensitiveWordBs.contains("a词2b"));

        // 两个词都应该存在
        Assert.assertTrue(sensitiveWordBs.contains("词1词2"));
        List<String> words = sensitiveWordBs.findAll("词1词2");
        Assert.assertEquals(2, words.size());
    }

    // ==================== 特殊英文字符测试 ====================

    /**
     * 测试包含大写字母的英文敏感词
     */
    @Test
    public void uppercaseEnglishWordTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        sensitiveWordBs.addWord("AV");
        sensitiveWordBs.addWord("Test");

        // 大写英文敏感词直接匹配
        Assert.assertTrue(sensitiveWordBs.contains("AV"));
        Assert.assertTrue(sensitiveWordBs.contains("Test"));

        // 在数字前后
        Assert.assertTrue(sensitiveWordBs.contains("1AV2"));
        Assert.assertTrue(sensitiveWordBs.contains("1Test2"));

        // 在中文前后
        Assert.assertTrue(sensitiveWordBs.contains("测AV试"));
        Assert.assertTrue(sensitiveWordBs.contains("测Test试"));

        // 在英文前后不应该匹配（全词匹配）
        Assert.assertFalse(sensitiveWordBs.contains("HAVE"));
        Assert.assertFalse(sensitiveWordBs.contains("ATESTB"));
    }

}
