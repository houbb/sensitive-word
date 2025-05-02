package com.github.houbb.sensitive.word.bs;

import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.data.WordCountDto;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.check.WordChecks;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import com.github.houbb.sensitive.word.support.ignore.SensitiveWordCharIgnores;
import com.github.houbb.sensitive.word.support.replace.WordReplaces;
import com.github.houbb.sensitive.word.support.resultcondition.WordResultConditions;
import com.github.houbb.sensitive.word.support.tag.WordTags;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * <p> project: sensitive-word-SensitiveWordBsConfigTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.0.14
 */
public class SensitiveWordBsConfigTest {

    @Test
    @Ignore
    public void config2Test() {
        SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
                .ignoreCase(true)
                .ignoreWidth(true)
                .ignoreNumStyle(true)
                .ignoreChineseStyle(true)
                .ignoreEnglishStyle(true)
                .ignoreRepeat(false)
                .enableNumCheck(false)
                .enableEmailCheck(false)
                .enableUrlCheck(false)
                .enableIpv4Check(false)
                .enableWordCheck(true)
                .wordCheckNum(WordChecks.num())
                .wordCheckEmail(WordChecks.email())
                .wordCheckUrl(WordChecks.url())
                .wordCheckIpv4(WordChecks.ipv4())
                .wordCheckWord(WordChecks.word())
                .numCheckLen(8)
                .wordTag(WordTags.none())
                .charIgnore(SensitiveWordCharIgnores.defaults())
                .wordResultCondition(WordResultConditions.alwaysTrue())
                .wordAllow(WordAllows.defaults())
                .wordDeny(WordDenys.defaults())
                .wordReplace(WordReplaces.defaults())
                .init();
    }

    @Test
    public void configTest() {
        SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
                .ignoreCase(true)
                .ignoreWidth(true)
                .ignoreNumStyle(true)
                .ignoreChineseStyle(true)
                .ignoreEnglishStyle(true)
                .ignoreRepeat(true)
                .enableNumCheck(true)
                .enableEmailCheck(true)
                .enableUrlCheck(true)
                .numCheckLen(8)
                .wordTag(WordTags.none())
                .charIgnore(SensitiveWordCharIgnores.defaults())
                .wordResultCondition(WordResultConditions.alwaysTrue())
                .init();

        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";
        Assert.assertTrue(wordBs.contains(text));
    }

}
