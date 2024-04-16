package com.github.houbb.sensitive.word.support.deny;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.sensitive.word.api.IEditableWordDeny;
import com.github.houbb.sensitive.word.api.IWordDeny;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有拒绝的结果
 *
 * @author binbin.hou
 * @since 0.0.13
 */
public final class WordDenys {

    private WordDenys() {
    }

    /**
     * 责任链
     *
     * @param wordDeny 拒绝
     * @param others   其他
     * @return 结果
     * @since 0.0.13
     */
    public static IWordDeny chains(final IWordDeny wordDeny,
                                   final IWordDeny... others) {
        return new WordDenyChains(wordDeny, others);
    }

    /**
     * 系统实现
     *
     * @return 结果
     * @since 0.0.13
     */
    public static IWordDeny defaults() {
        return WordDenySystem.getInstance();
    }


    private static class WordDenyChains extends WordDenyInit implements IEditableWordDeny {
        final List<IWordDeny> denySet;
        IEditableWordDeny editableWordDeny;

        WordDenyChains(final IWordDeny wordDeny,
                       final IWordDeny... others) {
            this.denySet = new ArrayList<>();
            this.denySet.add(wordDeny);
            IEditableWordDeny editableWordDeny;
            if ((editableWordDeny = convertEditableWordDenyInstance(wordDeny)) != null) {
                this.editableWordDeny = editableWordDeny;
            }
            if (ArrayUtil.isNotEmpty(others)) {
                for (IWordDeny other : others) {
                    this.denySet.add(other);
                    if (this.editableWordDeny != null) {
                        continue;
                    }
                    if ((editableWordDeny = convertEditableWordDenyInstance(other)) != null) {
                        this.editableWordDeny = editableWordDeny;
                    }
                }
            }
        }

        protected void init(Pipeline<IWordDeny> pipeline) {
            for (IWordDeny other : this.denySet) {
                pipeline.addLast(other);
            }
        }

        @Override
        public void add(String word) {
            if (this.editableWordDeny == null) {
                return;
            }
            this.editableWordDeny.add(word);
        }

        @Override
        public void remove(String word) {
            if (this.editableWordDeny == null) {
                return;
            }
            this.editableWordDeny.remove(word);
        }
    }

    /**
     * 尝试转换为IEditableWordDeny
     *
     * @return IEditableWordDeny实例
     * @since 0.15.0
     */
    public static IEditableWordDeny convertEditableWordDenyInstance(IWordDeny wordDeny) {
        if (wordDeny == null) {
            return null;
        }
        if (!(wordDeny instanceof IEditableWordDeny)) {
            return null;
        }
        return (IEditableWordDeny) wordDeny;
    }
}
