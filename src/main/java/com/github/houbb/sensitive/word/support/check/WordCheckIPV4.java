package com.github.houbb.sensitive.word.support.check;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.regex.RegexUtil;
import com.github.houbb.sensitive.word.api.IWordCheck;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.WordConst;
import com.github.houbb.sensitive.word.constant.enums.WordTypeEnum;
import com.github.houbb.sensitive.word.utils.InnerCharUtils;

import java.util.List;

/**
 * IPV4 检测
 *
 * @author binbin.hou
 * @since 0.17.0
 */
@ThreadSafe
public class WordCheckIPV4 extends AbstractConditionWordCheck {

    private static final IWordCheck INSTANCE = new WordCheckIPV4();

    public static IWordCheck getInstance() {
        return INSTANCE;
    }

    @Override
    protected Class<? extends IWordCheck> getSensitiveCheckClass() {
        return WordCheckIPV4.class;
    }

    @Override
    protected String getType() {
        return WordTypeEnum.IPV4.getCode();
    }

    @Override
    protected boolean isCharCondition(char mappingChar, int index, InnerSensitiveWordContext checkContext) {
        return CharUtil.isNumber(mappingChar) || '.' == mappingChar;
    }

    @Override
    protected boolean isStringCondition(int index, StringBuilder stringBuilder, InnerSensitiveWordContext checkContext) {
        int bufferLen = stringBuilder.length();
        //0.0.0.0
        //255.255.255.255
        if(bufferLen < 7
                || bufferLen > 15) {
            return false;
        }

        // 尽可能减少对象的创建
        String string = stringBuilder.toString();
        List<String> stringList = StringUtil.splitToList(string, '.');
        if(stringList.size() != 4) {
            return false;
        }

        for(String numStr : stringList) {
            int integer = InnerCharUtils.parseInt(numStr);

            if(integer < 0 || integer > 256) {
                return false;
            }
        }

        // 额外处理
        return true;
    }

}
