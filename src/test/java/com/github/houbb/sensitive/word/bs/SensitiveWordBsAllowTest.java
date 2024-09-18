package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import com.github.houbb.sensitive.word.support.replace.WordReplaces;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.21.0
 */
public class SensitiveWordBsAllowTest {

    /**
     * 是否包含
     *
     * https://github.com/houbb/sensitive-word/issues/76
     *
     * @since 0.0.1
     */
    @Test
    public void findAllowTest() {
        final String text = "三黄片黄片";

        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(new IWordAllow() {
                    @Override
                    public List<String> allow() {
                        return Arrays.asList("三黄片");
                    }
                })
                .init();

        Assert.assertEquals("[黄片]", sensitiveWordBs.findAll(text).toString());
    }

    /**
     * https://github.com/houbb/sensitive-word/issues/19
     *
     * @since 0.21.0
     */
    @Test
    public void bug19FixTest() {
        final String text = "共产党是白名单不会被检测";
        final String text2 = "共产党是白名单不会被检测，但是共产是黑名单";

        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(new IWordAllow() {
                    @Override
                    public List<String> allow() {
                        return Arrays.asList("共产党");
                    }
                })
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("政府", "国家", "共产");
                    }
                })
                .init();

        Assert.assertEquals("[]", sensitiveWordBs.findAll(text).toString());
        Assert.assertEquals("[共产]", sensitiveWordBs.findAll(text2).toString());
    }

}
