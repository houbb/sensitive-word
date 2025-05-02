package com.github.houbb.sensitive.word.bugs.b105;

import java.util.*;

import com.github.houbb.heaven.util.util.CollectionUtil;
import org.junit.Assert;
import org.junit.Test;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.ignore.SensitiveWordCharIgnores;
import com.github.houbb.sensitive.word.support.result.WordResultHandlers;
import com.github.houbb.sensitive.word.support.result.WordTagsDto;
import com.github.houbb.sensitive.word.support.tag.WordTags;

/**
 * 测试Issue #105: 处理带有噪音字符时的标签查找
 * 验证在启用字符忽略功能时，敏感词标签仍能被正确查找
 *
 * src/main/java/com/github/houbb/sensitive/word/support/result/WordResultHandlerWordTags.java
 * @since 0.25.1
 */
public class Bug105Test {

        @Test
        public void testNoiseCharacterInTaggedWords() {
                Map<String, Set<String>> newHashMap = new HashMap<>();
                newHashMap.put("毛主席", new HashSet<>(Arrays.asList("政治", "领导人")));

                // 配置同时启用字符忽略和标签的实例
                SensitiveWordBs ignoreAndTagWordBs = SensitiveWordBs.newInstance()
                                .charIgnore(SensitiveWordCharIgnores.specialChars()) // 启用字符忽略
                                .wordTag(WordTags.map(newHashMap))
                                .init();

                // 包含噪音字符的敏感词文本
                final String noisyText = "你好毛---主---席";

                // 测试同时启用字符忽略和标签的实例（修复前会失败）
                List<WordTagsDto> fixedWord = ignoreAndTagWordBs.findAll(noisyText, WordResultHandlers.wordTags());
                Assert.assertEquals(1, fixedWord.size());
                Assert.assertEquals("毛---主---席", fixedWord.get(0).getWord());
                Assert.assertNotNull("标签不应为空", fixedWord.get(0).getTags());
                Assert.assertTrue("应包含'政治'标签", fixedWord.get(0).getTags().contains("政治"));
                Assert.assertTrue("应包含'领导人'标签", fixedWord.get(0).getTags().contains("领导人"));
        }

        @Test
        public void testNoiseCharacterInTaggedWords2() {
                Map<String, Set<String>> newHashMap = new HashMap<>();
                newHashMap.put("毛主席", new HashSet<>(Arrays.asList("政治", "领导人")));
                newHashMap.put("毛---主---席", new HashSet<>(Arrays.asList("政治", "领导人", "自定义的")));

                // 配置同时启用字符忽略和标签的实例
                SensitiveWordBs ignoreAndTagWordBs = SensitiveWordBs.newInstance()
                        .charIgnore(SensitiveWordCharIgnores.specialChars()) // 启用字符忽略
                        .wordTag(WordTags.map(newHashMap))
                        .init();

                // 包含噪音字符的敏感词文本
                final String noisyText = "你好毛---主---席";

                // 测试同时启用字符忽略和标签的实例（修复前会失败）
                List<WordTagsDto> fixedWord = ignoreAndTagWordBs.findAll(noisyText, WordResultHandlers.wordTags());
                Assert.assertEquals(1, fixedWord.size());
                Assert.assertEquals("[政治, 自定义的, 领导人]", fixedWord.get(0).getTags().toString());
        }

        @Test
        public void testNoiseCharacterInTaggedWords3() {
                Map<String, Set<String>> newHashMap = new HashMap<>();
                newHashMap.put("毛xxx主xxxx席", new HashSet<>(Arrays.asList("政治", "领导人", "自定义的")));

                // 配置同时启用字符忽略和标签的实例
                SensitiveWordBs ignoreAndTagWordBs = SensitiveWordBs.newInstance()
                        .charIgnore(SensitiveWordCharIgnores.specialChars()) // 启用字符忽略
                        .wordTag(WordTags.map(newHashMap))
                        .init();

                // 包含噪音字符的敏感词文本
                final String noisyText = "你好毛---主---席";

                // 测试同时启用字符忽略和标签的实例（修复前会失败）
                List<WordTagsDto> fixedWord = ignoreAndTagWordBs.findAll(noisyText, WordResultHandlers.wordTags());
                Assert.assertEquals(1, fixedWord.size());
                Assert.assertTrue(CollectionUtil.isEmpty(fixedWord.get(0).getTags()));
        }

}
