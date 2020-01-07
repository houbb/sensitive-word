package com.github.houbb.sensitive.word.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public class StreamUtilsTest {

    @Test
    public void sizeTest() {
        final String dictPath = "/dict.txt";

        List<String> stringList = StreamUtils.readAllLines(dictPath);
        Assert.assertEquals(183837, stringList.size());
    }

}
