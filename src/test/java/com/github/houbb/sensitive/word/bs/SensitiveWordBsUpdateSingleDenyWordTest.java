package com.github.houbb.sensitive.word.bs;

import com.github.houbb.heaven.util.util.RandomUtil;
import com.github.houbb.sensitive.word.api.IEditableWordDeny;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.benchmark.BenchmarkBasicTest;
import com.github.houbb.sensitive.word.support.data.WordDatas;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsConfigTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.7.0
 */
public class SensitiveWordBsUpdateSingleDenyWordTest {

    private static class SingleDenyWordImpl implements IEditableWordDeny {
        List<String> denyList;

        SingleDenyWordImpl(List<String> denyList) {
            this.denyList = denyList;
        }

        @Override
        public List<String> deny() {
            return this.denyList;
        }

        @Override
        public void add(String word) {
            if (!this.denyList.contains(word)) {
                this.denyList.add(word);
            }
        }

        @Override
        public void remove(String word) {
            this.denyList.remove(word);
        }
    }

    @Test
    public void updateSingleDenyWordTest() {
        String text = "你他妈的不要说脏话我们他妈的从来不说脏说";

        // 1W 次
        final List<String> denyList = new ArrayList<String>() {
            {
                this.add("他妈的");
                this.add("你他妈的");
            }
        };
        final IWordDeny defaultWordDeny = new SensitiveWordBsUpdateSingleDenyWordTest.SingleDenyWordImpl(denyList);

        SensitiveWordBs wordBs = SensitiveWordBs
                .newInstance()
                .wordDeny(WordDenys.chains(defaultWordDeny))
                .init();

        Assert.assertEquals("[你他妈的, 他妈的]", wordBs.findAll(text).toString());

        Assert.assertTrue(wordBs.addDenyWord("脏话"));
        wordBs.init();
        Assert.assertEquals("[你他妈的, 脏话, 他妈的]", wordBs.findAll(text).toString());

        Assert.assertTrue(wordBs.editDenyWord("脏话", "不要说"));
        wordBs.init();
        Assert.assertEquals("[你他妈的, 不要说, 他妈的]", wordBs.findAll(text).toString());

        Assert.assertTrue(wordBs.removeDenyWord("不要说"));
        wordBs.init();

        Assert.assertEquals("[你他妈的, 他妈的]", wordBs.findAll(text).toString());
    }

}
