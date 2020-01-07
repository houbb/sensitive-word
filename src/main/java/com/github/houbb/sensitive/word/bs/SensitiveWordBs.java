package com.github.houbb.sensitive.word.bs;

import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordData;
import com.github.houbb.sensitive.word.support.data.SensitiveWordData;

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
     * 待验证字符串信息
     * ps: 可以添加多个辅助类 xxxStringProvider
     * 如 FileXXX
     * @since 0.0.1
     */
    private volatile String target;

    /**
     * 敏感数据信息
     * @since 0.0.1
     */
    private IWordData sensitiveWordData = Instances.singleton(SensitiveWordData.class);

    /**
     * 新建验证实例
     * @param string 字符串
     * @return this
     * @since 0.0.1
     */
    public static SensitiveWordBs newInstance(final String string) {
        SensitiveWordBs instance = new SensitiveWordBs();
        instance.target = string;
        return instance;
    }

    /**
     * 指定目标字符串信息
     * @param string 字符串
     * @return this
     * @since 0.0.1
     */
    public SensitiveWordBs target(final String string) {
        this.target = string;
        return this;
    }

    /**
     * 是否合法
     * @return 是否
     * @since 0.0.1
     * @see #contains() 是否包含
     */
    public boolean valid() {
        return !contains();
    }

    /**
     * 是否包含敏感词
     * @return 是否
     * @since 0.0.1
     * @see #findAll() 列表不为空即可
     */
    public boolean contains() {
        return CollectionUtil.isNotEmpty(findAll());
    }

    /**
     * 返回所有的敏感词
     * 1. 这里是默认去重的。
     * @return 敏感词列表
     * @since 0.0.1
     */
    public List<String> findAll() {
        if(StringUtil.isEmpty(target)) {
            return Guavas.newArrayList();
        }

        // 分词
        return null;
    }

    /**
     * 执行过滤
     * 1. 使用默认策略
     * 2. 默认策略就是直接移除。
     * @return 过滤后的结果
     * @since 0.0.1
     */
    private String filter() {
        return filter(StringUtil.EMPTY);
    }

    /**
     * 指定过滤的字符，执行过滤
     * 1. filter 只是一种特殊的字符串替换策略。
     * @return 过滤后的结果
     * @since 0.0.1
     */
    private String filter(final String filter) {
        return "";
    }

}
