package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.support.resultcondition.WordResultConditions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.13.0
 */
public class SensitiveWordBsResultConditionTest {

    @Test
    public void alwaysTrueTest() {
        final String text = "I have a nice day。";

        List<String> wordList = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Collections.singletonList("av");
                    }
                })
                .wordResultCondition(WordResultConditions.alwaysTrue())
                .init()
                .findAll(text);
        Assert.assertEquals("[av]", wordList.toString());
    }

    @Test
    public void englishWordMatchTest() {
        final String text = "I have a nice day。";

        List<String> wordList = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Collections.singletonList("av");
                    }
                })
                .wordResultCondition(WordResultConditions.englishWordMatch())
                .init()
                .findAll(text);
        Assert.assertEquals("[]", wordList.toString());
    }

    @Test
    public void englishWordMatchTest2() {
        final String text = "I hav";

        List<String> wordList = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("av");
                    }
                })
                .wordResultCondition(WordResultConditions.englishWordMatch())
                .init()
                .findAll(text);
        Assert.assertEquals("[]", wordList.toString());
    }

    @Test
    public void englishWordMatchTest3() {
        final String text = "av";

        List<String> wordList = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("av");
                    }
                })
                .wordResultCondition(WordResultConditions.englishWordMatch())
                .init()
                .findAll(text);
        Assert.assertEquals("[av]", wordList.toString());
    }

    @Test
    public void englishWordMatchTest4() {
        final String text = "I have a nice day";

        List<String> wordList = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("av", "day");
                    }
                })
                .wordResultCondition(WordResultConditions.englishWordMatch())
                .init()
                .findAll(text);
        Assert.assertEquals("[day]", wordList.toString());
    }

    @Test
    public void englishWordMatchTest5() {
        final String text = "test for International Congress";

        List<String> wordList = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("national Congress");
                    }
                })
                .wordResultCondition(WordResultConditions.englishWordMatch())
                .init()
                .findAll(text);
        Assert.assertEquals("[]", wordList.toString());
    }

}
