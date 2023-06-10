package com.github.houbb.sensitive.word.support.format;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.api.IWordContext;

import java.util.List;

/**
 * 忽略中文样式
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class WordFormatIgnoreChineseStyle implements IWordFormat {

    private static final IWordFormat INSTANCE = new WordFormatIgnoreChineseStyle();

    public static IWordFormat getInstance() {
        return INSTANCE;
    }

    @Override
    public char format(char original, IWordContext context) {
        List<String> mappingList = ZhConverterUtil.toSimple(original);
        if(CollectionUtil.isEmpty(mappingList)) {
            return original;
        }

        return mappingList.get(0).charAt(0);
    }

}
