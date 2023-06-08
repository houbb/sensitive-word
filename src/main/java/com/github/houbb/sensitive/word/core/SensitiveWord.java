package com.github.houbb.sensitive.word.core;

import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.sensitive.word.api.ISensitiveWord;
import com.github.houbb.sensitive.word.api.ISensitiveWordReplaceContext;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;
import com.github.houbb.sensitive.word.support.check.SensitiveCheckResult;
import com.github.houbb.sensitive.word.support.check.impl.SensitiveCheckUrl;
import com.github.houbb.sensitive.word.support.replace.SensitiveWordReplaceContext;
import com.github.houbb.sensitive.word.support.result.WordResult;

import java.util.List;

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
        return innerSensitiveWords(string, ValidModeEnum.FAIL_OVER, context);
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
                                                  final ValidModeEnum modeEnum,
                                                  final IWordContext context) {
        //1. 是否存在敏感词，如果比存在，直接返回空列表
        final ISensitiveCheck sensitiveCheck = context.sensitiveCheck();
        List<IWordResult> resultList = Guavas.newArrayList();

        //TODO: 这里拆分为2个部分，从而保障性能。但是要注意处理下标的问题。
        //1. 原始的敏感词部分
        //2. email/url/num 的单独一次遍历处理。
        for (int i = 0; i < text.length(); i++) {
            SensitiveCheckResult checkResult = sensitiveCheck.sensitiveCheck(text, i, ValidModeEnum.FAIL_OVER, context);

            // 命中
            int wordLength = checkResult.index();
            if (wordLength > 0) {
                // 保存敏感词
                WordResult wordResult = WordResult.newInstance()
                        .startIndex(i)
                        .endIndex(i+wordLength);
                resultList.add(wordResult);

                // 快速返回
                if (ValidModeEnum.FAIL_FAST.equals(modeEnum)) {
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
