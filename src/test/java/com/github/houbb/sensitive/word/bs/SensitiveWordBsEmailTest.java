package com.github.houbb.sensitive.word.bs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.0.6
 */
public class SensitiveWordBsEmailTest {

    /**
     * 邮箱测试
     * @since 0.0.9
     */
    @Test
    public void emailTest() {
        final String text = "楼主好人，邮箱 123456789@qq.com";

        List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
        Assert.assertEquals("[五星紅旗]", wordList.toString());
    }

}
