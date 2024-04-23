package com.github.houbb.sensitive.word.data;

import com.github.houbb.heaven.util.io.FileUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * 数据初始化
 * @author binbin.hou
 * @since 0.9.0
 */
@Ignore
public class DictRemoveSingleTest {

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
    public void removeSingleWord() {
        final String sourceFile = "D:\\code\\github\\sensitive-word\\src\\test\\resources\\dict_20231117.txt";
        final String targetFile = "D:\\code\\github\\sensitive-word\\src\\main\\resources\\sensitive_word_dict.txt";

        List<String> words = FileUtil.readAllLines(sourceFile);

        for(String word : words) {
            String wordTrim = word.trim();
            if(wordTrim.length() > 1) {
                FileUtil.append(targetFile, wordTrim);
            }
        }
    }

}
