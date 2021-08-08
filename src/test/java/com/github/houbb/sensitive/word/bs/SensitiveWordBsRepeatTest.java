package com.github.houbb.sensitive.word.bs;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.0.7
 */
public class SensitiveWordBsRepeatTest {

    /**
     * 忽略重复词
     * @since 0.0.7
     */
    @Test
    public void ignoreChineseStyleTest() {
        final String text = "ⒻⒻⒻfⓤuⓤ⒰cⓒ⒦ the bad words";

        List<String> wordList = SensitiveWordBs.newInstance()
                .ignoreRepeat(true)
                .findAll(text);
        Assert.assertEquals("[ⒻⒻⒻfⓤuⓤ⒰cⓒ⒦]", wordList.toString());
    }

}
