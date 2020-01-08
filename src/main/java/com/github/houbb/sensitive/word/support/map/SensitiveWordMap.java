package com.github.houbb.sensitive.word.support.map;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.lang.CharUtil;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.heaven.util.util.MapUtil;
import com.github.houbb.sensitive.word.api.IWordMap;
import com.github.houbb.sensitive.word.constant.AppConst;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 敏感词 map
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class SensitiveWordMap implements IWordMap {

    /**
     * 脱敏单词 map
     *
     * @since 0.0.1
     */
    private static Map sensitiveWordMap;

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
    public void initWordMap(Collection<String> collection) {
        // 避免重复加载
        if (MapUtil.isNotEmpty(sensitiveWordMap)) {
            return;
        }

        long startTime = System.currentTimeMillis();
        // 避免扩容带来的消耗
        sensitiveWordMap = new HashMap(collection.size());

        for (String key : collection) {
            if (StringUtil.isEmpty(key)) {
                continue;
            }

            // 用来按照相应的格式保存敏感词库数据
            char[] chars = key.toCharArray();
            final int size = chars.length;

            // 每一个新词的循环，直接将结果设置为当前 map，所有变化都会体现在结果的 map 中
            Map currentMap = sensitiveWordMap;

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
                    Map<String, Boolean> newWordMap = new HashMap<>();
                    newWordMap.put(AppConst.IS_END, false);

                    // 将新的节点放入当前 map 中
                    currentMap.put(charKey, newWordMap);

                    // 将新节点设置为当前节点，方便下一次节点的循环。
                    currentMap = newWordMap;
                }

                // 判断是否为最后一个，添加是否结束的标识。
                if (i == size - 1) {
                    currentMap.put(AppConst.IS_END, true);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Init sensitive word map end! Cost time " + (endTime - startTime) + "ms");
    }

    /**
     * 是否包含
     * （1）直接遍历所有
     * （2）如果遇到，则直接返回 true
     *
     * @param string 字符串
     * @return 是否包含
     * @since 0.0.1
     */
    @Override
    public boolean contains(String string) {
        if (StringUtil.isEmpty(string)) {
            return false;
        }

        for (int i = 0; i < string.length(); i++) {
            int checkResult = checkSensitiveWord(string, i, ValidModeEnum.FAIL_FAST);
            // 快速返回
            if (checkResult > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回所有对应的敏感词
     * （1）结果是有序的
     * （2）结果是默认去重的
     *
     * @param string 原始字符串
     * @return 结果
     * @since 0.0.1
     */
    @Override
    public List<String> findAll(String string) {
        return getSensitiveWords(string, ValidModeEnum.FAIL_OVER);
    }

    @Override
    public String findFirst(String string) {
        List<String> stringList = getSensitiveWords(string, ValidModeEnum.FAIL_FAST);

        if (CollectionUtil.isEmpty(stringList)) {
            return null;
        }

        return stringList.get(0);
    }

    @Override
    public String replace(String target, char replaceChar) {
        if(StringUtil.isEmpty(target)) {
            return target;
        }

        return this.replaceSensitiveWord(target, ValidModeEnum.FAIL_OVER, replaceChar);
    }

    /**
     * 获取敏感词列表
     *
     * @param text     文本
     * @param modeEnum 模式
     * @return 结果列表
     * @since 0.0.1
     */
    private List<String> getSensitiveWords(final String text, final ValidModeEnum modeEnum) {
        //1. 是否存在敏感词，如果比存在，直接返回空列表
        if (StringUtil.isEmpty(text)) {
            return Guavas.newArrayList();
        }

        List<String> resultList = Guavas.newArrayList();
        for (int i = 0; i < text.length(); i++) {
            int wordLength = checkSensitiveWord(text, i, ValidModeEnum.FAIL_OVER);

            // 命中
            if (wordLength > 0) {
                // 保存敏感词
                String sensitiveWord = text.substring(i, i + wordLength);

                // 添加去重
                if (!resultList.contains(sensitiveWord)) {
                    resultList.add(sensitiveWord);
                }

                // 快速返回
                if (ValidModeEnum.FAIL_FAST.equals(modeEnum)) {
                    break;
                }

                // 增加 i 的步长
                // 为什么要-1，因为默认就会自增1
                // TODO: 这里可以根据字符串匹配算法优化。
                i += wordLength - 1;
            }
        }

        return resultList;
    }

    /**
     * 检查敏感词数量
     * <p>
     * （1）如果未命中敏感词，直接返回 0
     * （2）命中敏感词，则返回敏感词的长度。
     *
     * ps: 这里结果进行优化，
     * 1. 是否包含敏感词。
     * 2. 敏感词的长度
     * 3. 正常走过字段的长度（便于后期替换优化，避免不必要的循环重复）
     *
     * @param txt           文本信息
     * @param beginIndex    开始下标
     * @param validModeEnum 验证模式
     * @return 敏感词对应的长度
     * @since 0.0.1
     */
    private int checkSensitiveWord(final String txt, final int beginIndex,
                                   final ValidModeEnum validModeEnum) {
        Map nowMap = sensitiveWordMap;

        // 记录敏感词的长度
        int lengthCount = 0;
        int actualLength = 0;

        for (int i = beginIndex; i < txt.length(); i++) {
            char charKey = txt.charAt(i);
            // 判断该字是否存在于敏感词库中
            // 并且将 nowMap 替换为新的 map，进入下一层的循环。
            nowMap = (Map) nowMap.get(charKey);
            if (ObjectUtil.isNotNull(nowMap)) {
                lengthCount++;

                // 判断是否是敏感词的结尾字，如果是结尾字则判断是否继续检测
                boolean isEnd = (boolean) nowMap.get(AppConst.IS_END);
                if (isEnd) {
                    // 只在匹配到结束的时候才记录长度，避免不完全匹配导致的问题。
                    // eg: 敏感词 敏感词xxx
                    // 如果是 【敏感词x】也会被匹配。
                    actualLength = lengthCount;

                    // 这里确实需要一种验证模式，主要是为了最大匹配从而达到最佳匹配的效果。
                    if (ValidModeEnum.FAIL_FAST.equals(validModeEnum)) {
                        break;
                    }
                }
            } else {
                // 直接跳出循环
                break;
            }
        }

        return actualLength;
    }

    /**
     * 直接替换敏感词，返回替换后的结果
     * @param target           文本信息
     * @param validModeEnum 验证模式
     * @return 脱敏后的字符串
     * @since 0.0.2
     */
    private String replaceSensitiveWord(final String target,
                                        final ValidModeEnum validModeEnum,
                                        final char replaceChar) {
        if(StringUtil.isEmpty(target)) {
            return target;
        }
        // 用于结果构建
        StringBuilder resultBuilder = new StringBuilder(target.length());

        for (int i = 0; i < target.length(); i++) {
            char currentChar = target.charAt(i);
            // 内层直接从 i 开始往后遍历，这个算法的，获取第一个匹配的单词
            int wordLength = checkSensitiveWord(target, i, validModeEnum);

            // 敏感词
            if(wordLength > 0) {
                String replaceStr = CharUtil.repeat(replaceChar, wordLength);
                resultBuilder.append(replaceStr);

                // 直接跳过敏感词的长度
                i += wordLength-1;
            } else {
                // 普通词
                resultBuilder.append(currentChar);
            }
        }

        return resultBuilder.toString();
    }

}
