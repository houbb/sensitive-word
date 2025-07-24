package com.github.houbb.sensitive.word.support.deny;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.utils.InnerStreamUtils;

import java.util.List;

/**
 * 系统默认的信息
 * @author binbin.hou
 * @since 0.0.13
 */
@ThreadSafe
public class WordDenySystem implements IWordDeny {

    /**
     * @since 0.3.0
     */
    private static final IWordDeny INSTANCE = new WordDenySystem();

    public static IWordDeny getInstance() {
        return INSTANCE;
    }

    @Override
    public List<String> deny() {
        List<String> results = InnerStreamUtils.readAllLines("/sensitive_word_dict.txt");
        results.addAll(InnerStreamUtils.readAllLines("/sensitive_word_dict_en.txt"));
        results.addAll(InnerStreamUtils.readAllLines("/sensitive_word_deny.txt"));
        return results;
    }

}
