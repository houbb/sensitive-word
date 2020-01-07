package com.github.houbb.sensitive.word;

import com.github.houbb.heaven.support.filter.IFilter;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CharsetUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.util.CharsetUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
public class SplitTest {

    @Test
    @Ignore
    public void splitTest() {
        final String targetFile = "D:\\github\\sensitive-word\\src\\main\\resources\\dict4.txt";
        final String sourceFile = "D:\\github\\sensitive-word\\src\\main\\resources\\_minganci.txt";

        String string = FileUtil.getFileContent(sourceFile);

        List<String> words = StringUtil.splitToList(string, "\\|");
        FileUtil.write(targetFile, words);
    }

    @Test
    @Ignore
    public void trimTest() {
        final String source = "D:\\github\\sensitive-word\\src\\main\\resources\\dict.txt";
        List<String> lines = FileUtil.readAllLines(source);
        List<String> trimLines = CollectionUtil.distinct(CollectionUtil.trimCollection(lines));

        final String target = "D:\\github\\sensitive-word\\src\\main\\resources\\dict.txt";
        FileUtil.write(target, trimLines);
    }

    private List<String> distinctLines(final String name) {
        final String dir = "D:\\github\\sensitive-word\\src\\main\\resources\\";
        final String path = dir + name;
        List<String> lines = FileUtil.readAllLines(path);
        return CollectionUtil.distinct(lines);
    }

    @Test
    public void stopWordTest() {
        final String source = "D:\\github\\sensitive-word\\src\\main\\resources\\stopword.txt";
        final String target = "D:\\github\\sensitive-word\\src\\main\\resources\\stopword_chars.txt";

        final List<String> lines = FileUtil.readAllLines(source);

        List<String> resultList = CollectionUtil.distinct(CollectionUtil.filterList(lines, new IFilter<String>() {
            @Override
            public boolean filter(String s) {
                return CharsetUtils.isContainsChinese(s);
            }
        }));
        Collections.sort(resultList);
        FileUtil.write(target, resultList);
    }

}
