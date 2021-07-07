package com.github.houbb.sensitive.word.define;

import com.github.houbb.sensitive.word.api.IWordAllow;

import java.util.Arrays;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.14
 */
public class MyWordAllow implements IWordAllow {

    @Override
    public List<String> allow() {
        return Arrays.asList("测试");
    }

}
