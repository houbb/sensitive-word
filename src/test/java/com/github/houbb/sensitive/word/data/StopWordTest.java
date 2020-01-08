package com.github.houbb.sensitive.word.data;

import com.github.houbb.heaven.support.condition.ICondition;
import com.github.houbb.heaven.support.filter.IFilter;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CharsetUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * 停止词数据初始化
 * @author binbin.hou
 * @since 0.0.3
 */
public class StopWordTest {

    /**
     * 中文测试
     * @since 0.0.3
     */
    @Test
    @Ignore
    public void zhTest() {
        final String sourceFile = "stopword.txt";
        final String targetFile = "D:\\github\\sensitive-word\\src\\main\\resources\\stopword_zh.txt";

        List<String> allLines = DataUtil.distinctLines(sourceFile);

        List<String> zhLines = CollectionUtil.conditionList(allLines, new ICondition<String>() {
            @Override
            public boolean condition(String s) {
                return CharsetUtil.isAllChinese(s);
            }
        });

        FileUtil.write(targetFile, zhLines);
    }

}
