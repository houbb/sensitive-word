package com.github.houbb.sensitive.word.bs;

import com.github.houbb.heaven.constant.CharConst;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.api.IWordMap;
import com.github.houbb.sensitive.word.support.data.SensitiveWordData;
import com.github.houbb.sensitive.word.support.map.SensitiveWordMap;

import java.util.List;

/**
 * 敏感词引导类
 * @author binbin.hou
 * @since 0.0.1
 */
public class SensitiveWordBs {

    /**
     * 私有化构造器
     * @since 0.0.1
     */
    private SensitiveWordBs(){}

    /**
     * 敏感词 map
     * @since 0.0.1
     */
    private static volatile IWordMap sensitiveWordMap;

    /**
     * 默认的执行上下文
     * @since 0.0.4
     */
    private volatile IWordContext context;

    /**
     * DCL 初始化 wordMap 信息
     * @return 初始化后的结果
     * @since 0.0.4
     */
    private static IWordMap initWordMap() {
        if(sensitiveWordMap == null) {
            synchronized (IWordMap.class) {
                if(sensitiveWordMap == null) {
                    // 加载配置信息
                    IWordData wordData = new SensitiveWordData();
                    List<String> lines = wordData.getWordData();

                    // 初始化 DFA 信息
                    sensitiveWordMap = new SensitiveWordMap();
                    sensitiveWordMap.initWordMap(lines);
                }
            }
        }

        return sensitiveWordMap;
    }

    /**
     * 新建验证实例
     *
     * double-lock
     * @return this
     * @since 0.0.1
     */
    public static SensitiveWordBs newInstance() {
        initWordMap();

        SensitiveWordBs bs = new SensitiveWordBs();
        bs.context = buildDefaultContext();
        return bs;
    }

    /**
     * 构建默认的上下文
     * @return 结果
     * @since 0.0.4
     */
    private static IWordContext buildDefaultContext() {
        IWordContext wordContext = SensitiveWordContext.newInstance();
        wordContext.ignoreCase(true);
        wordContext.ignoreWidth(true);

        return wordContext;
    }
    /**
     * 是否包含敏感词
     * @param target 目标字符串
     * @return 是否
     * @since 0.0.1
     */
    public boolean contains(final String target) {
        return sensitiveWordMap.contains(target, context);
    }

    /**
     * 返回所有的敏感词
     * 1. 这里是默认去重的，且是有序的。
     * 2. 如果不存在，返回空列表
     * @param target 目标字符串
     * @return 敏感词列表
     * @since 0.0.1
     */
    public List<String> findAll(final String target) {
        return sensitiveWordMap.findAll(target, context);
    }

    /**
     * 返回第一个敏感词
     * （1）如果不存在，则返回 {@code null}
     * @param target 目标字符串
     * @return 敏感词
     * @since 0.0.1
     */
    public String findFirst(final String target) {
        return sensitiveWordMap.findFirst(target, context);
    }

    /**
     * 替换所有内容
     * @param target 目标字符串
     * @param replaceChar 替换为的 char
     * @return 替换后结果
     * @since 0.0.2
     */
    public String replace(final String target, final char replaceChar) {
        return sensitiveWordMap.replace(target, replaceChar, context);
    }

    /**
     * 替换所有内容
     * @param target 目标字符串
     * @return 替换后结果
     * @since 0.0.2
     */
    public String replace(final String target) {
        return this.replace(target, CharConst.STAR);
    }

}
