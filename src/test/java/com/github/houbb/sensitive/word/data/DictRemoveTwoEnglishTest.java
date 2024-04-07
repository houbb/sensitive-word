package com.github.houbb.sensitive.word.data;

import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * 数据初始化
 * @author binbin.hou
 * @since 0.9.0
 */
@Ignore
public class DictRemoveTwoEnglishTest {

    public static void main(String[] args) {
        final String sourceFile = "D:\\github\\sensitive-word\\src\\main\\resources\\dict.txt";
        final String targetFile = "D:\\github\\sensitive-word\\src\\test\\resources\\dict_v20240407.txt";

        List<String> words = FileUtil.readAllLines(sourceFile);

        for(String word : words) {
            String wordTrim = word.trim();
            // 如果是2
            if(wordTrim.length() == 2 && StringUtil.isEnglish(wordTrim)) {
                System.out.println(word);
            } else {
                FileUtil.append(targetFile, wordTrim);
            }
        }
    }

}
