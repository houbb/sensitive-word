package com.github.houbb.sensitive.word.benchmark;

import com.github.houbb.heaven.util.util.RandomUtil;
import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

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
     *      * 黑白名单一次遍历 优化前：300*他们在地铁口交易，查10000次，26183
     *      * 黑白名单一次遍历 优化后：300*他们在地铁口交易，查10000次，15705
     *
     */
    @Test
    public void costTimeOneTraceTest() {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<300;i++){
            sb.append("他们在地铁口交易").append(i);
        }
        String text = sb.toString();

        // 1W 次
        long start = System.currentTimeMillis();
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Collections.singletonList("口交");
                    }
                })
                .wordAllow(new IWordAllow() {
                    @Override
                    public List<String> allow() {
                        return Collections.singletonList("地铁口交易");
                    }
                })
                .enableWordCheck(true)
                .enableNumCheck(false)
                .enableUrlCheck(false)
                .enableEmailCheck(false)
                .init();

        for(int i = 0; i < 10000; i++) {
            sensitiveWordBs.findAll(text);
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
