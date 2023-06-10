package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.util.regex.RegexUtil;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.WordConst;

/**
 * email 正则表达式检测实现。
 *
 * TODO: 这里暂时不实现邮箱后缀的实现。
 *
 * （1）命中结果应该有标记，属于哪一个验证模式命中
 * （2）后期优化方案可以是：
 * 如果数字后面紧跟的是邮箱后缀命中，则直接连接起来 num+email-suffix;
 * （3）邮箱后缀的去重
 * 邮箱后缀可以只处理为和 Num 构建，如果没有直接丢弃的模式。
 *
 * 也可以严格的保留下来。
 * @author binbin.hou
 * @since 0.0.9
 */
@ThreadSafe
public class WordCheckEmail extends AbstractConditionWordCheck {

    /**
     * @since 0.3.0
     */
    private static final IWordCheck INSTANCE = new WordCheckEmail();

    public static IWordCheck getInstance() {
        return INSTANCE;
    }

    @Override
    protected Class<? extends IWordCheck> getSensitiveCheckClass() {
        return WordCheckEmail.class;
    }

    @Override
    protected boolean isCharCondition(char mappingChar, int index, InnerSensitiveWordContext checkContext) {
        return CharUtil.isEmilChar(mappingChar);
    }

    @Override
    protected boolean isStringCondition(int index, StringBuilder stringBuilder, InnerSensitiveWordContext checkContext) {
        int bufferLen = stringBuilder.length();

        //x@a.cn
        if(bufferLen < 6) {
            return false;
        }
        if(bufferLen > WordConst.MAX_EMAIL_LEN) {
            return false;
        }

        String string = stringBuilder.toString();
        return RegexUtil.isEmail(string);
    }

}
