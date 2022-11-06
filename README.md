# sensitive-word

[sensitive-word](https://github.com/houbb/sensitive-word) 基于 DFA 算法实现的高性能敏感词工具。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/sensitive-word/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/sensitive-word)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/sensitive-word)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/sensitive-word/blob/master/LICENSE.txt)

> [在线体验](https://houbb.github.io/opensource/sensitive-word)

## 创作目的

实现一款好用敏感词工具。

基于 DFA 算法实现，目前敏感词库内容收录 6W+（源文件 18W+，经过一次删减）。

后期将进行持续优化和补充敏感词库，并进一步提升算法的性能。

希望可以细化敏感词的分类，感觉工作量比较大，暂时没有进行。

## 特性

- 6W+ 词库，且不断优化更新

- 基于 DFA 算法，性能较好

- 基于 fluent-api 实现，使用优雅简洁

- 支持敏感词的判断、返回、脱敏等常见操作

- 支持全角半角互换

- 支持英文大小写互换

- 支持数字常见形式的互换

- 支持中文繁简体互换

- 支持英文常见形式的互换

- 支持用户自定义敏感词和白名单

- 支持数据的数据动态更新，实时生效

## 变更日志

[CHANGE_LOG.md](https://github.com/houbb/sensitive-word/blob/master/doc/CHANGE_LOG.md)

v0.2.0 变更：

- 支持用户自定义替换策略

# 快速开始

## 准备

- JDK1.7+

- Maven 3.x+

## Maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>sensitive-word</artifactId>
    <version>0.2.0</version>
</dependency>
```

## api 概览

`SensitiveWordHelper` 作为敏感词的工具类，核心方法如下：

| 方法 | 参数 | 返回值| 说明 |
|:---|:---|:---|:---|
| contains(String) | 待验证的字符串 | 布尔值 | 验证字符串是否包含敏感词 |
| replace(String, ISensitiveWordReplace) | 使用指定的替换策略替换敏感词 | 字符串 | 返回脱敏后的字符串 |
| replace(String, char) | 使用指定的 char 替换敏感词 | 字符串 | 返回脱敏后的字符串 |
| replace(String) | 使用 `*` 替换敏感词 | 字符串 | 返回脱敏后的字符串 |
| findAll(String) | 待验证的字符串 | 字符串列表 | 返回字符串中所有敏感词 |
| findFirst(String) | 待验证的字符串 | 字符串 | 返回字符串中第一个敏感词 |
| findAll(String, IWordResultHandler) | IWordResultHandler 结果处理类 | 字符串列表 | 返回字符串中所有敏感词 |
| findFirst(String, IWordResultHandler) | IWordResultHandler 结果处理类 | 字符串 | 返回字符串中第一个敏感词 |

IWordResultHandler 可以对敏感词的结果进行处理，允许用户自定义。

内置实现见 `WordResultHandlers` 工具类：

- WordResultHandlers.word()

只保留敏感词单词本身。

- WordResultHandlers.raw()

保留敏感词相关信息，包含敏感词，开始和结束下标。

## 使用实例

所有测试案例参见 [SensitiveWordHelperTest](https://github.com/houbb/sensitive-word/blob/master/src/test/java/com/github/houbb/sensitive/word/core/SensitiveWordHelperTest.java)

### 判断是否包含敏感词

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

Assert.assertTrue(SensitiveWordHelper.contains(text));
```

### 返回第一个敏感词

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

String word = SensitiveWordHelper.findFirst(text);
Assert.assertEquals("五星红旗", word);
```

SensitiveWordHelper.findFirst(text) 等价于：

```java
String word = SensitiveWordHelper.findFirst(text, WordResultHandlers.word());
```

WordResultHandlers.raw() 可以保留对应的下标信息：

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

IWordResult word = SensitiveWordHelper.findFirst(text, WordResultHandlers.raw());
Assert.assertEquals("WordResult{word='五星红旗', startIndex=0, endIndex=4}", word.toString());
```

### 返回所有敏感词

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

List<String> wordList = SensitiveWordHelper.findAll(text);
Assert.assertEquals("[五星红旗, 毛主席, 天安门]", wordList.toString());
```

返回所有敏感词用法上类似于 SensitiveWordHelper.findFirst()，同样也支持指定结果处理类。

SensitiveWordHelper.findAll(text) 等价于：

```java
List<String> wordList = SensitiveWordHelper.findAll(text, WordResultHandlers.word());
```

WordResultHandlers.raw() 可以保留对应的下标信息：

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

List<IWordResult> wordList = SensitiveWordHelper.findAll(text, WordResultHandlers.raw());
Assert.assertEquals("[WordResult{word='五星红旗', startIndex=0, endIndex=4}, WordResult{word='毛主席', startIndex=9, endIndex=12}, WordResult{word='天安门', startIndex=18, endIndex=21}]", wordList.toString());
```

### 默认的替换策略

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";
String result = SensitiveWordHelper.replace(text);
Assert.assertEquals("****迎风飘扬，***的画像屹立在***前。", result);
```

### 指定替换的内容

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";
String result = SensitiveWordHelper.replace(text, '0');
Assert.assertEquals("0000迎风飘扬，000的画像屹立在000前。", result);
```

### 自定义替换策略

V0.2.0 支持该特性。

场景说明：有时候我们希望不同的敏感词有不同的替换结果。比如【游戏】替换为【电子竞技】，【失业】替换为【灵活就业】。

诚然，提前使用字符串的正则替换也可以，不过性能一般。

使用例子：

```java
/**
 * 自定替换策略
 * @since 0.2.0
 */
@Test
public void defineReplaceTest() {
    final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

    ISensitiveWordReplace replace = new MySensitiveWordReplace();
    String result = SensitiveWordHelper.replace(text, replace);

    Assert.assertEquals("国家旗帜迎风飘扬，教员的画像屹立在***前。", result);
}
```

其中 `MySensitiveWordReplace` 是我们自定义的替换策略，实现如下：

```java
public class MySensitiveWordReplace implements ISensitiveWordReplace {

    @Override
    public String replace(ISensitiveWordReplaceContext context) {
        String sensitiveWord = context.sensitiveWord();
        // 自定义不同的敏感词替换策略，可以从数据库等地方读取
        if("五星红旗".equals(sensitiveWord)) {
            return "国家旗帜";
        }
        if("毛主席".equals(sensitiveWord)) {
            return "教员";
        }

        // 其他默认使用 * 代替
        int wordLength = context.wordLength();
        return CharUtil.repeat('*', wordLength);
    }

}
```

我们针对其中的部分词做固定映射处理，其他的默认转换为 `*`。

# 更多特性

后续的诸多特性，主要是针对各种针对各种情况的处理，尽可能的提升敏感词命中率。

这是一场漫长的攻防之战。

## 忽略大小写

```java
final String text = "fuCK the bad words.";

String word = SensitiveWordHelper.findFirst(text);
Assert.assertEquals("fuCK", word);
```

## 忽略半角圆角

```java
final String text = "ｆｕｃｋ the bad words.";

String word = SensitiveWordHelper.findFirst(text);
Assert.assertEquals("ｆｕｃｋ", word);
```

## 忽略数字的写法

这里实现了数字常见形式的转换。

```java
final String text = "这个是我的微信：9⓿二肆⁹₈③⑸⒋➃㈤㊄";

List<String> wordList = SensitiveWordHelper.findAll(text);
Assert.assertEquals("[9⓿二肆⁹₈③⑸⒋➃㈤㊄]", wordList.toString());
```

## 忽略繁简体

```java
final String text = "我爱我的祖国和五星紅旗。";

List<String> wordList = SensitiveWordHelper.findAll(text);
Assert.assertEquals("[五星紅旗]", wordList.toString());
```

## 忽略英文的书写格式

```java
final String text = "Ⓕⓤc⒦ the bad words";

List<String> wordList = SensitiveWordHelper.findAll(text);
Assert.assertEquals("[Ⓕⓤc⒦]", wordList.toString());
```

## 忽略重复词

```java
final String text = "ⒻⒻⒻfⓤuⓤ⒰cⓒ⒦ the bad words";

List<String> wordList = SensitiveWordBs.newInstance()
        .ignoreRepeat(true)
        .findAll(text);
Assert.assertEquals("[ⒻⒻⒻfⓤuⓤ⒰cⓒ⒦]", wordList.toString());
```

## 邮箱检测

```java
final String text = "楼主好人，邮箱 sensitiveword@xx.com";

List<String> wordList = SensitiveWordHelper.findAll(text);
Assert.assertEquals("[sensitiveword@xx.com]", wordList.toString());
```

# 特性配置

## 说明

上面的特性默认都是开启的，有时业务需要灵活定义相关的配置特性。

所以 v0.0.14 开放了属性配置。

## 配置方法

为了让使用更加优雅，统一使用 fluent-api 的方式定义。

用户可以使用 `SensitiveWordBs` 进行如下定义：

```java
SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
        .ignoreCase(true)
        .ignoreWidth(true)
        .ignoreNumStyle(true)
        .ignoreChineseStyle(true)
        .ignoreEnglishStyle(true)
        .ignoreRepeat(true)
        .enableNumCheck(true)
        .enableEmailCheck(true)
        .enableUrlCheck(true)
        .init();

final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";
Assert.assertTrue(wordBs.contains(text));
```
## 配置说明

其中各项配置的说明如下：

| 序号 | 方法 | 说明 |
|:---|:---|:---|
| 1 | ignoreCase | 忽略大小写 |
| 2 | ignoreWidth | 忽略半角圆角 |
| 3 | ignoreNumStyle | 忽略数字的写法 |
| 4 | ignoreChineseStyle | 忽略中文的书写格式 |
| 5 | ignoreEnglishStyle | 忽略英文的书写格式 |
| 6 | ignoreRepeat | 忽略重复词 |
| 7 | enableNumCheck | 是否启用数字检测。默认连续 8 位数字认为是敏感词 |
| 8 | enableEmailCheck | 是有启用邮箱检测 |
| 9 | enableUrlCheck | 是否启用链接检测 |

# 动态加载（用户自定义）

## 情景说明

有时候我们希望将敏感词的加载设计成动态的，比如控台修改，然后可以实时生效。

v0.0.13 支持了这种特性。

## 接口说明

为了实现这个特性，并且兼容以前的功能，我们定义了两个接口。

### IWordDeny

接口如下，可以自定义自己的实现。

返回的列表，表示这个词是一个敏感词。

```java
/**
 * 拒绝出现的数据-返回的内容被当做是敏感词
 * @author binbin.hou
 * @since 0.0.13
 */
public interface IWordDeny {

    /**
     * 获取结果
     * @return 结果
     * @since 0.0.13
     */
    List<String> deny();

}
```

比如：

```java
public class MyWordDeny implements IWordDeny {

    @Override
    public List<String> deny() {
        return Arrays.asList("我的自定义敏感词");
    }

}
```

### IWordAllow

接口如下，可以自定义自己的实现。

返回的列表，表示这个词不是一个敏感词。

```java
/**
 * 允许的内容-返回的内容不被当做敏感词
 * @author binbin.hou
 * @since 0.0.13
 */
public interface IWordAllow {

    /**
     * 获取结果
     * @return 结果
     * @since 0.0.13
     */
    List<String> allow();

}
```

如：

```java
public class MyWordAllow implements IWordAllow {

    @Override
    public List<String> allow() {
        return Arrays.asList("五星红旗");
    }

}
```

## 配置使用

**接口自定义之后，当然需要指定才能生效。**

为了让使用更加优雅，我们设计了引导类 `SensitiveWordBs`。

可以通过 wordDeny() 指定敏感词，wordAllow() 指定非敏感词，通过 init() 初始化敏感词字典。

### 系统的默认配置

```java
SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
        .wordDeny(WordDenys.system())
        .wordAllow(WordAllows.system())
        .init();

final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";
Assert.assertTrue(wordBs.contains(text));
```

备注：init() 对于敏感词 DFA 的构建是比较耗时的，一般建议在应用初始化的时候**只初始化一次**。而不是重复初始化！

### 指定自己的实现

我们可以测试一下自定义的实现，如下:

```java
String text = "这是一个测试，我的自定义敏感词。";

SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
        .wordDeny(new MyWordDeny())
        .wordAllow(new MyWordAllow())
        .init();

Assert.assertEquals("[我的自定义敏感词]", wordBs.findAll(text).toString());
```

这里只有 `我的自定义敏感词` 是敏感词，而 `测试` 不是敏感词。

当然，这里是全部使用我们自定义的实现，一般建议使用系统的默认配置+自定义配置。

可以使用下面的方式。

### 同时配置多个

- 多个敏感词

`WordDenys.chains()` 方法，将多个实现合并为同一个 IWordDeny。

- 多个白名单

`WordAllows.chains()` 方法，将多个实现合并为同一个 IWordAllow。

例子：

```java
String text = "这是一个测试。我的自定义敏感词。";

IWordDeny wordDeny = WordDenys.chains(WordDenys.system(), new MyWordDeny());
IWordAllow wordAllow = WordAllows.chains(WordAllows.system(), new MyWordAllow());

SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
        .wordDeny(wordDeny)
        .wordAllow(wordAllow)
        .init();

Assert.assertEquals("[我的自定义敏感词]", wordBs.findAll(text).toString());
```

这里都是同时使用了系统默认配置，和自定义的配置。

# spring 整合

## 背景

实际使用中，比如可以在页面配置修改，然后实时生效。

数据存储在数据库中，下面是一个伪代码的例子，可以参考 [SpringSensitiveWordConfig.java](https://github.com/houbb/sensitive-word/blob/master/src/test/java/com/github/houbb/sensitive/word/spring/SpringSensitiveWordConfig.java)

要求，版本 v0.0.15 及其以上。

## 自定义数据源

简化伪代码如下，数据的源头为数据库。

MyDdWordAllow 和 MyDdWordDeny 是基于数据库为源头的自定义实现类。

```java
@Configuration
public class SpringSensitiveWordConfig {

    @Autowired
    private MyDdWordAllow myDdWordAllow;

    @Autowired
    private MyDdWordDeny myDdWordDeny;

    /**
     * 初始化引导类
     * @return 初始化引导类
     * @since 1.0.0
     */
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.chains(WordAllows.system(), myDdWordAllow))
                .wordDeny(myDdWordDeny)
                // 各种其他配置
                .init();

        return sensitiveWordBs;
    }

}
```

敏感词库的初始化较为耗时，建议程序启动时做一次 init 初始化。

## 动态变更

为了保证敏感词修改可以实时生效且保证接口的尽可能简化，此处没有新增 add/remove 的方法。

而是在调用 `sensitiveWordBs.init()` 的时候，根据 IWordDeny+IWordAllow 重新构建敏感词库。

因为初始化可能耗时较长（秒级别），所有优化为 init 未完成时**不影响旧的词库功能，完成后以新的为准**。

```java
@Component
public class SensitiveWordService {

    @Autowired
    private SensitiveWordBs sensitiveWordBs;

    /**
     * 更新词库
     *
     * 每次数据库的信息发生变化之后，首先调用更新数据库敏感词库的方法。
     * 如果需要生效，则调用这个方法。
     *
     * 说明：重新初始化不影响旧的方法使用。初始化完成后，会以新的为准。
     */
    public void refresh() {
        // 每次数据库的信息发生变化之后，首先调用更新数据库敏感词库的方法，然后调用这个方法。
        sensitiveWordBs.init();
    }

}
```

如上，你可以在数据库词库发生变更时，需要词库生效，主动触发一次初始化 `sensitiveWordBs.init();`。

其他使用保持不变，无需重启应用。


## STAR

[![Stargazers over time](https://starchart.cc/houbb/sensitive-word.svg)](https://starchart.cc/houbb/sensitive-word)

# 后期 road-map

- 同音字处理

- 形近字处理

- 文字镜像翻转

- 文字降噪处理

- 敏感词标签支持

- [ ] DFA 数据结构的另一种实现

# 拓展阅读

[敏感词工具实现思路](https://houbb.github.io/2020/01/07/sensitive-word)

[DFA 算法讲解](https://houbb.github.io/2020/01/07/sensitive-word-dfa)

[敏感词库优化流程](https://houbb.github.io/2020/01/07/sensitive-word-slim)

[java 如何实现开箱即用的敏感词控台服务？](https://mp.weixin.qq.com/s/rQo75cfMU_OEbTJa0JGMGg)

![wechat](https://img-blog.csdnimg.cn/63926529df364f09bcb203a8a9016854.png)

# NLP 开源矩阵

[pinyin 汉字转拼音](https://github.com/houbb/pinyin)

[pinyin2hanzi 拼音转汉字](https://github.com/houbb/pinyin2hanzi)

[segment 高性能中文分词](https://github.com/houbb/segment)

[opencc4j 中文繁简体转换](https://github.com/houbb/opencc4j)

[nlp-hanzi-similar 汉字相似度](https://github.com/houbb/nlp-hanzi-similar)

[word-checker 拼写检测](https://github.com/houbb/word-checker)

[sensitive-word 敏感词](https://github.com/houbb/sensitive-word)

