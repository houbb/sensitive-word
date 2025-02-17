package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.support.check.WordChecks;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.25.0
 */
public class SensitiveWordBsUrlNoPrefixTest {

    /**
     * URL 检测
     *
     * @since 0.25.0
     */
    @Test
    public void urlNoPrefixTest() {
        final String text = "点击链接 https://www.baidu.com 查看答案，当然也可以是 baidu.com、www.baidu.com";

        final SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .enableUrlCheck(true) // 启用URL检测
                .wordCheckUrl(WordChecks.urlNoPrefix()) //指定检测的方式
                .init();
        List<String> wordList = sensitiveWordBs.findAll(text);
        Assert.assertEquals("[www.baidu.com, baidu.com, www.baidu.com]", wordList.toString());

        Assert.assertEquals("点击链接 https://************* 查看答案，当然也可以是 *********、*************", sensitiveWordBs.replace(text));
    }

}
