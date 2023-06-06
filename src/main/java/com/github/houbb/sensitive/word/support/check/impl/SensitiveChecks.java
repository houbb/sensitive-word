package com.github.houbb.sensitive.word.support.check.impl;

import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.util.util.ArrayUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.support.check.ISensitiveCheck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 敏感词检测工具
 * @since 0.3.0
 */
public final class SensitiveChecks {

    private SensitiveChecks(){}

    /**
     * 初始化敏感检测策略
     * @param context 上下文
     *
     * @return 实现
     * @since 0.3.0
     */
    public static ISensitiveCheck initSensitiveCheck(final IWordContext context) {
        List<ISensitiveCheck> sensitiveCheckList = new ArrayList<>();

        // 默认添加敏感词校验
        sensitiveCheckList.add(SensitiveChecks.word());

        if(context.sensitiveCheckNum()) {
            sensitiveCheckList.add(SensitiveChecks.num());
        }
        if(context.sensitiveCheckEmail()) {
            sensitiveCheckList.add(SensitiveChecks.email());
        }
        if(context.sensitiveCheckUrl()) {
            sensitiveCheckList.add(SensitiveChecks.url());
        }

        return SensitiveChecks.chains(sensitiveCheckList);
    }

    public static ISensitiveCheck chains(final ISensitiveCheck... sensitiveChecks) {
        if (ArrayUtil.isEmpty(sensitiveChecks)){
            return none();
        }

        return new SensitiveCheckInit() {
            @Override
            protected void init(Pipeline<ISensitiveCheck> pipeline) {
                for(ISensitiveCheck check : sensitiveChecks) {
                    pipeline.addLast(check);
                }
            }
        };
    }

    public static ISensitiveCheck chains(final Collection<ISensitiveCheck> sensitiveChecks) {
        if (CollectionUtil.isEmpty(sensitiveChecks)){
            return none();
        }

        return new SensitiveCheckInit() {
            @Override
            protected void init(Pipeline<ISensitiveCheck> pipeline) {
                for(ISensitiveCheck check : sensitiveChecks) {
                    pipeline.addLast(check);
                }
            }
        };
    }

    public static ISensitiveCheck email() {
        return SensitiveCheckEmail.getInstance();
    }

    public static ISensitiveCheck num() {
        return SensitiveCheckNum.getInstance();
    }

    public static ISensitiveCheck url() {
        return SensitiveCheckUrl.getInstance();
    }

    public static ISensitiveCheck word() {
        return SensitiveCheckWord.getInstance();
    }

    public static ISensitiveCheck none()  {
        return SensitiveCheckNone.getInstance();
    }

}
