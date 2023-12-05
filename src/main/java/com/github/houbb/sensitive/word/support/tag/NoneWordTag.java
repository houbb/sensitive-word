package com.github.houbb.sensitive.word.support.tag;

import java.util.Collections;
import java.util.Set;

/**
 * 空标签
 *
 * word tag1,tag2
 * @since 0.10.0
 */
public class NoneWordTag extends AbstractWordTag {

    @Override
    protected Set<String> doGetTag(String word) {
        return Collections.emptySet();
    }

}
