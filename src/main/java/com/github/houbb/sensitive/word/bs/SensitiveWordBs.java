package com.github.houbb.sensitive.word.bs;

import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.*;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;
import com.github.houbb.sensitive.word.support.check.impl.SensitiveChecks;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import com.github.houbb.sensitive.word.support.format.CharFormats;
import com.github.houbb.sensitive.word.support.map.WordMaps;
import com.github.houbb.sensitive.word.support.replace.SensitiveWordReplaces;
import com.github.houbb.sensitive.word.support.result.WordResultHandlers;
import com.github.houbb.sensitive.word.utils.InnerWordDataUtils;

import java.util.List;

/**
 * 敏感词引导类
 *
 * @author binbin.hou
 * @since 0.0.1
 */
public class SensitiveWordBs {

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
    private boolean sensitiveCheckNum = true;
    /**
     * 启用邮箱检测
     */
    private boolean sensitiveCheckEmail = true;
    /**
     * 启用 URL 检测
     */
    private boolean sensitiveCheckUrl = true;

    // 额外配置
    /**
     * 检测数字时的长度
     */
    private int sensitiveCheckNumLen = 8;

    //------------------------------------------------------------- 基本属性 END
    /**
     * 敏感词 map
     *
     * TODO: 暂时定义为 final，后续放开抽象。
     *
     * @since 0.0.1
     */
    private final IWordMap wordMap = WordMaps.defaults();

    /**
     * 禁止的单词
     * @since 0.0.13
     */
    private IWordDeny wordDeny = WordDenys.system();

    /**
     * 允许的单词
     * @since 0.0.13
     */
    private IWordAllow wordAllow = WordAllows.system();

    /**
     * 替换策略
     * @since 0.3.0
     */
    private ISensitiveWordReplace sensitiveWordReplace = SensitiveWordReplaces.chars();

    /**
     * 上下文
     * @since 0.3.0
     */
    private IWordContext context = SensitiveWordContext.newInstance();

    public SensitiveWordBs sensitiveWordReplace(ISensitiveWordReplace sensitiveWordReplace) {
        ArgUtil.notNull(sensitiveWordReplace, "sensitiveWordReplace");
        this.sensitiveWordReplace = sensitiveWordReplace;
        return this;
    }

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
        // 初始化 context
        this.initContext();

        // 替换策略
        final ICharFormat charFormat = CharFormats.initCharFormat(context);
        context.charFormat(charFormat);

        // 3. 初始化对应的 sensitiveCheck
        final ISensitiveCheck sensitiveCheck = SensitiveChecks.initSensitiveCheck(context);
        context.sensitiveCheck(sensitiveCheck);

        //2. 初始化 word
        this.initWordMap();

        return this;
    }

    /**
     * 构建默认的上下文
     *
     * @return 结果
     * @since 0.0.4
     */
    private IWordContext initContext() {
        this.context = SensitiveWordContext.newInstance();

        // 格式统一化
        context.ignoreCase(ignoreCase);
        context.ignoreWidth(ignoreWidth);
        context.ignoreNumStyle(ignoreNumStyle);
        context.ignoreChineseStyle(ignoreChineseStyle);
        context.ignoreEnglishStyle(ignoreEnglishStyle);
        context.ignoreRepeat(ignoreRepeat);

        // 开启校验
        context.sensitiveCheckNum(sensitiveCheckNum);
        context.sensitiveCheckEmail(sensitiveCheckEmail);
        context.sensitiveCheckUrl(sensitiveCheckUrl);

        // 额外配置
        context.sensitiveCheckNumLen(sensitiveCheckNumLen);
        context.sensitiveWordReplace(sensitiveWordReplace);

        return context;
    }

    /**
     * DCL 初始化 wordMap 信息
     *
     * 注意：map 的构建是一个比较耗时的动作
     * @since 0.0.4
     */
    private synchronized void initWordMap() {
        // 加载配置信息
        List<String> denyList = wordDeny.deny();
        List<String> allowList = wordAllow.allow();
        List<String> results = InnerWordDataUtils.getActualDenyList(denyList, allowList, context);

        // 便于可以多次初始化
        wordMap.initWordMap(results);
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

    /**
     * 设置是否启动数字检测
     *
     * @param enableNumCheck 数字检测
     * @since 0.0.11
     * @return this
     */
    public SensitiveWordBs enableNumCheck(boolean enableNumCheck) {
        this.sensitiveCheckNum = enableNumCheck;
        return this;
    }

    /**
     * 检测敏感词对应的长度限制，便于用户灵活定义
     * @param numCheckLen 长度
     * @return this
     * @since 0.2.1
     */
    public SensitiveWordBs numCheckLen(int numCheckLen) {
        this.sensitiveCheckNumLen = numCheckLen;
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
        this.sensitiveCheckEmail = enableEmailCheck;
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
        this.sensitiveCheckUrl = enableUrlCheck;
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
        return wordMap.contains(target, context);
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

        List<IWordResult> wordResults = wordMap.findAll(target, context);
        return CollectionUtil.toList(wordResults, new IHandler<IWordResult, R>() {
            @Override
            public R handle(IWordResult wordResult) {
                return handler.handle(wordResult);
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

        IWordResult wordResult = wordMap.findFirst(target, context);
        return handler.handle(wordResult);
    }

    /**
     * 替换所有内容
     *
     * @param target      目标字符串
     * @return 替换后结果
     * @since 0.2.0
     */
    public String replace(final String target) {
        return wordMap.replace(target, context);
    }

    //------------------------------------------------------------------------------------ 公开方法 END

}
