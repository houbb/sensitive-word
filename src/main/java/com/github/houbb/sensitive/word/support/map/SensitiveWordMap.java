package com.github.houbb.sensitive.word.support.map;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.sensitive.word.api.IWordMap;
import com.github.houbb.sensitive.word.model.WordMapEntry;

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
public class SensitiveWordMap implements IWordMap {


    /**
     * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：
     *
     * <pre>
     * 中 = {
     * isEnd = 0
     * 国 = {
     * isEnd = 1
     * 人 = {isEnd = 0
     * 民 = {isEnd = 1}
     * }
     * 男  = {
     * isEnd = 0
     * 人 = {
     * isEnd = 1
     * }
     * }
     * }
     * }
     *
     * 五 = {
     * isEnd = 0
     * 星 = {
     * isEnd = 0
     * 红 = {
     * isEnd = 0
     * 旗 = {
     * isEnd = 1
     * }
     * }
     * }
     * }
     * </pre>
     *
     * key: 对应的中文
     * value: 是否为结束。
     *
     * 日本人，日本鬼子为例
     *
     * 1、在hashMap中查询“日”看其是否在hashMap中存在，如果不存在，则证明已“日”开头的敏感词还不存在，则我们直接构建这样的一棵树。跳至3。
     * 2、如果在hashMap中查找到了，表明存在以“日”开头的敏感词，设置hashMap = hashMap.get("日")，跳至1，依次匹配“本”、“人”。
     * 3、判断该字是否为该词中的最后一个字。若是表示敏感词结束，设置标志位isEnd = 1，否则设置标志位isEnd = 0；
     *
     * @param collection 敏感词库集合
     * @since 0.0.1
     *
     * 使用对象代码 map 的这种一直递归。
     *
     */
    @Override
    public Map<String, WordMapEntry> getWordMap(Collection<String> collection) {
        Map<String, WordMapEntry> resultMap = new HashMap<>(collection.size());

        for (String key : collection) {
            char[] chars = key.toCharArray();
            final int size = chars.length;

            for (int i = 0; i < size; i++) {
                String charStr = String.valueOf(chars[i]);

                // 直接获取对应的 map
                WordMapEntry wordMapEntry = resultMap.get(charStr);

                // 如果集合存在
                if(ObjectUtil.isNotNull(wordMapEntry)) {

                } else {
//                    // 如果集合不存在，直接新建一个 map
//                    wordMap = new HashMap<>(size);
//                    // 判断是否为最后一个，如果是则设置为1
//                    boolean isEnd = i == size - 1;
//                    // 设置最后的结果
//                    wordMap.put(charStr, isEnd);
                }
            }

        }
        return resultMap;
    }

    public static void main(String[] args) {
        System.out.println("s".toCharArray()[0]+"");
    }

}
