package com.github.houbb.sensitive.word.support.data;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.util.StreamUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据加载使用单例的模式，只需要加载一次即可。
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class SensitiveWordData implements IWordData {

    /**
     * 默认的内置行
     * @since 0.0.1
     */
    private static List<String> defaultLines;

    static {
        defaultLines = new ArrayList<>(183837);
        defaultLines = StreamUtils.readAllLines("/dict.txt");
    }

    @Override
    public List<String> getWordData() {
        return defaultLines;
    }

}
