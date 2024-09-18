package com.github.houbb.sensitive.word.support.combine.allowdeny;

import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.api.combine.IWordAllowDenyCombine;
import com.github.houbb.sensitive.word.utils.InnerWordFormatUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author d
 * @since 0.8.0
 */
public abstract class AbstractWordAllowDenyCombine implements IWordAllowDenyCombine {

    protected abstract Collection<String> doGetActualDenyList(List<String> allowList,
                                                        List<String> denyList,
                                                        IWordContext context);

    @Override
    public Collection<String> getActualDenyList(final List<String> allowList,
                                                final List<String> denyList,
                                                IWordContext context) {
        List<String> formatAllowList = InnerWordFormatUtils.formatWordList(allowList, context);
        List<String> formatDenyList = InnerWordFormatUtils.formatWordList(denyList, context);

        if (CollectionUtil.isEmpty(formatDenyList)) {
            return Collections.emptyList();
        }
        if (CollectionUtil.isEmpty(formatAllowList)) {
            return formatDenyList;
        }

        return doGetActualDenyList(formatAllowList, formatDenyList, context);
    }

}
