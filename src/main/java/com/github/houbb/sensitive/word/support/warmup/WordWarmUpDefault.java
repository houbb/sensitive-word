package com.github.houbb.sensitive.word.support.warmup;

import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordWarmUp;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;

import java.util.List;

/**
 * 默认策略
 * @since 1.0.0
 */
public class WordWarmUpDefault implements IWordWarmUp {

    @Override
    public void warmUp(SensitiveWordBs sensitiveWordBs, IWordContext wordContext, List<String> wordAllowList, List<String> wordDenyList) {
        String testInfo = "sensitive-word";
        if(CollectionUtil.isNotEmpty(wordAllowList)) {
            testInfo = testInfo + " " + wordAllowList.get(0);
        }
        if(CollectionUtil.isNotEmpty(wordDenyList)) {
            testInfo = testInfo + " " + wordDenyList.get(0);
        }

        // 只能说优化，但是无法杜绝
        for(int i = 0; i < 5; i++) {
            sensitiveWordBs.findAll(testInfo);
            sensitiveWordBs.findFirst(testInfo);
            sensitiveWordBs.contains(testInfo);
            sensitiveWordBs.replace(testInfo);
            sensitiveWordBs.tags(testInfo);
        }
    }

}
