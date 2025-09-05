package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词检测工具
 * @since 0.3.0
 */
public final class WordChecks {

    private WordChecks(){}

    public static IWordCheck chains(final IWordCheck... sensitiveChecks) {
        if (ArrayUtil.isEmpty(sensitiveChecks)){
            return none();
        }

        List<IWordCheck> wordChecks = new ArrayList<>(sensitiveChecks.length);
        return array(wordChecks);
    }

    public static IWordCheck chains(final List<IWordCheck> sensitiveChecks) {
        if (CollectionUtil.isEmpty(sensitiveChecks)){
            return none();
        }

        return array(sensitiveChecks);
    }

    public static IWordCheck email() {
        return WordCheckEmail.getInstance();
    }

    public static IWordCheck num() {
        return WordCheckNum.getInstance();
    }

    public static IWordCheck url() {
        return WordCheckUrl.getInstance();
    }

    public static IWordCheck word() {
        return WordCheckWord.getInstance();
    }

    public static IWordCheck none()  {
        return WordCheckNone.getInstance();
    }

    /**
     * ipv4 校验
     * @since 0.17.0
     * @return 实现
     */
    public static IWordCheck ipv4()  {
        return WordCheckIPV4.getInstance();
    }

    /**
     * 不需要前缀的 urlPrefix
     * 注意：这种检测方法可能会和代码中的包名称冲突
     *
     * @return 实现
     * @since 0.25.0
     */
    public static IWordCheck urlNoPrefix() {
        return WordCheckUrlNoPrefix.getInstance();
    }

    /**
     * 集合
     *
     * @return 实现
     * @since 0.30.0
     */
    public static IWordCheck array(final List<IWordCheck> wordChecks) {
        return new WordCheckArray(wordChecks);
    }

}
