package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.util.regex.RegexUtil;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.WordConst;

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
public class WordCheckUrl extends AbstractConditionWordCheck {

    /**
     * @since 0.3.0
     */
    private static final IWordCheck INSTANCE = new WordCheckUrl();

    public static IWordCheck getInstance() {
        return INSTANCE;
    }

    @Override
    protected Class<? extends IWordCheck> getSensitiveCheckClass() {
        return WordCheckUrl.class;
    }

    @Override
    protected boolean isCharCondition(char mappingChar, int index, InnerSensitiveWordContext checkContext) {
        return CharUtil.isWebSiteChar(mappingChar);
    }

    @Override
    protected boolean isStringCondition(int index, StringBuilder stringBuilder, InnerSensitiveWordContext checkContext) {
        int bufferLen = stringBuilder.length();
        //a.cn
        if(bufferLen < 4) {
            return false;
        }
        if(bufferLen > WordConst.MAX_WEB_SITE_LEN) {
            return false;
        }

        String string = stringBuilder.toString();
        return RegexUtil.isWebSite(string);
    }

}
