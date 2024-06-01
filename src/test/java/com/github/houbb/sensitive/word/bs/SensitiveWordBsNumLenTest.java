package com.github.houbb.sensitive.word.bs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.2.1
 */
public class SensitiveWordBsNumLenTest {

    /**
     * 返回所有敏感词
     * @since 0.2.1
     */
    @Test
    public void findAllTest() {
        final String text = "你懂得：12345678";

        // 默认检测 8 位
        List<String> wordList = SensitiveWordBs.newInstance()
                .enableNumCheck(true)
                .init().findAll(text);
        Assert.assertEquals("[12345678]", wordList.toString());

        // 指定数字的长度，避免误杀
        List<String> wordList2 = SensitiveWordBs.newInstance()
                .enableNumCheck(true)
                .numCheckLen(9)
                .init()
                .findAll(text);
        Assert.assertEquals("[]", wordList2.toString());
    }


}
