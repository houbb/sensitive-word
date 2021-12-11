package com.github.houbb.sensitive.word.bugs.b20211211;

import com.github.houbb.sensitive.word.api.IWordDeny;

import java.util.Arrays;
import java.util.List;

public class MyWordDeny implements IWordDeny {

    @Override
    public List<String> deny() {
        return Arrays.asList("尼玛");
    }


}
