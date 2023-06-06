package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.util.regex.RegexUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;
import com.github.houbb.sensitive.word.support.check.SensitiveCheckResult;

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
public class SensitiveCheckEmail implements ISensitiveCheck {

    /**
     * @since 0.3.0
     */
    private static final ISensitiveCheck INSTANCE = new SensitiveCheckEmail();

    public static ISensitiveCheck getInstance() {
        return INSTANCE;
    }

    @Override
    public SensitiveCheckResult sensitiveCheck(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        // 记录敏感词的长度
        int lengthCount = 0;
        int actualLength = 0;

        StringBuilder stringBuilder = new StringBuilder();
        // 这里偷懒直接使用 String 拼接，然后结合正则表达式。
        // DFA 本质就可以做正则表达式，这样实现不免性能会差一些。
        // 后期如果有想法，对 DFA 进一步深入学习后，将进行优化。
        for(int i = beginIndex; i < txt.length(); i++) {
            char currentChar = txt.charAt(i);
            char mappingChar = context.charFormat()
                    .format(currentChar, context);

            if(CharUtil.isEmilChar(mappingChar)) {
                lengthCount++;
                stringBuilder.append(currentChar);

                if(isCondition(stringBuilder.toString())) {
                    actualLength = lengthCount;

                    // 是否遍历全部匹配的模式
                    if(ValidModeEnum.FAIL_FAST.equals(validModeEnum)) {
                        break;
                    }
                }
            } else {
                break;
            }
        }

        return SensitiveCheckResult.of(actualLength, SensitiveCheckEmail.class);
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

}
