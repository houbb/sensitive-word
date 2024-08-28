package com.github.houbb.sensitive.word.support.deny;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.sensitive.word.api.IWordDeny;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 空实现
 * @author binbin.hou
 * @since 0.19.0
 */
@ThreadSafe
public class WordDenyEmpty implements IWordDeny {

    @Override
    public List<String> deny() {
        return new ArrayList<>();
    }

}
