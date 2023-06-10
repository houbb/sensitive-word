package com.github.houbb.sensitive.word.support.combine.check;

import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.support.check.WordChecks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author d
 * @since 0.8.0
 */
public class WordCheckCombine extends AbstractWordCheckCombine {

    @Override
    protected List<IWordCheck> getWordCheckList(IWordContext context) {
        List<IWordCheck> wordCheckList = new ArrayList<>();

        if(context.enableWordCheck()) {
            wordCheckList.add(WordChecks.word());
        }
        if(context.enableNumCheck()) {
            wordCheckList.add(WordChecks.num());
        }
        if(context.enableEmailCheck()) {
            wordCheckList.add(WordChecks.email());
        }
        if(context.enableUrlCheck()) {
            wordCheckList.add(WordChecks.url());
        }

        return wordCheckList;
    }

}
