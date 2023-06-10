package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.sensitive.word.api.IWordCheck;

/**
 * 敏感信息监测接口结果
 *
 * 可以使用责任链的模式，循环调用。
 * @author binbin.hou
 * @since 0.0.12
 */
public class WordCheckResult {

    /**
     * 下标
     * @since 0.0.12
     */
    private int index;

    /**
     * 检测类
     * @since 0.0.12
     */
    private Class<? extends IWordCheck> checkClass;

    /**
     * 实例化
     * @param index 返回索引
     * @param checkClass 验证类
     * @return 结果
     * @since 0.0.12
     */
    public static WordCheckResult of(final int index,
                                     final Class<? extends IWordCheck> checkClass) {
        WordCheckResult result = new WordCheckResult();
        result.index(index).checkClass(checkClass);
        return result;
    }

    public int index() {
        return index;
    }

    public WordCheckResult index(int index) {
        this.index = index;
        return this;
    }

    public Class<? extends IWordCheck> checkClass() {
        return checkClass;
    }

    public WordCheckResult checkClass(Class<? extends IWordCheck> checkClass) {
        this.checkClass = checkClass;
        return this;
    }

    @Override
    public String toString() {
        return "SensitiveCheckResult{" +
                "index=" + index +
                ", checkClass=" + checkClass +
                '}';
    }

}
