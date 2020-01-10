package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.util.regex.RegexUtil;
import com.github.houbb.sensitive.word.api.ISensitiveCheck;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
import com.github.houbb.sensitive.word.support.format.CharFormatChain;

/**
 * email 正则表达式检测实现。
 * @author binbin.hou
 * @since 0.0.9
 */
@ThreadSafe
public class SensitiveEmailCheck implements ISensitiveCheck {

    @Override
    public int checkSensitive(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        // 记录敏感词的长度
        int lengthCount = 0;
        int actualLength = 0;

        StringBuilder stringBuilder = new StringBuilder();
        // 这里偷懒直接使用 String 拼接，然后结合正则表达式。
        // DFA 本质就可以做正则表达式，这样实现不免性能会差一些。
        // 后期如果有想法，对 DFA 进一步深入学习后，将进行优化。
        for(int i = beginIndex; i < txt.length(); i++) {
            char currentChar = txt.charAt(i);
            char mappingChar = Instances.singleton(CharFormatChain.class)
                    .format(currentChar, context);

            if(isEmailChar(mappingChar)) {
                lengthCount++;
                stringBuilder.append(currentChar);

                if(isCondition(stringBuilder.toString())) {
                    actualLength = lengthCount;
                    break;
                }
            } else {
                break;
            }
        }

        return actualLength;
    }

    /**
     * 这里指定一个阈值条件
     * @param string 长度
     * @return 是否满足条件
     * @since 0.0.9
     */
    private boolean isCondition(final String string) {
        return RegexUtil.isEmail(string);
    }

    /**
     * 是否为组成 email 的字符
     * @param c 字符
     * @return 结果
     * @since 0.0.9
     */
    private boolean isEmailChar(final char c) {
        return CharUtil.isDigitOrLetter(c)
                || c == '.' || c == '@';
    }

}
