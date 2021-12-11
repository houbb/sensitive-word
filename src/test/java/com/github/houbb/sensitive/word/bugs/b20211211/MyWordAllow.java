package com.github.houbb.sensitive.word.bugs.b20211211;

import com.github.houbb.sensitive.word.api.IWordAllow;

import java.util.Arrays;
import java.util.List;

public class MyWordAllow implements IWordAllow {

    @Override
    public List<String> allow() {
        return Arrays.asList("五星红旗");
    }

}
