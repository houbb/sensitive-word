package com.github.houbb.sensitive.word.data;

import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.3
 */
public class DataUtil {

    /**
     * 获取对应文件的独一无二内容
     * @param name 名称
     * @return 结果
     * @since 0.0.1
     */
    public static List<String> distinctLines(final String name) {
        final String dir = "D:\\github\\sensitive-word\\src\\main\\resources\\";
        final String path = dir + name;
        List<String> lines = FileUtil.readAllLines(path);
        return CollectionUtil.distinct(lines);
    }

}
