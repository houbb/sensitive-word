package com.github.houbb.sensitive.word.support.combine.check;

import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.combine.IWordCheckCombine;
import com.github.houbb.sensitive.word.support.check.WordChecks;

import java.util.List;

/**
 * @author d
 * @since 0.8.0
 */
public abstract class AbstractWordCheckCombine implements IWordCheckCombine {

    protected abstract List<IWordCheck> getWordCheckList(IWordContext context);

    @Override
    public IWordCheck initWordCheck(IWordContext context) {
        List<IWordCheck> wordCheckList = getWordCheckList(context);

        return WordChecks.chains(wordCheckList);
    }

}
