package com.github.houbb.sensitive.word.support.data;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.constant.AppConst;

import java.util.List;

/**
 * 数据加载使用单例的模式，只需要加载一次即可。
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class SensitiveWordData implements IWordData {

    /**
     * 默认的内置行
     *
     * @since 0.0.1
     */
    private static List<String> defaultLines;

    static {
        synchronized (SensitiveWordData.class) {
            long start = System.currentTimeMillis();
            defaultLines = Guavas.newArrayList(AppConst.DICT_SIZE);
            defaultLines = StreamUtil.readAllLines("/dict.txt");
            long end = System.currentTimeMillis();
            System.out.println("Sensitive data loaded!, cost time: " + (end - start) + " ms");
        }
    }


    @Override
    public List<String> getWordData() {
        return defaultLines;
    }

}
