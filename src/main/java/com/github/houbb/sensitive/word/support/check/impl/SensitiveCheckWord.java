package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
import com.github.houbb.sensitive.word.core.NodeTree;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;
import com.github.houbb.sensitive.word.support.check.SensitiveCheckResult;
import com.github.houbb.sensitive.word.support.format.CharFormatChain;

import java.util.Map;

/**
 * 敏感词监测实现
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class SensitiveCheckWord implements ISensitiveCheck {

    @Override
    public SensitiveCheckResult sensitiveCheck(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        NodeTree nowNode = context.sensitiveWordInfo();

        // 记录敏感词的长度
        int lengthCount = 0;
        int actualLength = 0;

        for (int i = beginIndex; i < txt.length(); i++) {
            // 获取当前的node信息
            nowNode = getNowNode(nowNode, context, txt, i);

            if (ObjectUtil.isNotNull(nowNode)) {
                lengthCount++;
                if (nowNode.isKeywordEnd()) {
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

        return SensitiveCheckResult.of(actualLength, SensitiveCheckWord.class);
    }
    /**
     * 获取当前的前缀树
     * @param nodeTree 原始的当前node
     * @param context 上下文
     * @param txt 文本信息
     * @param index 下标
     * @return 实际的当前 map
     * @since 0.0.7
     */
    private NodeTree getNowNode(NodeTree nodeTree,
                          final IWordContext context,
                          final String txt,
                          final int index) {
        char c = txt.charAt(index);
        char mappingChar = Instances.singleton(CharFormatChain.class).format(c, context);

        // 这里做一次重复词的处理
        NodeTree subNode = nodeTree.getSubNode(mappingChar);
        // 启用忽略重复&当前下标不是第一个
        if(context.ignoreRepeat()
            && index > 0) {
            char preChar = txt.charAt(index-1);
            char preMappingChar = Instances.singleton(CharFormatChain.class)
                    .format(preChar, context);

            // 返回父节点
            if(preMappingChar == mappingChar) {
                return nodeTree;
            }
        }

        return subNode;
    }

}
