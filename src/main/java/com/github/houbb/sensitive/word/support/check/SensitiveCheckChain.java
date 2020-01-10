package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.sensitive.word.api.ISensitiveCheck;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;

import java.util.List;

/**
 * 敏感词检测责任链模式
 *
 * 这里可以提供一个公共的父类。
 *
 *
 * DFA 算法的优化可以参考论文：
 * 【DFA 算法】各种论文。
 *
 * @author binbin.hou
 * @since 0.0.5
 */
@ThreadSafe
public class SensitiveCheckChain implements ISensitiveCheck {

    @Override
    public int checkSensitive(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        // 初始化责任链
        List<ISensitiveCheck> sensitiveChecks = Guavas.newArrayList();
        // 默认添加敏感词校验
        sensitiveChecks.add(Instances.singleton(SensitiveWordCheck.class));
        if(context.sensitiveNumCheck()) {
            sensitiveChecks.add(Instances.singleton(SensitiveNumCheck.class));
        }
        if(context.sensitiveEmailCheck()) {
            sensitiveChecks.add(Instances.singleton(SensitiveEmailCheck.class));
        }

        // 循环调用
        //TODO: 这里同时满足两个条件，会出现 BUG
        for(ISensitiveCheck sensitiveCheck : sensitiveChecks) {
            System.out.println(sensitiveCheck.getClass().getSimpleName()+"check start");
            int result = sensitiveCheck.checkSensitive(txt, beginIndex, validModeEnum, context);

            if(result > 0) {
                return result;
            }
        }

        // 默认返回 0
        return 0;
    }

}
