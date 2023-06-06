package com.github.houbb.sensitive.word.bugs.b32;

import com.github.houbb.sensitive.word.api.IWordDeny;

import java.util.Arrays;
import java.util.List;

public class MyWordDenyChineseNum implements IWordDeny {

    @Override
    public List<String> deny() {
        return Arrays.asList("三三九乘元功", "一军两策");
    }

}
