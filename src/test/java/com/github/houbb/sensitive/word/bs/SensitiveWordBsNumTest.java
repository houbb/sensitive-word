package com.github.houbb.sensitive.word.bs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.0.5
 */
public class SensitiveWordBsNumTest {

    /**
     * 返回所有敏感词
     * @since 0.0.5
     */
    @Test
    public void findAllTest() {
        final String text = "这个是我的微信：9989123456";

        List<String> wordList = SensitiveWordBs.newInstance().init().findAll(text);
        Assert.assertEquals("[微信, 9989123456]", wordList.toString());
    }

    /**
     * 返回所有敏感词
     * @since 0.0.5
     */
    @Test
    public void ignoreNumStyleTest() {
        final String text = "这个是我的微信：9⓿二肆⁹₈③⑸⒋➃㈤㊄";

        List<String> wordList = SensitiveWordBs.newInstance().init().findAll(text);
        Assert.assertEquals("[微信, 9⓿二肆⁹₈③⑸⒋➃㈤㊄]", wordList.toString());
    }

}
