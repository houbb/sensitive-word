package com.github.houbb.sensitive.word.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 * @author xiaochangbai
 * @date 2023-06-04 09:54
 */
public class NodeTree {

    // 关键词结束标识
    private boolean isKeywordEnd;

    // 子节点(key是下级字符,value是下级节点)
    private Map<Character, NodeTree> subNodes;

    public boolean isKeywordEnd() {
        return Boolean.TRUE.equals(isKeywordEnd);
    }

    public void setKeywordEnd(boolean keywordEnd) {
        isKeywordEnd = keywordEnd;
    }

    // 添加子节点
    public void addSubNode(Character c, NodeTree node) {
        if(subNodes==null){
            subNodes = new HashMap<>(8);
        }
        subNodes.put(c, node);
    }

    // 获取子节点
    public NodeTree getSubNode(Character c) {
        if(subNodes==null){
            return null;
        }
        return subNodes.get(c);
    }

}
