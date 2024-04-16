package com.github.houbb.sensitive.word.support.deny;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.sensitive.word.api.IEditableWordDeny;
import com.github.houbb.sensitive.word.api.IWordDeny;

import java.util.List;

/**
 * 系统默认的信息
 *
 * @author binbin.hou
 * @since 0.0.13
 */
@ThreadSafe
public class WordDenySystem implements IEditableWordDeny {
    private static final List<String> defaultDenyList;

    static {
        defaultDenyList = StreamUtil.readAllLines("/dict.txt");
        defaultDenyList.addAll(StreamUtil.readAllLines("/dict_en.txt"));
        defaultDenyList.addAll(StreamUtil.readAllLines("/sensitive_word_deny.txt"));
    }

    private final List<String> denyList;

    private WordDenySystem() {
        this.denyList = defaultDenyList;
    }

    /**
     * @since 0.3.0
     */
    private static final IWordDeny INSTANCE = new WordDenySystem();

    public static IWordDeny getInstance() {
        return INSTANCE;
    }

    @Override
    public List<String> deny() {
        return this.denyList;
    }

    @Override
    public void add(String word) {
        if (!this.denyList.contains(word)) {
            this.denyList.add(word);
        }
    }

    @Override
    public void remove(String word) {
        this.denyList.remove(word);
    }
}
