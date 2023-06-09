package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveContext;

import java.util.Map;

/**
 * 抽象实现策略
 *
 * @author binbin.hou
 * @since 0.3.2
 */
@ThreadSafe
public abstract class AbstractConditionSensitiveCheck extends AbstractSensitiveCheck {

    /**
     * 当前字符串是否符合规范
     * @param mappingChar 当前字符
     * @param index 下标
     * @param checkContext 校验文本
     * @return 结果
     * @since 0.3.2
     */
    protected abstract boolean isCharCondition(char mappingChar, int index, InnerSensitiveContext checkContext);

    /**
     * 这里指定一个阈值条件
     * @param index 当前下标
     * @param stringBuilder 缓存
     * @param checkContext 上下文
     * @return 是否满足条件
     * @since 0.3.2
     */
    protected abstract boolean isStringCondition(int index,
                                               final StringBuilder stringBuilder, InnerSensitiveContext checkContext);

    @Override
    protected int getActualLength(int beginIndex, InnerSensitiveContext checkContext) {
        final String txt = checkContext.originalText();
        final IWordContext context = checkContext.wordContext();
        final Map<Character, Character> formatCharMapping = checkContext.formatCharMapping();

        int actualLength = 0;

        // 采用 ThreadLocal 应该可以提升性能，减少对象的创建。
        StringBuilder stringBuilder = new StringBuilder();
        int currentIx = 0;
        for(int i = beginIndex; i < txt.length(); i++) {
            currentIx = i;
            char currentChar = txt.charAt(i);
            // 映射处理
            char mappingChar = formatCharMapping.get(currentChar);

            // 符合条件
            boolean currentCondition = isCharCondition(mappingChar, i, checkContext);

            //4 个场景
            if(currentCondition) {
                stringBuilder.append(currentChar);
            } else {
                break;
            }
        }

        // 匹配
        if(isStringCondition(currentIx, stringBuilder, checkContext)) {
            actualLength = stringBuilder.length();
        }

        return actualLength;
    }

}
