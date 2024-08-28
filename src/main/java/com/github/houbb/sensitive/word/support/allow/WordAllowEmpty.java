package com.github.houbb.sensitive.word.support.allow;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.github.houbb.sensitive.word.api.IWordAllow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 空列表
 * @author binbin.hou
 * @since 0.19.0
 */
@ThreadSafe
public class WordAllowEmpty implements IWordAllow {

    @Override
    public List<String> allow() {
        return new ArrayList<>();
    }

}
