package com.github.houbb.sensitive.word.bs;

import com.github.houbb.sensitive.word.api.IWordTag;
import com.github.houbb.sensitive.word.support.tag.WordTags;
import org.junit.Assert;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.10.0
 */
public class SensitiveWordBsTagTest {

    public static void main(String[] args) {
        String filePath = "D:\\code\\github\\sensitive-word\\src\\test\\resources\\dict_tag_test.txt";

        IWordTag wordTag = WordTags.file(filePath);

        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordTag(wordTag)
                .init()
        ;

        Assert.assertEquals("[政治, 国家]", sensitiveWordBs.tags("五星红旗").toString());;
    }

}
