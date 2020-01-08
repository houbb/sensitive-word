//package com.github.houbb.sensitive.word.util;
//
//import com.github.houbb.heaven.annotation.CommonEager;
//import com.github.houbb.heaven.util.lang.StringUtil;
//
///**
// * @author binbin.hou
// * @since 0.0.1
// */
//@CommonEager
//public class CharsetUtils {
//
//    /**
//     * 是否为中文字符
//     * @param c char
//     * @return 是否
//     * @since 0.0.1
//     */
//    public static boolean isChinese(char c) {
//        boolean result = false;
//        // 汉字范围 \u4e00-\u9fa5 (中文)
//        if (c >= 19968 && c <= 171941) {
//            result = true;
//        }
//        return result;
//    }
//
//    /**
//     * 是否包含中文
//     * @param string 字符串
//     * @return 是否
//     * @since 0.0.1
//     */
//    public static boolean isContainsChinese(String string) {
//        if(StringUtil.isEmpty(string)) {
//            return false;
//        }
//
//        char[] chars = string.toCharArray();
//        for(char c : chars) {
//            if(isChinese(c)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    /**
//     * 是否全是中文
//     * @param string 字符串
//     * @return 是否
//     * @since 0.0.1
//     */
//    public static boolean isAllChinese(String string) {
//        if(StringUtil.isEmpty(string)) {
//            return false;
//        }
//
//        char[] chars = string.toCharArray();
//        for(char c : chars) {
//            if(!isChinese(c)) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//}
