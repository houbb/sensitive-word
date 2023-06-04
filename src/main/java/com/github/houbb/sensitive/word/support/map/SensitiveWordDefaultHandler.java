package com.github.houbb.sensitive.word.support.map;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.FileUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sensitive.word.api.*;
import com.github.houbb.sensitive.word.constant.enums.ValidModeEnum;
import com.github.houbb.sensitive.word.core.NodeTree;
import com.github.houbb.sensitive.word.support.check.SensitiveCheckResult;
import com.github.houbb.sensitive.word.support.check.impl.SensitiveCheckChain;
import com.github.houbb.sensitive.word.support.check.impl.SensitiveCheckUrl;
import com.github.houbb.sensitive.word.support.replace.SensitiveWordReplaceContext;
import com.github.houbb.sensitive.word.support.result.WordResult;

import java.util.Collection;
import java.util.List;

/**
 * 敏感词处理器
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class SensitiveWordDefaultHandler implements IWordHandler {

    /**
     * 脱敏单词
     *
     * @since 0.0.1
     */
    private NodeTree rootNode;

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
    public synchronized void initWord(Collection<String> collection) {
        rootNode = new NodeTree();
        for (String key : collection) {
            if (StringUtil.isEmpty(key)) {
                continue;
            }
            NodeTree tempNode = rootNode;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                NodeTree subNode = tempNode.getSubNode(c);
                if (subNode == null) {
                    // 初始化子节点
                    subNode = new NodeTree();
                    tempNode.addSubNode(c, subNode);
                }
                // 指向子节点,进入下一轮循环
                tempNode = subNode;
                // 设置结束标识
                if (i == key.length() - 1) {
                    tempNode.setKeywordEnd(true);
                }
            }

        }
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
    public boolean contains(String string, final IWordContext context) {
        if (StringUtil.isEmpty(string)) {
            return false;
        }

        for (int i = 0; i < string.length(); i++) {
            SensitiveCheckResult checkResult = sensitiveCheck(string, i, ValidModeEnum.FAIL_FAST, context);
            // 快速返回
            if (checkResult.index() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回所有对应的敏感词
     * （1）结果是有序的
     * （2）为了保留所有的下标，结果从 v0.1.0 之后不再去重。
     *
     * @param string 原始字符串
     * @return 结果
     * @since 0.0.1
     */
    @Override
    public List<IWordResult> findAll(String string, final IWordContext context) {
        return getSensitiveWords(string, ValidModeEnum.FAIL_OVER, context);
    }

    @Override
    public IWordResult findFirst(String string, final IWordContext context) {
        List<IWordResult> stringList = getSensitiveWords(string, ValidModeEnum.FAIL_FAST, context);

        if (CollectionUtil.isEmpty(stringList)) {
            return null;
        }

        return stringList.get(0);
    }

    @Override
    public String replace(String target, final ISensitiveWordReplace replace, final IWordContext context) {
        if(StringUtil.isEmpty(target)) {
            return target;
        }

        return this.replaceSensitiveWord(target, replace, context);
    }

    /**
     * 获取敏感词列表
     *
     * @param text     文本
     * @param modeEnum 模式
     * @return 结果列表
     * @since 0.0.1
     */
    private List<IWordResult> getSensitiveWords(final String text, final ValidModeEnum modeEnum,
                                           final IWordContext context) {
        //1. 是否存在敏感词，如果比存在，直接返回空列表
        if (StringUtil.isEmpty(text)) {
            return Guavas.newArrayList();
        }

        List<IWordResult> resultList = Guavas.newArrayList();
        for (int i = 0; i < text.length(); i++) {
            SensitiveCheckResult checkResult = sensitiveCheck(text, i, ValidModeEnum.FAIL_OVER, context);
            // 命中
            int wordLength = checkResult.index();
            if (wordLength > 0) {
                // 保存敏感词
                String sensitiveWord = text.substring(i, i + wordLength);

                // 添加去重
                WordResult wordResult = WordResult.newInstance()
                        .startIndex(i)
                        .endIndex(i+wordLength)
                        .word(sensitiveWord);
                resultList.add(wordResult);

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
     * 直接替换敏感词，返回替换后的结果
     * @param target           文本信息
     * @param replace 替换策略
     * @param context 上下文
     * @return 脱敏后的字符串
     * @since 0.0.2
     */
    private String replaceSensitiveWord(final String target,
                                        final ISensitiveWordReplace replace,
                                        final IWordContext context) {
        if(StringUtil.isEmpty(target)) {
            return target;
        }
        // 用于结果构建
        StringBuilder resultBuilder = new StringBuilder(target.length());

        for (int i = 0; i < target.length(); i++) {
            char currentChar = target.charAt(i);
            // 内层直接从 i 开始往后遍历，这个算法的，获取第一个匹配的单词
            SensitiveCheckResult checkResult = sensitiveCheck(target, i, ValidModeEnum.FAIL_OVER, context);

            // 敏感词
            int wordLength = checkResult.index();
            if(wordLength > 0) {
                // 是否执行替换
                Class checkClass = checkResult.checkClass();
                String string = target.substring(i, i+wordLength);
                if(SensitiveCheckUrl.class.equals(checkClass)
                    && FileUtil.isImage(string)) {
                    // 直接使用原始内容，避免 markdown 图片转换失败
                    resultBuilder.append(string);
                } else {
                    // 创建上下文
                    ISensitiveWordReplaceContext replaceContext = SensitiveWordReplaceContext.newInstance()
                            .sensitiveWord(string)
                            .wordLength(wordLength);
                    String replaceStr = replace.replace(replaceContext);

                    resultBuilder.append(replaceStr);
                }

                // 直接跳过敏感词的长度
                i += wordLength-1;
            } else {
                // 普通词
                resultBuilder.append(currentChar);
            }
        }

        return resultBuilder.toString();
    }

    @Override
    public SensitiveCheckResult sensitiveCheck(String txt, int beginIndex, ValidModeEnum validModeEnum, IWordContext context) {
        // 默认执行敏感词操作
        context.sensitiveWordInfo(rootNode);

        // 责任链模式调用
        return Instances.singleton(SensitiveCheckChain.class)
                .sensitiveCheck(txt, beginIndex, validModeEnum, context);
    }

}
