package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.sensitive.word.api.ISensitiveCheck;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.AppConst;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
import com.github.houbb.sensitive.word.support.format.CharFormatChain;

import java.util.Map;

/**
 * 敏感词监测实现
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class SensitiveWordCheck implements ISensitiveCheck {

    @Override
    public int checkSensitive(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        Map nowMap = context.sensitiveWordMap();

        // 记录敏感词的长度
        int lengthCount = 0;
        int actualLength = 0;

        for (int i = beginIndex; i < txt.length(); i++) {
            char c = txt.charAt(i);
            char charKey = Instances.singleton(CharFormatChain.class).format(c, context);

            // 判断该字是否存在于敏感词库中
            // 并且将 nowMap 替换为新的 map，进入下一层的循环。
            nowMap = (Map) nowMap.get(charKey);
            if (ObjectUtil.isNotNull(nowMap)) {
                lengthCount++;

                // 判断是否是敏感词的结尾字，如果是结尾字则判断是否继续检测
                boolean isEnd = (boolean) nowMap.get(AppConst.IS_END);
                if (isEnd) {
                    // 只在匹配到结束的时候才记录长度，避免不完全匹配导致的问题。
                    // eg: 敏感词 敏感词xxx
                    // 如果是 【敏感词x】也会被匹配。
                    actualLength = lengthCount;

                    // 这里确实需要一种验证模式，主要是为了最大匹配从而达到最佳匹配的效果。
                    if (ValidModeEnum.FAIL_FAST.equals(validModeEnum)) {
                        break;
                    }
                }
            } else {
                // 直接跳出循环
                break;
            }
        }

        return actualLength;
    }

}
