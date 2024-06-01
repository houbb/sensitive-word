package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.IWordContext;

import java.util.ArrayList;
import java.util.Collection;
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

        return new WordCheckInit() {
            @Override
            protected void init(Pipeline<IWordCheck> pipeline) {
                for(IWordCheck check : sensitiveChecks) {
                    pipeline.addLast(check);
                }
            }
        };
    }

    public static IWordCheck chains(final Collection<IWordCheck> sensitiveChecks) {
        if (CollectionUtil.isEmpty(sensitiveChecks)){
            return none();
        }

        return new WordCheckInit() {
            @Override
            protected void init(Pipeline<IWordCheck> pipeline) {
                for(IWordCheck check : sensitiveChecks) {
                    pipeline.addLast(check);
                }
            }
        };
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

}
