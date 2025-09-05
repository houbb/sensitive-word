package com.github.houbb.sensitive.word.support.data;

import com.github.houbb.sensitive.word.api.ISensitiveWordDestroy;

import java.util.HashMap;
import java.util.Map;

/**
 * 树节点
 *
 * @since 0.7.0
 */
public class WordDataTreeNode implements ISensitiveWordDestroy {

    /**
     * 关键词结束标识
     */
    private boolean end;

    /**
     * 子节点(key是下级字符,value是下级节点)
     */
    private Map<Character, WordDataTreeNode> subNodeMap;

    public boolean end() {
        return end;
    }

    public WordDataTreeNode end(boolean end) {
        this.end = end;
        return this;
    }

    public WordDataTreeNode getSubNode(final Character c) {
        if(subNodeMap == null) {
            return null;
        }

        return subNodeMap.get(c);
    }
    public int getNodeSize() {
        if (subNodeMap == null) {
            return 0;
        }
        return subNodeMap.size();
    }

    public void clearNode() {
        if (subNodeMap == null) {
            return;
        }
        subNodeMap=null;
    }

    public void removeNode(final Character c) {
        if (subNodeMap == null) {
            return;
        }
        subNodeMap.remove(c);
    }

    public WordDataTreeNode addSubNode(Character c, WordDataTreeNode subNode) {
        if(this.subNodeMap == null) {
            subNodeMap = new HashMap<>();
        }

        subNodeMap.put(c, subNode);
        return this;
    }

    @Override
    public void destroy() {
        if(subNodeMap != null) {
            subNodeMap.clear();
        }
    }

}
