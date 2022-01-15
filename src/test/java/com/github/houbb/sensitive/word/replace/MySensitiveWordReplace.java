package com.github.houbb.sensitive.word.replace;

import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.sensitive.word.api.ISensitiveWordReplace;
import com.github.houbb.sensitive.word.api.ISensitiveWordReplaceContext;

/**
 * 自定义敏感词替换策略
 *
 * @author binbin.hou
 * @since 0.2.0
 */
public class MySensitiveWordReplace implements ISensitiveWordReplace {

    @Override
    public String replace(ISensitiveWordReplaceContext context) {
        String sensitiveWord = context.sensitiveWord();
        // 自定义不同的敏感词替换策略，可以从数据库等地方读取
        if("五星红旗".equals(sensitiveWord)) {
            return "国家旗帜";
        }
        if("毛主席".equals(sensitiveWord)) {
            return "教员";
        }

        // 其他默认使用 * 代替
        int wordLength = context.wordLength();
        return CharUtil.repeat('*', wordLength);
    }

}
