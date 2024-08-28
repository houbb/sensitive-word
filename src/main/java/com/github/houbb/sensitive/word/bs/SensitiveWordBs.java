package com.github.houbb.sensitive.word.bs;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.*;
import com.github.houbb.sensitive.word.api.combine.IWordAllowDenyCombine;
import com.github.houbb.sensitive.word.api.combine.IWordCheckCombine;
import com.github.houbb.sensitive.word.api.combine.IWordFormatCombine;
import com.github.houbb.sensitive.word.core.SensitiveWords;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.combine.allowdeny.WordAllowDenyCombines;
import com.github.houbb.sensitive.word.support.combine.check.WordCheckCombines;
import com.github.houbb.sensitive.word.support.combine.format.WordFormatCombines;
import com.github.houbb.sensitive.word.support.data.WordDatas;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import com.github.houbb.sensitive.word.support.ignore.SensitiveWordCharIgnores;
import com.github.houbb.sensitive.word.support.replace.WordReplaces;
import com.github.houbb.sensitive.word.support.result.WordResultHandlers;
import com.github.houbb.sensitive.word.support.resultcondition.WordResultConditions;
import com.github.houbb.sensitive.word.support.tag.WordTags;

import java.util.*;

/**
 * 敏感词引导类
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public class SensitiveWordBs implements ISensitiveWordDestroy {

    /**
     * 私有化构造器
     *
     * @since 0.0.1
     */
    private SensitiveWordBs() {
    }


    //------------------------------------------------------------- 基本属性 START
    // 格式统一化
    /**
     * 是否忽略大小写
     */
    private boolean ignoreCase = true;
    /**
     * 是否忽略全角、半角
     */
    private boolean ignoreWidth = true;
    /**
     * 是否忽略数字样式
     */
    private boolean ignoreNumStyle = true;
    /**
     * 是否忽略中文样式
     */
    private boolean ignoreChineseStyle = true;
    /**
     * 是否忽略英文样式
     */
    private boolean ignoreEnglishStyle = true;
    /**
     * 是否忽略重复
     */
    private boolean ignoreRepeat = false;

    // 开启校验
    /**
     * 启用数字检测
     */
    private boolean enableNumCheck = false;
    /**
     * 启用邮箱检测
     */
    private boolean enableEmailCheck = false;
    /**
     * 启用 URL 检测
     */
    private boolean enableUrlCheck = false;

    /**
     * 单词校验
     * @since 0.4.0
     */
    private boolean enableWordCheck = true;

    /**
     * 是否启用 ipv4 校验
     * @since 0.17.0
     */
    private boolean enableIpv4Check = false;

    // 额外配置
    /**
     * 检测数字时的长度
     */
    private int numCheckLen = 8;

    //------------------------------------------------------------- 基本属性 END
    /**
     * 脱敏策略
     * @since 0.3.2
     */
    private ISensitiveWord sensitiveWord = SensitiveWords.defaults();

    /**
     * 敏感词 Data
     *
     * @since 0.0.1
     */
    private IWordData wordData = WordDatas.defaults();

    /**
     * 禁止的单词
     * @since 0.0.13
     */
    private IWordDeny wordDeny = WordDenys.defaults();

    /**
     * 允许的单词
     * @since 0.0.13
     */
    private IWordAllow wordAllow = WordAllows.defaults();

    /**
     * 替换策略
     * @since 0.3.0
     */
    private IWordReplace wordReplace = WordReplaces.defaults();

    /**
     * 上下文
     * @since 0.3.0
     */
    private IWordContext context = SensitiveWordContext.newInstance();

    /**
     * 单词检测组合策略
     * @since 0.8.0
     */
    private IWordCheckCombine wordCheckCombine = WordCheckCombines.defaults();

    /**
     * 单词格式化组合策略
     * @since 0.8.0
     */
    private IWordFormatCombine wordFormatCombine = WordFormatCombines.defaults();

    /**
     * 单词组合策略
     * @since 0.8.0
     */
    private IWordAllowDenyCombine wordAllowDenyCombine = WordAllowDenyCombines.defaults();

    /**
     * 单词标签
     * @since 0.10.0
     */
    private IWordTag wordTag = WordTags.none();

    /**
     * 忽略的字符策略
     * @since 0.11.0
     */
    private ISensitiveWordCharIgnore charIgnore = SensitiveWordCharIgnores.defaults();

    /**
     * 敏感词结果匹配策略
     * @since 0.13.0
     */
    private IWordResultCondition wordResultCondition = WordResultConditions.alwaysTrue();

    /**
     * 新建验证实例
     * <p>
     * double-lock
     *
     * @return this
     * @since 0.0.1
     */
    public static SensitiveWordBs newInstance() {
        return new SensitiveWordBs();
    }

    /**
     * 初始化
     *
     * 1. 根据配置，初始化对应的 map。比较消耗性能。
     * @since 0.0.13
     * @return this
     */
    public SensitiveWordBs init() {
        // 1. 初始化 context
        IWordContext context = this.initContext();

        // 2. 格式化策略
        final IWordFormat charFormat = wordFormatCombine.initWordFormat(context);
        context.wordFormat(charFormat);

        // 3. 初始化对应的 Check 策略
        final IWordCheck sensitiveCheck = wordCheckCombine.initWordCheck(context);
        context.sensitiveCheck(sensitiveCheck);

        // 4. 初始化 word
        Collection<String> denyList  = wordAllowDenyCombine.getActualDenyList(wordAllow, wordDeny, context);
        wordData.initWordData(denyList);

        //5. 更新 context
        this.context = context;

        return this;
    }

    /**
     * 构建默认的上下文
     *
     * @return 结果
     * @since 0.0.4
     */
    private IWordContext initContext() {
        IWordContext context = SensitiveWordContext.newInstance();

        // 格式统一化
        context.ignoreCase(ignoreCase);
        context.ignoreWidth(ignoreWidth);
        context.ignoreNumStyle(ignoreNumStyle);
        context.ignoreChineseStyle(ignoreChineseStyle);
        context.ignoreEnglishStyle(ignoreEnglishStyle);
        context.ignoreRepeat(ignoreRepeat);

        // 开启校验
        context.enableNumCheck(enableNumCheck);
        context.enableEmailCheck(enableEmailCheck);
        context.enableUrlCheck(enableUrlCheck);
        context.enableWordCheck(enableWordCheck);
        context.enableIpv4Check(enableIpv4Check);

        // 额外配置
        context.sensitiveCheckNumLen(numCheckLen);
        context.wordReplace(wordReplace);
        context.wordData(wordData);
        context.wordTag(wordTag);
        context.charIgnore(charIgnore);
        context.wordResultCondition(wordResultCondition);

        return context;
    }

    public SensitiveWordBs wordResultCondition(IWordResultCondition wordResultCondition) {
        ArgUtil.notNull(wordResultCondition, "wordResultCondition");

        this.wordResultCondition = wordResultCondition;
        return this;
    }

    public SensitiveWordBs charIgnore(ISensitiveWordCharIgnore charIgnore) {
        ArgUtil.notNull(charIgnore, "charIgnore");

        this.charIgnore = charIgnore;
        return this;
    }

    public SensitiveWordBs wordTag(IWordTag wordTag) {
        ArgUtil.notNull(wordTag, "wordTag");

        this.wordTag = wordTag;
        return this;
    }

    public SensitiveWordBs wordCheckCombine(IWordCheckCombine wordCheckCombine) {
        ArgUtil.notNull(wordCheckCombine, "wordCheckCombine");

        this.wordCheckCombine = wordCheckCombine;
        return this;
    }

    public SensitiveWordBs wordFormatCombine(IWordFormatCombine wordFormatCombine) {
        ArgUtil.notNull(wordFormatCombine, "wordFormatCombine");

        this.wordFormatCombine = wordFormatCombine;
        return this;
    }

    public SensitiveWordBs wordAllowDenyCombine(IWordAllowDenyCombine wordAllowDenyCombine) {
        ArgUtil.notNull(wordAllowDenyCombine, "wordAllowDenyCombine");

        this.wordAllowDenyCombine = wordAllowDenyCombine;
        return this;
    }

    /**
     * 允许指定策略数据
     * @param wordData 单词数据
     * @return 结果
     * @since 0.7.0
     */
    public SensitiveWordBs wordData(IWordData wordData) {
        ArgUtil.notNull(wordData, "wordData");

        this.wordData = wordData;
        return this;
    }

    public SensitiveWordBs sensitiveWord(ISensitiveWord sensitiveWord) {
        ArgUtil.notNull(sensitiveWord, "sensitiveWord");

        this.sensitiveWord = sensitiveWord;
        return this;
    }

    /**
     * 设置替换策略
     * @param wordReplace 替换
     * @return 结果
     */
    public SensitiveWordBs wordReplace(IWordReplace wordReplace) {
        ArgUtil.notNull(wordReplace, "wordReplace");
        this.wordReplace = wordReplace;
        return this;
    }

    /**
     * 设置禁止的实现
     * @param wordDeny 禁止的实现
     * @return this
     * @since 0.0.13
     */
    public SensitiveWordBs wordDeny(IWordDeny wordDeny) {
        ArgUtil.notNull(wordDeny, "wordDeny");
        this.wordDeny = wordDeny;
        return this;
    }

    /**
     * 设置允许的实现
     * @param wordAllow 允许的实现
     * @return this
     * @since 0.0.13
     */
    public SensitiveWordBs wordAllow(IWordAllow wordAllow) {
        ArgUtil.notNull(wordAllow, "wordAllow");
        this.wordAllow = wordAllow;
        return this;
    }

    //-------------------------------------------------------- 基础属性设置
    /**
     * 是否启用 ipv4 校验
     * @param enableIpv4Check 是否启用
     * @return this
     * @since 0.17.0
     */
    public SensitiveWordBs enableIpv4Check(boolean enableIpv4Check) {
        this.enableIpv4Check = enableIpv4Check;
        return this;
    }

    /**
     * 设置是否启动数字检测
     *
     * @param enableWordCheck 数字检测
     * @since 0.0.11
     * @return this
     */
    public SensitiveWordBs enableWordCheck(boolean enableWordCheck) {
        this.enableWordCheck = enableWordCheck;
        return this;
    }

    /**
     * 设置是否启动数字检测
     *
     * @param enableNumCheck 数字检测
     * @since 0.0.11
     * @return this
     */
    public SensitiveWordBs enableNumCheck(boolean enableNumCheck) {
        this.enableNumCheck = enableNumCheck;
        return this;
    }

    /**
     * 检测敏感词对应的长度限制，便于用户灵活定义
     * @param numCheckLen 长度
     * @return this
     * @since 0.2.1
     */
    public SensitiveWordBs numCheckLen(int numCheckLen) {
        this.numCheckLen = numCheckLen;
        return this;
    }

    /**
     * 设置是否启动 email 检测
     *
     * @param enableEmailCheck email 检测
     * @since 0.0.11
     * @return this
     */
    public SensitiveWordBs enableEmailCheck(boolean enableEmailCheck) {
        this.enableEmailCheck = enableEmailCheck;
        return this;
    }

    /**
     * 设置是否启动 url 检测
     *
     * @param enableUrlCheck url 检测
     * @since 0.0.12
     * @return this
     */
    public SensitiveWordBs enableUrlCheck(boolean enableUrlCheck) {
        this.enableUrlCheck = enableUrlCheck;
        return this;
    }

    /**
     * 是否忽略大小写
     * @param ignoreCase 大小写
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordBs ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    /**
     * 是否忽略半角全角
     * @param ignoreWidth 半角全角
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordBs ignoreWidth(boolean ignoreWidth) {
        this.ignoreWidth = ignoreWidth;
        return this;
    }

    /**
     * 是否忽略数字格式
     * @param ignoreNumStyle 数字格式
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordBs ignoreNumStyle(boolean ignoreNumStyle) {
        this.ignoreNumStyle = ignoreNumStyle;
        return this;
    }

    /**
     * 是否忽略中文样式
     * @param ignoreChineseStyle 中文样式
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordBs ignoreChineseStyle(boolean ignoreChineseStyle) {
        this.ignoreChineseStyle = ignoreChineseStyle;
        return this;
    }

    /**
     * 是否忽略英文样式
     * @param ignoreEnglishStyle 英文样式
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordBs ignoreEnglishStyle(boolean ignoreEnglishStyle) {
        this.ignoreEnglishStyle = ignoreEnglishStyle;
        return this;
    }

    /**
     * 是否忽略重复
     * @param ignoreRepeat 忽略重复
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordBs ignoreRepeat(boolean ignoreRepeat) {
        this.ignoreRepeat = ignoreRepeat;
        return this;
    }

    //------------------------------------------------------------------------------------ 公开方法 START
    /**
     * 是否包含敏感词
     *
     * @param target 目标字符串
     * @return 是否
     * @since 0.0.1
     */
    public boolean contains(final String target) {
        return sensitiveWord.contains(target, context);
    }

    /**
     * 返回所有的敏感词
     * 1. 这里是默认去重的，且是有序的。
     * 2. 如果不存在，返回空列表
     *
     * @param target 目标字符串
     * @return 敏感词列表
     * @since 0.0.1
     */
    public List<String> findAll(final String target) {
        return findAll(target, WordResultHandlers.word());
    }

    /**
     * 返回第一个敏感词
     * （1）如果不存在，则返回 {@code null}
     *
     * @param target 目标字符串
     * @return 敏感词
     * @since 0.0.1
     */
    public String findFirst(final String target) {
        return findFirst(target, WordResultHandlers.word());
    }

    /**
     * 返回所有的敏感词
     * 1. 这里是默认去重的，且是有序的。
     * 2. 如果不存在，返回空列表
     *
     * @param target 目标字符串
     * @param <R> 泛型
     * @param handler 处理类
     * @return 敏感词列表
     * @since 0.0.1
     */
    public <R> List<R> findAll(final String target, final IWordResultHandler<R> handler) {
        ArgUtil.notNull(handler, "handler");

        List<IWordResult> wordResults = sensitiveWord.findAll(target, context);
        return CollectionUtil.toList(wordResults, new IHandler<IWordResult, R>() {
            @Override
            public R handle(IWordResult wordResult) {
                return handler.handle(wordResult, context, target);
            }
        });
    }

    /**
     * 返回第一个敏感词
     * （1）如果不存在，则返回 {@code null}
     *
     * @param target 目标字符串
     * @param handler 处理类
     * @param <R> 泛型
     * @return 敏感词
     * @since 0.0.1
     */
    public <R> R findFirst(final String target, final IWordResultHandler<R> handler) {
        ArgUtil.notNull(handler, "handler");

        IWordResult wordResult = sensitiveWord.findFirst(target, context);
        return handler.handle(wordResult, context, target);
    }

    /**
     * 替换所有内容
     *
     * @param target      目标字符串
     * @return 替换后结果
     * @since 0.2.0
     */
    public String replace(final String target) {
        return sensitiveWord.replace(target, context);
    }

    /**
     * 获取敏感词的标签
     *
     * @param word 敏感词
     * @return 结果
     * @since 0.10.0
     */
    public Set<String> tags(final String word) {
        if(StringUtil.isEmpty(word)) {
            return Collections.emptySet();
        }

        // 是否需要格式化？
        return wordTag.getTag(word);
    }

    @Override
    public void destroy() {
        this.wordData.destroy();
    }

    /**
     * 删除敏感词
     * @param word 单词
     * @since 0.19.0
     */
    public void removeWord(String word, String ... others) {
        List<String> wordList = new ArrayList<>();
        wordList.add(word);
        wordList.addAll(Arrays.asList(others));

        removeWord(wordList);
    }

    /**
     * 删除单词
     * @param collection 集合
     * @since 0.19.0
     */
    public void removeWord(Collection<String> collection) {
        if(CollectionUtil.isEmpty(collection)) {
            return;
        }
        for(String word : collection) {
            this.wordData.removeWord(word);
        }
    }

    /**
     * 新增敏感词
     * @param collection 敏感词集合
     * @since 0.19.0
     */
    public void addWord(Collection<String> collection) {
        this.wordData.addWord(collection);
    }

    /**
     * 新增敏感词
     * @param word 敏感词
     * @param others 其他
     * @since 0.19.0
     */
    public void addWord(String word, String...others) {
        List<String> wordList = new ArrayList<>();
        wordList.add(word);
        wordList.addAll(Arrays.asList(others));

        this.addWord(wordList);
    }

    //------------------------------------------------------------------------------------ 公开方法 END

}
