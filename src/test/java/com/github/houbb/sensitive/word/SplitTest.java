package com.github.houbb.sensitive.word;

import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import org.junit.Ignore;
import org.junit.Test;

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
        final String sourceFile = "D:\\github\\sensitive-word\\src\\main\\resources\\minganci.txt";

        String string = FileUtil.getFileContent(sourceFile);

        List<String> words = StringUtil.splitToList(string, "\\|");
        FileUtil.write(targetFile, words);
    }

}
