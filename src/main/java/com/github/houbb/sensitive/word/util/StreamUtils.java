//package com.github.houbb.sensitive.word.util;
//
//import com.github.houbb.heaven.annotation.CommonEager;
//import com.github.houbb.heaven.constant.CharsetConst;
//import com.github.houbb.heaven.util.lang.StringUtil;
//import com.github.houbb.sensitive.word.exception.SensitiveWordException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.charset.Charset;
//import java.util.*;
//
///**
// * 流工具类
// * @author binbin.hou
// * @since 0.0.1
// */
//@CommonEager
//public final class StreamUtils {
//
//    private StreamUtils(){}
//
//    /**
//     * 构建数据集合
//     *
//     * 后期考虑：是否允许用户自定义字典？
//     * 目前不支持这些操作。后期如果需要，再把这些限制放开。
//     * @param path 文件路径
//     * @return 返回数据集合
//     * @since 0.0.1
//     */
//    public static List<String> readAllLines(final String path) {
//        return readAllLines(path, CharsetConst.UTF8, true);
//    }
//
//    /**
//     * 构建数据集合
//     *
//     * 后期考虑：是否允许用户自定义字典？
//     * 目前不支持这些操作。后期如果需要，再把这些限制放开。
//     * @param path 文件路径
//     * @param charset 文件编码
//     * @param ignoreEmpty 是否忽略空白行
//     * @return 返回数据集合
//     * @since 0.0.1
//     */
//    public static List<String> readAllLines(final String path,
//                                            final String charset,
//                                            final boolean ignoreEmpty) {
//        try {
//            List<String> lines = new ArrayList<>();
//            InputStream is = StreamUtils.class.getResourceAsStream(path);
//            BufferedReader e = new BufferedReader(new InputStreamReader(is,
//                    Charset.forName(charset)));
//
//            while (e.ready()) {
//                String entry = e.readLine();
//                if (StringUtil.isEmpty(entry)
//                    && ignoreEmpty) {
//                    continue;
//                }
//                lines.add(entry);
//            }
//            return lines;
//        } catch (IOException e) {
//            throw new SensitiveWordException("dict init failed!", e);
//        }
//    }
//
//}
