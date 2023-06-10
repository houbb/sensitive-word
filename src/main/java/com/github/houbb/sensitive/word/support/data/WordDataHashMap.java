package com.github.houbb.sensitive.word.support.data;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.sensitive.word.api.IWordContext;
import com.github.houbb.sensitive.word.api.context.InnerSensitiveWordContext;
import com.github.houbb.sensitive.word.constant.WordConst;
import com.github.houbb.sensitive.word.constant.enums.WordContainsTypeEnum;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 敏感词 map
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class WordDataHashMap extends AbstractWordData {

    /**
     * 脱敏单词 map
     *
     * @since 0.0.1
     */
    private Map innerWordMap;

    /**
     * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：
     *
     * @param collection 敏感词库集合
     * @since 0.0.1
     * <p>
     * 使用对象代码 map 的这种一直递归。
     * 参考资料：https://www.cnblogs.com/AlanLee/p/5329555.html
     * https://blog.csdn.net/chenssy/article/details/26961957
     */
    @Override
    @SuppressWarnings("unchecked")
    public synchronized void doInitWordData(Collection<String> collection) {
        // 避免扩容带来的消耗
        Map newInnerWordMap = new HashMap(collection.size());

        for (String key : collection) {
            if (StringUtil.isEmpty(key)) {
                continue;
            }

            // 用来按照相应的格式保存敏感词库数据
            char[] chars = key.toCharArray();
            final int size = chars.length;

            // 每一个新词的循环，直接将结果设置为当前 map，所有变化都会体现在结果的 map 中
            Map currentMap = newInnerWordMap;

            for (int i = 0; i < size; i++) {
                // 截取敏感词当中的字，在敏感词库中字为HashMap对象的Key键值
                char charKey = chars[i];
                // 如果集合存在
                Object wordMap = currentMap.get(charKey);

                // 如果集合存在
                if (ObjectUtil.isNotNull(wordMap)) {
                    // 直接将获取到的 map 当前当前 map 进行继续的操作
                    currentMap = (Map) wordMap;
                } else {
                    //不存在则，则构建一个新的map，同时将isEnd设置为0，因为他不是最后一
                    Map<String, Boolean> newWordMap = new HashMap<>(8);
                    newWordMap.put(WordConst.IS_END, false);

                    // 将新的节点放入当前 map 中
                    currentMap.put(charKey, newWordMap);

                    // 将新节点设置为当前节点，方便下一次节点的循环。
                    currentMap = newWordMap;
                }
            }

            // 判断是否为最后一个，添加是否结束的标识。
            currentMap.put(WordConst.IS_END, true);
        }

        // 最后更新为新的 map，保证更新过程中旧的数据可用
        this.innerWordMap = newInnerWordMap;
    }

    /**
     * 是否包含
     * （1）直接遍历所有
     * （2）如果遇到，则直接返回 true
     *
     * @param stringBuilder 字符串
     * @param innerContext 内部上下文
     * @return 是否包含
     * @since 0.0.1
     */
    @Override
    public WordContainsTypeEnum doContains(final StringBuilder stringBuilder,
                                         final InnerSensitiveWordContext innerContext) {
        return innerContainsSensitive(stringBuilder, innerContext);
    }

    private WordContainsTypeEnum innerContainsSensitive(StringBuilder stringBuilder,
                                                        final InnerSensitiveWordContext innerContext) {
        // 初始化为当前的 map
        Map nowMap = this.innerWordMap;

        // 记录敏感词的长度
        final int len = stringBuilder.length();
        for (int i = 0; i < len; i++) {
            // 获取当前的 map 信息
            nowMap = getNowMap(nowMap, i, stringBuilder, innerContext);

            // 如果不为空，则判断是否为结尾。
            if (ObjectUtil.isNull(nowMap)) {
                return WordContainsTypeEnum.NOT_FOUND;
            }
        }

        // 是否为结尾，便于快速失败
        boolean isEnd =  isEnd(nowMap);
        if(isEnd) {
            return WordContainsTypeEnum.CONTAINS_END;
        }

        return WordContainsTypeEnum.CONTAINS_PREFIX;
    }

    /**
     * 判断是否结束
     * BUG-FIX: 避免出现敏感词库中没有的文字。
     * @param map map 信息
     * @return 是否结束
     * @since 0.0.9
     */
    private static boolean isEnd(final Map map) {
        if(ObjectUtil.isNull(map)) {
            return false;
        }

        Object value = map.get(WordConst.IS_END);
        if(ObjectUtil.isNull(value)) {
            return false;
        }

        return (boolean)value;
    }
    /**
     * 获取当前的 Map
     * @param nowMap 原始的当前 map
     * @param index 下标
     * @param stringBuilder 文本缓存
     * @param sensitiveContext 上下文
     * @return 实际的当前 map
     * @since 0.0.7
     */
    private Map getNowMap(Map nowMap,
                          final int index,
                          final StringBuilder stringBuilder,
                          final InnerSensitiveWordContext sensitiveContext) {
        final IWordContext context = sensitiveContext.wordContext();

        // 这里的 char 已经是统一格式化之后的，所以可以不用再次格式化。
        char mappingChar = stringBuilder.charAt(index);

        // 这里做一次重复词的处理
        //TODO: 这里可以优化，是否获取一次。
        Map currentMap = (Map) nowMap.get(mappingChar);
        // 启用忽略重复&当前下标不是第一个
        if(context.ignoreRepeat()
                && index > 0) {
            char preMappingChar = stringBuilder.charAt(index-1);

            // 直接赋值为上一个 map
            if(preMappingChar == mappingChar) {
                currentMap = nowMap;
            }
        }

        return currentMap;
    }

}
