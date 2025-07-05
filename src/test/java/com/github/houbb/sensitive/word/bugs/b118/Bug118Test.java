package com.github.houbb.sensitive.word.bugs.b118;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.check.WordChecks;
import com.github.houbb.sensitive.word.support.ignore.SensitiveWordCharIgnores;
import org.junit.Assert;
import org.junit.Test;

public class Bug118Test {

    @Test
    public void test() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .charIgnore(SensitiveWordCharIgnores.specialChars())
                .wordCheckNum(WordChecks.num())
                .numCheckLen(8)
                .enableNumCheck(true)
                .init();

        Assert.assertEquals(sensitiveWordBs.findFirst("1234567===0001哈哈哈"), "1234567===0001");
        Assert.assertEquals(sensitiveWordBs.findFirst("12345670002 哈哈哈"), "12345670002");
        Assert.assertEquals(sensitiveWordBs.findFirst("=====123456====70002 哈哈哈"), "=====123456====70002");
        Assert.assertEquals(sensitiveWordBs.findFirst("=====123456====X70002 哈哈哈"), null);
    }

}
