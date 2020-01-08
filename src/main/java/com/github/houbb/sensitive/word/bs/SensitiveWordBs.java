package com.github.houbb.sensitive.word.bs;

import com.github.houbb.heaven.constant.CharConst;
import com.github.houbb.heaven.support.instance.impl.Instances;
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
     * 敏感数据信息
     * @since 0.0.1
     */
    private IWordData sensitiveWordData = Instances.singleton(SensitiveWordData.class);

    /**
     * 敏感词 map
     * @since 0.0.1
     */
    private IWordMap sensitiveWordMap = Instances.singleton(SensitiveWordMap.class);

    /**
     * 获取单例信息
     * @since 0.0.1
     */
    private static final SensitiveWordBs INSTANCE;

    static {
        synchronized (SensitiveWordBs.class) {
            INSTANCE = new SensitiveWordBs();
            List<String> lines = INSTANCE.sensitiveWordData.getWordData();
            INSTANCE.sensitiveWordMap.initWordMap(lines);
        }
    }

    /**
     * 新建验证实例
     * @return this
     * @since 0.0.1
     */
    public static SensitiveWordBs getInstance() {
        return INSTANCE;
    }

    /**
     * 是否包含敏感词
     * @param target 目标字符串
     * @return 是否
     * @since 0.0.1
     */
    public boolean contains(final String target) {
        return this.sensitiveWordMap.contains(target);
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
        return this.sensitiveWordMap.findAll(target);
    }

    /**
     * 返回第一个敏感词
     * （1）如果不存在，则返回 {@code null}
     * @param target 目标字符串
     * @return 敏感词
     * @since 0.0.1
     */
    public String findFirst(final String target) {
        return this.sensitiveWordMap.findFirst(target);
    }

    /**
     * 替换所有内容
     * @param target 目标字符串
     * @param replaceChar 替换为的 char
     * @return 替换后结果
     * @since 0.0.2
     */
    public String replace(final String target, final char replaceChar) {
        return this.sensitiveWordMap.replace(target, replaceChar);
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
