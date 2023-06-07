package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;
import com.github.houbb.sensitive.word.support.check.SensitiveCheckResult;

/**
 * 抽象实现策略
 *
 * @author binbin.hou
 * @since 0.3.2
 */
@ThreadSafe
public abstract class AbstractSensitiveCheck implements ISensitiveCheck {

    /**
     * 当前字符串是否符合规范
     * @param mappingChar 当前字符
     * @param index 下标
     * @param rawText 原始文本
     * @param context 上下文
     * @return 结果
     * @since 0.3.2
     */
    protected abstract boolean isCharCondition(char mappingChar,
                                      int index,
                                      String rawText,
                                      final IWordContext context);

    /**
     * 这里指定一个阈值条件
     * @param index 当前下标
     * @param rawText 原始文本
     * @param stringBuilder 缓存
     * @param context 上下文
     * @return 是否满足条件
     * @since 0.3.2
     */
    protected abstract boolean isStringCondition(int index,
                                               String rawText,
                                               final StringBuilder stringBuilder,
                                               final IWordContext context);

    /**
     * 获取校验类
     * @return 类
     * @since 0.3.2
     */
    protected abstract Class<? extends ISensitiveCheck> getSensitiveCheckClass();

    @Override
    public SensitiveCheckResult sensitiveCheck(String txt, int beginIndex,
                                               ValidModeEnum validModeEnum,
                                               IWordContext context) {
        // 采用 ThreadLocal 应该可以提升性能，减少对象的创建。
        StringBuilder stringBuilder = new StringBuilder();
        int actualLength = 0;
        // 前一个条件
        for(int i = beginIndex; i < txt.length(); i++) {
            char currentChar = txt.charAt(i);

            // 映射处理
            char mappingChar = context.charFormat().format(currentChar, context);

            // 符合条件
            boolean currentCondition = isCharCondition(mappingChar, i, txt, context);
            if(currentCondition) {
                stringBuilder.append(currentChar);

                // 匹配
                if(isStringCondition(i, txt, stringBuilder, context)) {
                    actualLength = stringBuilder.length();

                    // 是否遍历全部匹配的模式
                    if(ValidModeEnum.FAIL_FAST.equals(validModeEnum)) {
                        break;
                    }
                }
            } else {
                break;
            }
        }

        // 处理结果
        return SensitiveCheckResult.of(actualLength, getSensitiveCheckClass());
    }

}
