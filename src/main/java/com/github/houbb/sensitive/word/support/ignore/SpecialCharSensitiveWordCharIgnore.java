package com.github.houbb.sensitive.word.support.ignore;

import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;

import java.util.Set;

/**
 * 特殊字符忽略
 * @since 0.11.0
 */
public class SpecialCharSensitiveWordCharIgnore extends AbstractSensitiveWordCharIgnore {

    private static final String SPECIAL = "`-=~!@#$%^&*()_+[]{}\\|;:'\",./<>?";

    private static final Set<Character> SET;

    static {
        SET = StringUtil.toCharSet(SPECIAL);
    }

    @Override
    protected boolean doIgnore(int ix, char[] chars, InnerSensitiveWordContext innerContext) {
        char c = chars[ix];
        return SET.contains(c);
    }

}
