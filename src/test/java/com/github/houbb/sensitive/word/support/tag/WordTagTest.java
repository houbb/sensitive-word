package com.github.houbb.sensitive.word.support.tag;

import com.github.houbb.sensitive.word.api.IWordTag;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 单词处理
 * @since 0.24.0
 */
public class WordTagTest {

    public static void main(String[] args) {
        final String path = "D:\\github\\sensitive-word\\src\\test\\resources\\dict_tag_test.txt";
        IWordTag wordTag = WordTags.file(path);
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordTag(wordTag)
                .init();

        Set<String> tagSet = sensitiveWordBs.tags("零售");
        Assert.assertEquals("[广告, 网络]", tagSet.toString());


        IWordTag wordTag2 = WordTags.file(path, " ", ",");
        SensitiveWordBs sensitiveWordBs2 = SensitiveWordBs.newInstance()
                .wordTag(wordTag2)
                .init();
        Set<String> tagSet2 = sensitiveWordBs2.tags("零售");
        Assert.assertEquals("[广告, 网络]", tagSet2.toString());
    }

    @Test
    public void noneTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordTag(WordTags.none())
                .init();

        Set<String> tagSet = sensitiveWordBs.tags("博彩");
        Assert.assertEquals("[]", tagSet.toString());
    }

    @Test
    public void defaultsTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordTag(WordTags.defaults())
                .init();

        Set<String> tagSet = sensitiveWordBs.tags("博彩");
        Assert.assertEquals("[3]", tagSet.toString());
    }

    @Test
    public void systemTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordTag(WordTags.system())
                .init();

        Set<String> tagSet = sensitiveWordBs.tags("博彩");
        Assert.assertEquals("[3]", tagSet.toString());
    }

    @Test
    public void linesTest() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordTag(WordTags.lines(Arrays.asList("博彩 赌博")))
                .init();

        Set<String> tagSet = sensitiveWordBs.tags("博彩");
        Assert.assertEquals("[赌博]", tagSet.toString());
    }

    @Test
    public void lines2Test() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordTag(WordTags.lines(Arrays.asList("博彩:赌博,网络"), ":", ","))
                .init();

        Set<String> tagSet = sensitiveWordBs.tags("博彩");
        Assert.assertEquals("[网络, 赌博]", tagSet.toString());
    }

    @Test
    public void mapTest() {
        Map<String, Set<String>> wordTagMap = new HashMap<>();
        Set<String> initTagSet = new HashSet<>();
        initTagSet.add("广告");
        initTagSet.add("网络");
        wordTagMap.put("零售", initTagSet);

        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordTag(WordTags.map(wordTagMap))
                .init()
                ;

        Set<String> tagSet = sensitiveWordBs.tags("零售");
        Assert.assertEquals("[广告, 网络]", tagSet.toString());
    }

    @Test
    public void chainsTest() {
        IWordTag wordTag = WordTags.lines(Arrays.asList("零售 广告,网络"));
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordTag(WordTags.chains(WordTags.none(), wordTag))
                .init();

        Set<String> tagSet = sensitiveWordBs.tags("零售");
        Assert.assertEquals("[广告, 网络]", tagSet.toString());
    }

}
