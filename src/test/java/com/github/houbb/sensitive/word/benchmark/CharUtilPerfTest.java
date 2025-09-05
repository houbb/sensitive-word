package com.github.houbb.sensitive.word.benchmark;

import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.sensitive.word.utils.InnerCharUtils;

public class CharUtilPerfTest {


    private static final int COUNT = 10_00_000;

    public static void main(String[] args) {
        char[] testData = new char[COUNT];
        for (int i = 0; i < COUNT; i++) {
            testData[i] = (char) ('A' + (i % 52)); // A-Z a-z
        }

        // 测试新小写
        // 测试原始半角
        char[] fullWidthData = new char[COUNT];
        for (int i = 0; i < COUNT; i++) {
            fullWidthData[i] = (char) ('\uFF01' + (i % 94)); // 常见全角字符
        }

        long t5 = System.currentTimeMillis();
        char sum3 = 0;
        for (char c : fullWidthData) {
            sum3 += CharUtil.toHalfWidth(c);
        }
        long t6 = System.currentTimeMillis();
        System.out.println("原始 toHalfWidth 耗时: " + (t6 - t5) + "ms, sum=" + sum3);

        // 测试新半角
        long t7 = System.currentTimeMillis();
        char sum4 = 0;
        for (char c : fullWidthData) {
            sum4 += InnerCharUtils.toHalfWidth(c);
        }
        long t8 = System.currentTimeMillis();
        System.out.println("优化 toHalfWidth 耗时: " + (t8 - t7) + "ms, sum=" + sum4);
    }


}
