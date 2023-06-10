package com.github.houbb.sensitive.word.data;

import com.github.houbb.heaven.support.filter.IFilter;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.opencc4j.core.impl.ZhConvertBootstrap;
import com.github.houbb.opencc4j.support.segment.impl.CharSegment;
import com.github.houbb.sensitive.word.utils.InnerWordNumUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * 数据初始化
 * @author binbin.hou
 * @since 0.0.3
 */
@Ignore
public class DictSlimTest {

    /**
     * 统一格式
     *
     * 1. 将所有的大写字母统一转换为小写
     * 2. 将所有的全角转换为半角
     * 3. 移除所有【空格】【符号】(这个就是各种符号的过滤了)
     * 4. 繁体字统一转换为简体字
     * @since 0.0.3
     */
    @Test
    @Ignore
    public void formatTest() {
        final String sourceFile = "D:\\github\\sensitive-word\\src\\main\\resources\\dict.txt";
        final String targetFile = "D:\\github\\sensitive-word\\src\\main\\resources\\dict.txt";

        List<String> words = FileUtil.readAllLines(sourceFile);

        List<String> formats = CollectionUtil.toList(words, new IHandler<String, String>() {
            @Override
            public String handle(String string) {
                String lower = string.toLowerCase();
                String half = StringUtil.toHalfWidth(lower);
                String trim = StringUtil.trimAnyBlank(half);
                String punc = StringUtil.trimAnyPunctionAndSymbol(trim);
                return ZhConvertBootstrap.newInstance(new CharSegment()).toSimple(punc);
            }
        });

        List<String> resultList = DataUtil.disctinctAndSort(formats);
        FileUtil.write(targetFile, resultList);
    }

    /**
     * 移除测试
     *
     * 1. 移除 QQ 号的类似数字
     * 2. 移除所有网址（.com、cn、.org）
     * 3. 移除纯英文
     * 4. 移除乱码 `�`
     * 5. 移除英文+数字的
     *
     * @since 0.0.3
     */
    @Test
    @Ignore
    public void removeTest() {
        final String sourceFile = "D:\\github\\sensitive-word\\src\\main\\resources\\dict.txt";
        final String targetFile = "D:\\github\\sensitive-word\\src\\main\\resources\\dict.txt";

        List<String> words = FileUtil.readAllLines(sourceFile);

        List<String> formats = CollectionUtil.filterList(words, new IFilter<String>() {
            @Override
            public boolean filter(String string) {
                return StringUtil.isDigitOrLetter(string)
                        || string.contains("�")
                        || string.contains("删掉")
                        || isUrl(string);
            }
        });

        List<String> resultList = DataUtil.disctinctAndSort(formats);
        FileUtil.write(targetFile, resultList);
    }

    /**
     * 数字映射处理
     * @since 0.0.4
     */
    @Test
    public void removeNumberMappingTest() {
        final String sourceFile = "D:\\_github\\sensitive-word\\src\\main\\resources\\dict.txt";
        final String targetFile = "D:\\_github\\sensitive-word\\src\\main\\resources\\dict.txt";

        List<String> words = FileUtil.readAllLines(sourceFile);
        List<String> formats = CollectionUtil.toList(words, new IHandler<String, String>() {
            @Override
            public String handle(String s) {
                return s.replaceAll(" ", "");
            }
        });
        List<String> filters = CollectionUtil.filterList(formats, new IFilter<String>() {
            @Override
            public boolean filter(String string) {
                return isNumber(string);
            }
        });

        List<String> resultList = DataUtil.disctinctAndSort(filters);
        FileUtil.write(targetFile, resultList);
    }

    /**
     * 是否为存数字
     * （1）数字小于4的直接跳过。
     * @param string 原始字符串
     * @return 结果
     * @since 0.0.4
     */
    private static boolean isNumber(final String string) {
        if(string.length() <= 4) {
            return false;
        }

        // 停顿词语
        String trim = string.replaceAll("加|否|与|和", "");
        String mapString = InnerWordNumUtils.getMappingString(trim);
        boolean result = StringUtil.isDigit(mapString);
        if(result) {
            System.out.println(string);
        }
        return result;
    }

    private static boolean isUrl(final String string) {
        return string.endsWith(".com")
                || string.endsWith(".cn")
                || string.endsWith(".org");
    }

    public static void main(String[] args) {
        String trim = "1和2".replaceAll("加|否|与|和", "");
        System.out.println(trim);
    }

}
