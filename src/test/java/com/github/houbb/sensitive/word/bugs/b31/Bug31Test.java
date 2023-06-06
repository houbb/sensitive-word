package com.github.houbb.sensitive.word.bugs.b31;

import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.junit.Assert;
import org.junit.Test;

public class Bug31Test {

    @Test
    public void lettersTest() {
        String text = "你是SB吧";

        Assert.assertTrue(SensitiveWordHelper.contains(text));
        Assert.assertEquals("[SB]", SensitiveWordHelper.findAll(text).toString());
    }

}
