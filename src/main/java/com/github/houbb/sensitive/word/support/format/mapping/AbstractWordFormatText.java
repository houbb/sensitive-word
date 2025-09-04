package com.github.houbb.sensitive.word.support.format.mapping;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordFormatText;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 抽象实现
 * @author binbin.hou
 * @since 0.28.0
 */
public abstract class AbstractWordFormatText implements IWordFormatText {

    protected abstract Map<Character, Character> doFormat(String text, IWordContext context);

    @Override
    public Map<Character, Character> format(String text, IWordContext context) {
        if(StringUtil.isEmpty(text)) {
            return Collections.emptyMap();
        }

        return doFormat(text, context);
    }

}
