package com.github.houbb.sensitive.word.core;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.sensitive.word.api.*;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordTypeEnum;
import com.github.houbb.sensitive.word.constant.enums.WordValidModeEnum;
import com.github.houbb.sensitive.word.support.check.WordCheckResult;
import com.github.houbb.sensitive.word.support.check.WordCheckWordAllow;
import com.github.houbb.sensitive.word.support.result.WordResult;
import com.github.houbb.sensitive.word.utils.InnerWordFormatUtils;

import java.util.List;
import java.util.Map;

/**
 * 默认实现
 *
 * @since 0.3.2
 */
public class SensitiveWord extends AbstractSensitiveWord {

    /**
     * 0.3.2
     */
    private static final ISensitiveWord INSTANCE = new SensitiveWord();

    public static ISensitiveWord getInstance() {
        return INSTANCE;
    }

    @Override
    protected List<IWordResult> doFindAll(String string, IWordContext context) {
        return innerSensitiveWords(string, WordValidModeEnum.FAIL_OVER, context);
    }

    /**
     * 获取敏感词列表
     *
     * @param text     文本
     * @param modeEnum 模式
     * @return 结果列表
     * @since 0.0.1
     */
    private List<IWordResult> innerSensitiveWords(final String text,
                                                  final WordValidModeEnum modeEnum,
                                                  final IWordContext context) {
        //1. 是否存在敏感词，如果比存在，直接返回空列表
        final IWordCheck sensitiveCheck = context.sensitiveCheck();
        List<IWordResult> resultList = Guavas.newArrayList();

        //TODO: 这里拆分为2个部分，从而保障性能。但是要注意处理下标的问题。
        //1. 原始的敏感词部分
        //2. email/url/num 的单独一次遍历处理。
        final Map<Character, Character> characterCharacterMap = InnerWordFormatUtils.formatCharsMapping(text, context);
        final InnerSensitiveWordContext checkContext = InnerSensitiveWordContext.newInstance()
                .originalText(text)
                .wordContext(context)
                .modeEnum(WordValidModeEnum.FAIL_OVER)
                .formatCharMapping(characterCharacterMap);
        final IWordResultCondition wordResultCondition = context.wordResultCondition();

        final IWordCheck wordCheckAllow = new WordCheckWordAllow();

        for (int i = 0; i < text.length(); i++) {
            // v0.21.0 白名单跳过 TODO: 感觉这种实现性能一般，考虑后续优化。
            WordCheckResult wordCheckAllowResult = wordCheckAllow.sensitiveCheck(i, checkContext);
            int wordLengthAllow = wordCheckAllowResult.index();
            if(wordLengthAllow > 0) {
                i += wordLengthAllow-1;
                continue;
            }

            WordCheckResult checkResult = sensitiveCheck.sensitiveCheck(i, checkContext);

            // 命中
            int wordLength = checkResult.index();
            if (wordLength > 0) {
                // 保存敏感词
                WordResult wordResult = WordResult.newInstance()
                        .startIndex(i)
                        .endIndex(i+wordLength)
                        .type(checkResult.type());
                //v0.13.0 添加判断
                if(wordResultCondition.match(wordResult, text, modeEnum, context)) {
                    resultList.add(wordResult);
                }

                // 快速返回
                if (WordValidModeEnum.FAIL_FAST.equals(modeEnum)) {
                    break;
                }

                // 增加 i 的步长
                // 为什么要-1，因为默认就会自增1
                // TODO: 这里可以根据字符串匹配算法优化。
                i += wordLength - 1;
            }
        }

        return resultList;
    }

}
