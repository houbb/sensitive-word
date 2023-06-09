package com.github.houbb.sensitive.word.memory;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.support.data.WordDatas;
import org.apache.lucene.util.RamUsageEstimator;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * 数据内存测试
 *
 * @since 0.7.0
 */
@Ignore
public class DataMemoryTest {

    /**
     * 35.5 MB
     */
    @Test
    public void hashMapTest() {
        List<String> allLines = StreamUtil.readAllLines("/dict.txt");
        IWordData wordData = WordDatas.defaults();

        wordData.initWordData(allLines);

        //计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
        String humanSize = RamUsageEstimator.humanSizeOf(wordData);
        System.out.println(humanSize);
    }


    //33.4 MB
    @Test
    public void treeTest() {
        List<String> allLines = StreamUtil.readAllLines("/dict.txt");
        IWordData wordData = WordDatas.tree();

        wordData.initWordData(allLines);

        //计算指定对象及其引用树上的所有对象的综合大小，返回可读的结果，如：2KB
        String humanSize = RamUsageEstimator.humanSizeOf(wordData);
        System.out.println(humanSize);
    }

}
