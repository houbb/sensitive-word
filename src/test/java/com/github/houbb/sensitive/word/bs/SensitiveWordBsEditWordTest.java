package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.api.IWordDeny;
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


    /**
     * @since 0.21.0
     */
    @Test
    public void editWordAllowTest() {
        final String text = "测试一下新增敏感词白名单，验证一下删除和新增对不对";

        SensitiveWordBs sensitiveWordBs =
                SensitiveWordBs.newInstance()
                        .wordAllow(WordAllows.empty())
                        .wordDeny(new IWordDeny() {
                            @Override
                            public List<String> deny() {
                                return Arrays.asList("测试", "新增");
                            }
                        })
                        .init();

        // 当前
        Assert.assertEquals("[测试, 新增, 新增]", sensitiveWordBs.findAll(text).toString());

        // 新增单个
        sensitiveWordBs.addWordAllow("测试");
        sensitiveWordBs.addWordAllow("新增");
        Assert.assertEquals("[]", sensitiveWordBs.findAll(text).toString());

        // 删除单个
        sensitiveWordBs.removeWordAllow("测试");
        Assert.assertEquals("[测试]", sensitiveWordBs.findAll(text).toString());
        sensitiveWordBs.removeWordAllow("新增");
        Assert.assertEquals("[测试, 新增, 新增]", sensitiveWordBs.findAll(text).toString());

        // 新增集合
        sensitiveWordBs.addWordAllow(Arrays.asList("新增", "测试"));
        Assert.assertEquals("[]", sensitiveWordBs.findAll(text).toString());
        // 删除集合
        sensitiveWordBs.removeWordAllow(Arrays.asList("新增", "测试"));
        Assert.assertEquals("[测试, 新增, 新增]", sensitiveWordBs.findAll(text).toString());

        // 新增数组
        sensitiveWordBs.addWordAllow("新增", "测试");
        Assert.assertEquals("[]", sensitiveWordBs.findAll(text).toString());
        // 删除集合
        sensitiveWordBs.removeWordAllow("新增", "测试");
        Assert.assertEquals("[测试, 新增, 新增]", sensitiveWordBs.findAll(text).toString());
    }

}
