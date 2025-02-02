package com.github.houbb.sensitive.word.support.data;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.enums.WordContainsTypeEnum;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 敏感词 map
 * PR：https://github.com/houbb/sensitive-word/pull/33
 *
 * PR: https://github.com/houbb/sensitive-word/pull/74
 *
 * @author xiaochangbai
 * @author binbin.hou
 * @author zldaysleepy
 *
 * @since 0.7.0
 */
@ThreadSafe
public class WordDataTree extends AbstractWordData {

    @Override
    public synchronized void initWordData(Collection<String> collection) {
        WordDataTreeNode newRoot = new WordDataTreeNode();

        for (String word : collection) {
            if (StringUtil.isEmpty(word)) {
                continue;
            }
            addWord(newRoot, word);
        }

        // 初始化完成才做替换
        this.root = newRoot;
    }
    /**
     * 根节点
     */
    private WordDataTreeNode root;

    @Override
    protected WordContainsTypeEnum doContains(StringBuilder stringBuilder, InnerSensitiveWordContext innerContext) {
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

    @Override
    protected void doInitWordData(Collection<String> collection) {
        WordDataTreeNode newRoot = new WordDataTreeNode();

        for (String word : collection) {
            if (StringUtil.isEmpty(word)) {
                continue;
            }
            addWord(newRoot, word);
        }

        // 初始化完成才做替换
        this.root = newRoot;
    }



    /**
     * 新增敏感词
     *
     * @param collection 敏感词集合
     */
    @Override
    public synchronized void doAddWord(Collection<String> collection) {
        for (String word : collection) {
            if (StringUtil.isEmpty(word)) {
                continue;
            }
            addWord(this.root, word);
        }
    }

    @Override
    protected synchronized void doRemoveWord(Collection<String> collection) {
        for (String word : collection) {
            if (StringUtil.isEmpty(word)) {
                continue;
            }
            removeWord(this.root, word);
        }
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

    @Override
    public void destroy() {
        if(this.root != null) {
            this.root.destroy();
        }
    }

    /**
     * 添加敏感词
     * @param newRoot 新的根节点
     * @param word 单词
     *
     * @since 0.19.0
     */
    private void addWord(WordDataTreeNode newRoot, String word) {
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


    private void removeWord(WordDataTreeNode root, String word){
        WordDataTreeNode tempNode = root;
        //需要删除的
        Map<Character, WordDataTreeNode> map = new HashMap<>();
        char[] chars = word.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            //不存在第一个词
            WordDataTreeNode subNode = tempNode.getSubNode(chars[i]);
            if (subNode == null) {
                return;
            }
            if (i == (length - 1)) {
                //尾字符判断是否结束
                if (!subNode.end()) {
                    return;
                }
                if (subNode.getNodeSize() > 0) {
                    //尾字符下还存在字符,即标识即可
                    subNode.end(false);
                    return;
                }
            }
            if (subNode.end()) {
                map.clear();
            }
            map.put(chars[i], tempNode);

            tempNode = subNode;
        }

        for (Map.Entry<Character, WordDataTreeNode> entry : map.entrySet()) {
            WordDataTreeNode value = entry.getValue();
            //节点只有一个就置空
            if (value.getNodeSize() == 1) {
                value.clearNode();
                return;
            }
            //多个就删除
            value.removeNode(entry.getKey());
        }
    }

}
