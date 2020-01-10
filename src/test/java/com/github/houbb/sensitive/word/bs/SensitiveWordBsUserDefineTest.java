package com.github.houbb.sensitive.word.bs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.0.8
 */
public class SensitiveWordBsUserDefineTest {

    /**
     * 自定义允许和拒绝的文件
     * @since 0.0.8
     */
    @Test
    public void allowAndDenyTest() {
        final String text = "gender 我们认为应该通过，自定义敏感词我们认为应该拒绝。";

        List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
        Assert.assertEquals("[自定义敏感词]", wordList.toString());
    }

}
