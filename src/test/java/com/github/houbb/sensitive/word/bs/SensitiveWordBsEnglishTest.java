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
public class SensitiveWordBsEnglishTest {

    /**
     * 忽略英文写法
     * @since 0.0.6
     */
    @Test
    public void ignoreEnglishStyleTest() {
        final String text = "Ⓕⓤc⒦ the bad words";

        List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
        Assert.assertEquals("[Ⓕⓤc⒦]", wordList.toString());
    }

}
