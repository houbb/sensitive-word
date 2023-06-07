package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.util.regex.RegexUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.AppConst;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;

/**
 * URL 正则表达式检测实现。
 *
 * 也可以严格的保留下来。
 *
 * （1）暂时先粗略的处理 web-site
 * （2）如果网址的最后为图片类型，则跳过。
 * （3）长度超过 70，直接结束。
 *
 * @author binbin.hou
 * @since 0.0.9
 */
@ThreadSafe
public class SensitiveCheckUrl extends AbstractSensitiveCheck {

    /**
     * @since 0.3.0
     */
    private static final ISensitiveCheck INSTANCE = new SensitiveCheckUrl();

    public static ISensitiveCheck getInstance() {
        return INSTANCE;
    }

    @Override
    protected boolean isCharCondition(char mappingChar, int index, String rawText, IWordContext context) {
        return CharUtil.isWebSiteChar(mappingChar);
    }

    @Override
    protected boolean isStringCondition(int index, String rawText, StringBuilder stringBuilder, IWordContext context) {
        int bufferLen = stringBuilder.length();
        if(bufferLen > AppConst.MAX_WEB_SITE_LEN) {
            return false;
        }

        String string = stringBuilder.toString();
        return RegexUtil.isWebSite(string);
    }

    @Override
    protected Class<? extends ISensitiveCheck> getSensitiveCheckClass() {
        return SensitiveCheckUrl.class;
    }

}
