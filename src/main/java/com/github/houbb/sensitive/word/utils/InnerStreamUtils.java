package com.github.houbb.sensitive.word.utils;

import com.github.houbb.heaven.util.io.StreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * @since 0.27.1
 */
public class InnerStreamUtils {

    /**
     * 获取文件，兼容为空的场景
     * @param path 路径
     * @return 结果
     */
    public static List<String> readAllLines(String path) {
        try(InputStream inputStream = StreamUtil.class.getResourceAsStream(path);) {
            if(inputStream == null) {
                return Collections.emptyList();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return StreamUtil.readAllLines(path);
    }

}

