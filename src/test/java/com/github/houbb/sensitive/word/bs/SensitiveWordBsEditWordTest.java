package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.19.0
 */
public class SensitiveWordBsEditWordTest {

    /**
     * @since 0.19.0
     */
    @Test
    public void editWordTest() {
        final String text = "测试一下新增敏感词，验证一下删除和新增对不对";

        SensitiveWordBs sensitiveWordBs =
        SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.empty())
                .wordDeny(WordDenys.empty())
                .init();

        // 当前
        Assert.assertEquals("[]", sensitiveWordBs.findAll(text).toString());

        // 新增单个
        sensitiveWordBs.addWord("测试");
        sensitiveWordBs.addWord("新增");
        Assert.assertEquals("[测试, 新增, 新增]", sensitiveWordBs.findAll(text).toString());

        // 删除单个
        sensitiveWordBs.removeWord("新增");
        Assert.assertEquals("[测试]", sensitiveWordBs.findAll(text).toString());
        sensitiveWordBs.removeWord("测试");
        Assert.assertEquals("[]", sensitiveWordBs.findAll(text).toString());

        // 新增集合
        sensitiveWordBs.addWord(Arrays.asList("新增", "测试"));
        Assert.assertEquals("[测试, 新增, 新增]", sensitiveWordBs.findAll(text).toString());
        // 删除集合
        sensitiveWordBs.removeWord(Arrays.asList("新增", "测试"));
        Assert.assertEquals("[]", sensitiveWordBs.findAll(text).toString());

        // 新增数组
        sensitiveWordBs.addWord("新增", "测试");
        Assert.assertEquals("[测试, 新增, 新增]", sensitiveWordBs.findAll(text).toString());
        // 删除集合
        sensitiveWordBs.removeWord("新增", "测试");
        Assert.assertEquals("[]", sensitiveWordBs.findAll(text).toString());
    }

}
