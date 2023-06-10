package com.github.houbb.sensitive.word.support.data;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordContainsTypeEnum;

import java.util.Collection;

/**
 * 敏感词 map
 * PR：https://github.com/houbb/sensitive-word/pull/33
 *
 * @author xiaochangbai
 * @author binbin.hou
 * @since 0.7.0
 */
@ThreadSafe
public class WordDataTree implements IWordData {

    /**
     * 根节点
     */
    private WordDataTreeNode root;

    @Override
    public synchronized void initWordData(Collection<String> collection) {
        WordDataTreeNode newRoot = new WordDataTreeNode();

        for(String word : collection) {
            if(StringUtil.isEmpty(word)) {
                continue;
            }

            WordDataTreeNode tempNode = newRoot;
            char[] chars = word.toCharArray();
            for (char c : chars) {
                // 获取子节点
                WordDataTreeNode subNode = tempNode.getSubNode(c);
                if (subNode == null) {
                    subNode = new WordDataTreeNode();
                    // 加入新的子节点
                    tempNode.addSubNode(c, subNode);
                }

                // 临时节点指向子节点，进入下一次循环
                tempNode = subNode;
            }

            // 设置结束标识（循环结束，设置一次即可）
            tempNode.end(true);
        }

        // 初始化完成才做替换
        this.root = newRoot;
    }

    @Override
    public WordContainsTypeEnum contains(StringBuilder stringBuilder,
                                         InnerSensitiveWordContext innerContext) {
        WordDataTreeNode nowNode = root;

        int len = stringBuilder.length();

        for(int i = 0; i < len; i++) {
            // 获取当前的 map 信息
            nowNode = getNowMap(nowNode, i, stringBuilder, innerContext);

            // 如果不为空，则判断是否为结尾。
            if (ObjectUtil.isNull(nowNode)) {
                return WordContainsTypeEnum.NOT_FOUND;
            }
        }

        if(nowNode.end()) {
            return WordContainsTypeEnum.CONTAINS_END;
        }

        return WordContainsTypeEnum.CONTAINS_PREFIX;
    }


    /**
     * 获取当前的 Map
     * @param nowNode 当前节点
     * @param index 下标
     * @param stringBuilder 文本缓存
     * @param sensitiveContext 上下文
     * @return 实际的当前 map
     * @since 0.0.7
     */
    private WordDataTreeNode getNowMap(WordDataTreeNode nowNode,
                          final int index,
                          final StringBuilder stringBuilder,
                          final InnerSensitiveWordContext sensitiveContext) {
        final IWordContext context = sensitiveContext.wordContext();

        // 这里的 char 已经是统一格式化之后的，所以可以不用再次格式化。
        char mappingChar = stringBuilder.charAt(index);

        // 这里做一次重复词的处理
        WordDataTreeNode currentMap = nowNode.getSubNode(mappingChar);
        // 启用忽略重复&当前下标不是第一个
        if(context.ignoreRepeat()
                && index > 0) {
            char preMappingChar = stringBuilder.charAt(index-1);

            // 直接赋值为上一个 map
            if(preMappingChar == mappingChar) {
                currentMap = nowNode;
            }
        }

        return currentMap;
    }

}
