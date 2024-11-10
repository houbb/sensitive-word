package com.github.houbb.sensitive.word.bs;

import org.junit.Test;

import java.util.List;

public class SensitiveWordBsBigDataTest {
    @Test
    public void findAllInLongTextTest() {
        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            longText.append("这是我的微信：9989123456。");
        }

        List<String> wordList = SensitiveWordBs.newInstance()
                .enableNumCheck(true)
                .init().findAll(longText.toString());
        System.out.println(wordList.toString());
    }
}
