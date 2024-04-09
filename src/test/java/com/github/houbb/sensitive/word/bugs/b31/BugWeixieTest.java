package com.github.houbb.sensitive.word.bugs.b31;

import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.junit.Assert;
import org.junit.Test;

public class BugWeixieTest {

    @Test
    public void lettersTest() {
        String text = "我受到了威胁救救我";

        System.out.println(SensitiveWordHelper.findAll(text));
    }

}
