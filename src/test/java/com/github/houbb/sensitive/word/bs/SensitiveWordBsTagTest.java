package com.github.houbb.sensitive.word.bs;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.api.IWordTag;
import com.github.houbb.sensitive.word.support.result.WordResultHandlers;
import com.github.houbb.sensitive.word.support.result.WordTagsDto;
import com.github.houbb.sensitive.word.support.tag.AbstractWordTag;
import com.github.houbb.sensitive.word.support.tag.WordTags;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.10.0
 */
public class SensitiveWordBsTagTest {

    private void addLine(String line,
                         Map<String, Set<String>> wordTagMap) {
        String[] strings = line.split(" ");
        String key = strings[0];
        Set<String> tags = new HashSet<>(StringUtil.splitToList(strings[1]));
        wordTagMap.put(key, tags);
    }

    @Test
    public void wordResultHandlerWordTagsTest() {
        // 自定义测试标签类
        final Map<String, Set<String>> wordTagMap = new HashMap<>();
        addLine("0售 广告", wordTagMap);
        IWordTag wordTag = new AbstractWordTag() {
            @Override
            protected Set<String> doGetTag(String word) {
                return wordTagMap.get(word);
            }
        };

        // 指定初始化
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordDeny(new IWordDeny() {
                    @Override
                    public List<String> deny() {
                        return Arrays.asList("0售");
                    }
                })
                .wordTag(wordTag)
                .init()
                ;
        List<WordTagsDto> wordTagsDtoList1 = sensitiveWordBs.findAll("零售", WordResultHandlers.wordTags());
        Assert.assertEquals("[WordTagsDto{word='零售', tags=[广告]}]", wordTagsDtoList1.toString());

        List<WordTagsDto> wordTagsDtoList2 = sensitiveWordBs.findAll("0售", WordResultHandlers.wordTags());
        Assert.assertEquals("[WordTagsDto{word='0售', tags=[广告]}]", wordTagsDtoList2.toString());
    }

    @Test
    public void wordTagsTest() {
        // 自定义测试标签类
        final Map<String, Set<String>> wordTagMap = new HashMap<>();
        addLine("0售 广告", wordTagMap);
        addLine("天安门 政治,国家,地址", wordTagMap);
        IWordTag wordTag = new AbstractWordTag() {
            @Override
            protected Set<String> doGetTag(String word) {
                return wordTagMap.get(word);
            }
        };

        // 指定初始化
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordTag(wordTag)
                .init()
                ;

        Assert.assertEquals("[政治, 国家, 地址]", sensitiveWordBs.tags("天安门").toString());
        Assert.assertEquals("[广告]", sensitiveWordBs.tags("零售").toString());
        Assert.assertEquals("[广告]", sensitiveWordBs.tags("0售").toString());
    }

}
