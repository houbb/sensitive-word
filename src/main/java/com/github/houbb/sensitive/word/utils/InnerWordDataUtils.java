package com.github.houbb.sensitive.word.utils;

import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordContext;

import java.util.*;

/**
 * 数据工具包
 * @since 0.3.0
 */
public final class InnerWordDataUtils {

    private InnerWordDataUtils(){}

    /**
     * 获取禁止列表中真正的禁止词汇
     * @param denyList 禁止
     * @param allowList 允许
     * @return 结果
     * @since 0.3.0
     */
    public static List<String> getActualDenyList(List<String> denyList, List<String> allowList,
                                                 final IWordContext context) {
        if(CollectionUtil.isEmpty(denyList)) {
            return Collections.emptyList();
        }
        if(CollectionUtil.isEmpty(allowList)) {
            return denyList;
        }

        List<String> formatDenyList = InnerFormatUtils.formatWordList(denyList, context);
        List<String> formatAllowList = InnerFormatUtils.formatWordList(allowList, context);

        List<String> resultList = new ArrayList<>();
        // O(1)
        Set<String> allowSet = new HashSet<>(formatAllowList);

        for(String deny : formatDenyList) {
            if(allowSet.contains(deny)) {
                continue;
            }

            resultList.add(deny);
        }
        return resultList;
    }
}
