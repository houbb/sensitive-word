package com.github.houbb.sensitive.word.data;

import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 常用的 it 使用
 *
 * @author binbin.hou
 * @since 0.14.1
 */
@Ignore
public class DictRemoveCommonITUsageTest {

    /**
     * 统计自己的文章，移除常用的 it 用语等。降低误判率
     */
    @Test
    @Ignore
    public void removeSingleWord() {
        final String dir = "D:\\github\\houbb.github.io\\_posts";

        File[] files = new File(dir).listFiles();

        // 默认策略
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance().init();

        Set<String> allWords = new HashSet<>();
        for(File file : files) {
            String content = FileUtil.getFileContent(file);

            List<String> words = sensitiveWordBs.findAll(content);
            allWords.addAll(words);
        }

        System.out.println(allWords);
    }

}
