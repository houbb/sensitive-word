package com.github.houbb.sensitive.word.api.combine;

import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordDeny;

import java.util.Collection;
import java.util.List;

/**
 * @author d
 * @since 0.8.0
 */
public interface IWordAllowDenyCombine {

    /**
     * 获取最终的拒绝单词列表
     * @param wordAllow 允许
     * @param wordDeny 拒绝
     * @param context 上下文
     * @return 结果
     * @since 0.8.0
     */
    Collection<String> getActualDenyList(IWordAllow wordAllow,
                                         IWordDeny wordDeny,
                                         final IWordContext context);

}
