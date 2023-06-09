package com.github.houbb.sensitive.word.benchmark;

import com.github.houbb.heaven.util.util.RandomUtil;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class BenchmarkBasicTest {

    /**
     *
     *
     * 100*100 耗时：926ms，性能較差。
     *
     * 100*100000 的字符：
     *
     * 12942ms 第一次优化。
     * 12983ms 添加对应的 contains 优化，性能无太大变化。
     *
     */
    @Test
    public void costTimeTest() {
        String randomText = "你他妈的不要说脏话"+ RandomUtil.randomString("1234567890bcdefghiJKLMNOPQRSTUVWXYZ", 100)
                + "我们他妈的从来不说脏说";


        // 1W 次
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++) {
            SensitiveWordHelper.findAll(randomText);
        }
        long end = System.currentTimeMillis();
        System.out.println("------------------ COST: " + (end-start));
    }

    /**
     *
     * 100*100000 的字符：12440ms
     *
     * 12111 第一次优化
     *
     * 1133 只有单词校验
     *
     * V0.6.0 优化 replace mapping 之后：621ms，性能接近翻倍。
     */
    @Test
    public void costTimeOnlyWordTest() {
        String randomText = "你他妈的不要说脏话"+ RandomUtil.randomString("1234567890bcdefghiJKLMNOPQRSTUVWXYZ", 100)
                + "我们他妈的从来不说脏说";

        // 1W 次
        long start = System.currentTimeMillis();
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .enableWordCheck(true)
                .enableNumCheck(false)
                .enableUrlCheck(false)
                .enableEmailCheck(false)
                .init();

        for(int i = 0; i < 10000; i++) {
            sensitiveWordBs.findAll(randomText);
        }
        long end = System.currentTimeMillis();
        System.out.println("------------------ COST: " + (end-start));
    }

    /**
     *
     * COST: 1540-pc
     */
    @Test
    public void costTimeOnlyNumTest() {
        String randomText = "你他妈的不要说脏话"+ RandomUtil.randomString("1234567890bcdefghiJKLMNOPQRSTUVWXYZ", 100)
                + "我们他妈的从来不说脏说";

        // 1W 次
        long start = System.currentTimeMillis();
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .enableWordCheck(false)
                .enableNumCheck(true)
                .enableUrlCheck(false)
                .enableEmailCheck(false)
                .init();

        for(int i = 0; i < 10000; i++) {
            sensitiveWordBs.findAll(randomText);
        }
        long end = System.currentTimeMillis();
        System.out.println("------------------ COST: " + (end-start));
    }

    /**
     *
     * COST: 20284-pc
     */
    @Test
    public void costTimeOnlyUrlTest() {
        String randomText = "你他妈的不要说脏话"+ RandomUtil.randomString("1234567890bcdefghiJKLMNOPQRSTUVWXYZ", 100)
                + "我们他妈的从来不说脏说";

        // 1W 次
        long start = System.currentTimeMillis();
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .enableWordCheck(false)
                .enableNumCheck(false)
                .enableUrlCheck(true)
                .enableEmailCheck(false)
                .init();

        for(int i = 0; i < 10000; i++) {
            sensitiveWordBs.findAll(randomText);
        }
        long end = System.currentTimeMillis();
        System.out.println("------------------ COST: " + (end-start));
    }

    /**
     *
     * COST: 19036-pc
     */
    @Test
    public void costTimeOnlyEmailTest() {
        String randomText = "你他妈的不要说脏话"+ RandomUtil.randomString("1234567890bcdefghiJKLMNOPQRSTUVWXYZ", 100)
                + "我们他妈的从来不说脏说";

        // 1W 次
        long start = System.currentTimeMillis();
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .enableWordCheck(false)
                .enableNumCheck(false)
                .enableUrlCheck(false)
                .enableEmailCheck(true)
                .init();

        for(int i = 0; i < 10000; i++) {
            sensitiveWordBs.findAll(randomText);
        }
        long end = System.currentTimeMillis();
        System.out.println("------------------ COST: " + (end-start));
    }

}
