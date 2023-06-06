package com.github.houbb.sensitive.word.bugs.b29;

import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.junit.Assert;
import org.junit.Test;

public class Bug29Test {

    @Test
    public void test() {
        String text = "生日快乐";

        Assert.assertFalse(SensitiveWordHelper.contains(text));
        Assert.assertEquals("[]", SensitiveWordHelper.findAll(text).toString());
    }

}
