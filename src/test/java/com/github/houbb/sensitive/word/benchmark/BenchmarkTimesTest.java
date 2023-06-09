package com.github.houbb.sensitive.word.benchmark;

import com.github.houbb.heaven.util.util.RandomUtil;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class BenchmarkTimesTest {

    /**
     * 测试基准：100+字符串 * 10W次
     *
     * V0.6.0: 1470ms，接近 7.2W QPS
     */
    @Test
    public void onlyWordAndNoReplaceTest() {
        // 1W 次
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .enableWordCheck(true)
                .enableNumCheck(false)
                .enableUrlCheck(false)
                .enableEmailCheck(false)
                .ignoreRepeat(false)
                .ignoreCase(false)
                .ignoreNumStyle(false)
                .ignoreChineseStyle(false)
                .ignoreEnglishStyle(false)
                .ignoreWidth(false)
                .init();

        String randomText = "你他妈的不要说脏话"+ RandomUtil.randomString("1234567890bcdefghiJKLMNOPQRSTUVWXYZ", 100)
                + "我们他妈的从来不说脏说";

        long start = System.currentTimeMillis();
        for(int i = 0; i < 100_000; i++) {
            sensitiveWordBs.findAll(randomText);
        }
        long end = System.currentTimeMillis();
        System.out.println("------------------ COST: " + (end-start));
    }

    /**
     * 测试基准：100+字符串 * 10W次
     *
     * V0.6.0: 2744ms, 约 3.7W QPS
     */
    @Test
    public void onlyWordAndWithReplaceTest() {
        // 1W 次
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .enableWordCheck(true)
                .enableNumCheck(false)
                .enableUrlCheck(false)
                .enableEmailCheck(false)
                .ignoreRepeat(true)
                .ignoreCase(true)
                .ignoreNumStyle(true)
                .ignoreChineseStyle(true)
                .ignoreEnglishStyle(true)
                .ignoreWidth(true)
                .init();

        String randomText = "你他妈的不要说脏话"+ RandomUtil.randomString("1234567890bcdefghiJKLMNOPQRSTUVWXYZ", 100)
                + "我们他妈的从来不说脏说";

        long start = System.currentTimeMillis();
        for(int i = 0; i < 100_000; i++) {
            sensitiveWordBs.findAll(randomText);
        }
        long end = System.currentTimeMillis();
        System.out.println("------------------ COST: " + (end-start));
    }

}
