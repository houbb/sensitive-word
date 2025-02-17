package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.util.util.regex.RegexUtil;
import com.github.houbb.sensitive.word.api.IWordCheck;

/**
 * （1）暂时先粗略的处理 web-site
 * （2）如果网址的最后为图片类型，则跳过。
 * （3）长度超过 70，直接结束。
 *
 * 不包含前缀的实现策略
 *
 * @author binbin.hou
 * @since 0.25.0
 */
public class WordCheckUrlNoPrefix extends WordCheckUrl {

    /**
     * @since 0.3.0
     */
    private static final IWordCheck INSTANCE = new WordCheckUrlNoPrefix();

    public static IWordCheck getInstance() {
        return INSTANCE;
    }

    @Override
    protected boolean isUrl(String text) {
        return RegexUtil.isWebSite(text);
    }

}
