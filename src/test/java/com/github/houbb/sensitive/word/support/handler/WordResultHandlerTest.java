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

import java.util.List;

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
        Assert.assertEquals("[WordResult{startIndex=0, endIndex=4}, WordResult{startIndex=9, endIndex=12}, WordResult{startIndex=18, endIndex=21}]", wordList3.toString());
    }

    @Test
    @Ignore
    public void wordTagsTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        // 默认敏感词标签为空
        List<WordTagsDto> wordList1 = SensitiveWordHelper.findAll(text, WordResultHandlers.wordTags());
        Assert.assertEquals("[WordTagsDto{word='五星红旗', tags=[]}, WordTagsDto{word='毛主席', tags=[]}, WordTagsDto{word='天安门', tags=[]}]", wordList1.toString());

        List<WordTagsDto> wordList2 = SensitiveWordBs.newInstance()
                .wordTag(WordTags.file("D:\\github\\sensitive-word\\src\\test\\resources\\dict_tag_test.txt"))
                .init()
                .findAll(text, WordResultHandlers.wordTags());
        Assert.assertEquals("[WordTagsDto{word='五星红旗', tags=[政治, 国家]}, WordTagsDto{word='毛主席', tags=[政治, 伟人, 国家]}, WordTagsDto{word='天安门', tags=[政治, 国家, 地址]}]", wordList2.toString());
    }

}
