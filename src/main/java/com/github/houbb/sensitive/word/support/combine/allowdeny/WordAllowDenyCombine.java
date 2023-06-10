package com.github.houbb.sensitive.word.support.combine.allowdeny;

import com.github.houbb.sensitive.word.api.IWordContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author d
 * @since 0.8.0
 */
public class WordAllowDenyCombine extends AbstractWordAllowDenyCombine{

    @Override
    protected Collection<String> doGetActualDenyList(List<String> allowList,
                                                     List<String> denyList,
                                                     IWordContext context) {
        Set<String> resultSet = new HashSet<>(denyList.size());

        // O(1)
        Set<String> allowSet = new HashSet<>(allowList);

        for(String deny : denyList) {
            if(allowSet.contains(deny)) {
                continue;
            }

            resultSet.add(deny);
        }
        return resultSet;
    }


}
