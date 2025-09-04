package com.github.houbb.sensitive.word.support.format.mapping;

import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordFormat;
import com.github.houbb.sensitive.word.support.check.WordCheckNone;
import com.github.houbb.sensitive.word.support.format.WordFormatNone;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 默认实现
 *
 * @author d
 * @since 0.28.0
 */
public class WordFormatTextDefault extends AbstractWordFormatText {

    @Override
    protected Map<Character, Character> doFormat(String text, IWordContext context) {
        // 单个字符串里信息
        final IWordFormat wordFormat = context.wordFormat();
        // 不需要处理的场景
        if(wordFormat.getClass().getName().equals(WordFormatNone.class.getName())) {
            return Collections.emptyMap();
        }

        Map<Character, Character> map = new HashMap<>();
        for(int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char mc = wordFormat.format(c, context);

            if(c != mc) {
                map.put(c, mc);
            }
        }
        return map;
    }
    
}
