package com.github.houbb.sensitive.word.support.handler;

import com.github.houbb.sensitive.word.api.IWordResult;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.github.houbb.sensitive.word.support.result.WordResultHandlers;
import com.github.houbb.sensitive.word.support.result.WordTagsDto;
import com.github.houbb.sensitive.word.support.tag.WordTags;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

/**
 * @since 0.12.0
 */
public class WordResultHandlerTest {

    @Test
    public void findAllWordTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        List<String> wordList = SensitiveWordHelper.findAll(text);
        Assert.assertEquals("[五星红旗, 毛主席, 天安门]", wordList.toString());
        List<String> wordList2 = SensitiveWordHelper.findAll(text, WordResultHandlers.word());
        Assert.assertEquals("[五星红旗, 毛主席, 天安门]", wordList2.toString());

        List<IWordResult> wordList3 = SensitiveWordHelper.findAll(text, WordResultHandlers.raw());
        Assert.assertEquals("[WordResult{startIndex=0, endIndex=4, type='WORD', word='5星红旗'}, WordResult{startIndex=9, endIndex=12, type='WORD', word='毛主席'}, WordResult{startIndex=18, endIndex=21, type='WORD', word='天安门'}]", wordList3.toString());
    }

    @Test
    public void findAllWordTest2() {
        final String text = "骂人：你他妈; 邮箱：123@qq.com; mobile: 13088889999; 网址：https://www.baidu.com";
        List<IWordResult> wordList3 = SensitiveWordHelper
                .findAll(text, WordResultHandlers.raw());
        Assert.assertEquals("[WordResult{startIndex=3, endIndex=6, type='WORD', word='你他妈'}]", wordList3.toString());
    }

    @Test
    public void wordTagsTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        // 默认敏感词标签为空
        List<WordTagsDto> wordList1 = SensitiveWordHelper.findAll(text, WordResultHandlers.wordTags());
        Assert.assertEquals("[WordTagsDto{word='五星红旗', tags=null}, WordTagsDto{word='毛主席', tags=[0]}, WordTagsDto{word='天安门', tags=null}]", wordList1.toString());

        Map<String, Set<String>> wordMap = new HashMap<>();
        wordMap.put("五星红旗", new HashSet<>(Arrays.asList("政治", "国家")));
        wordMap.put("毛主席", new HashSet<>(Arrays.asList("政治", "伟人", "国家")));
        wordMap.put("天安门", new HashSet<>(Arrays.asList("政治", "国家", "地址")));

        List<WordTagsDto> wordList2 = SensitiveWordBs.newInstance()
                .wordTag(WordTags.map(wordMap))
                .init()
                .findAll(text, WordResultHandlers.wordTags());
        Assert.assertEquals("[WordTagsDto{word='五星红旗', tags=[政治, 国家]}, WordTagsDto{word='毛主席', tags=[政治, 伟人, 国家]}, WordTagsDto{word='天安门', tags=[政治, 国家, 地址]}]", wordList2.toString());
    }

}
