# sensitive-word

[sensitive-word](https://github.com/houbb/sensitive-word) 基于 DFA 算法实现的高性能敏感词工具。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/sensitive-word/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/sensitive-word)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/sensitive-word)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/sensitive-word/blob/master/LICENSE.txt)

> [在线体验](https://houbb.github.io/opensource/sensitive-word)

如果有一些疑难杂症，可以加入：[技术交流群](https://mp.weixin.qq.com/s/rkSvXxiiLGjl3S-ZOZCr0Q)

[sensitive-word-admin](https://github.com/houbb/sensitive-word-admin) 是对应的控台的应用，目前功能处于初期开发中，MVP 版本可用。

## 创作目的

实现一款好用敏感词工具。

基于 DFA 算法实现，目前敏感词库内容收录 6W+（源文件 18W+，经过一次删减）。

后期将进行持续优化和补充敏感词库，并进一步提升算法的性能。

希望可以细化敏感词的分类，感觉工作量比较大，暂时没有进行。

## 特性

- 6W+ 词库，且不断优化更新

- 基于 fluent-api 实现，使用优雅简洁

- [基于 DFA 算法，性能为 7W+ QPS，应用无感](https://github.com/houbb/sensitive-word#benchmark)

- [支持敏感词的判断、返回、脱敏等常见操作](https://github.com/houbb/sensitive-word#%E6%A0%B8%E5%BF%83%E6%96%B9%E6%B3%95)

- [支持常见的格式转换](https://github.com/houbb/sensitive-word#%E6%9B%B4%E5%A4%9A%E7%89%B9%E6%80%A7)

全角半角互换、英文大小写互换、数字常见形式的互换、中文繁简体互换、英文常见形式的互换、忽略重复词等

- [支持敏感词检测、邮箱检测、数字检测、网址检测等](https://github.com/houbb/sensitive-word#%E6%9B%B4%E5%A4%9A%E6%A3%80%E6%B5%8B%E7%AD%96%E7%95%A5)

- [支持自定义替换策略](https://github.com/houbb/sensitive-word#%E8%87%AA%E5%AE%9A%E4%B9%89%E6%9B%BF%E6%8D%A2%E7%AD%96%E7%95%A5)

- [支持用户自定义敏感词和白名单](https://github.com/houbb/sensitive-word#%E9%85%8D%E7%BD%AE%E4%BD%BF%E7%94%A8)

- [支持数据的数据动态更新（用户自定义），实时生效](https://github.com/houbb/sensitive-word#%E5%8A%A8%E6%80%81%E5%8A%A0%E8%BD%BD%E7%94%A8%E6%88%B7%E8%87%AA%E5%AE%9A%E4%B9%89)

- [支持敏感词的标签接口](https://github.com/houbb/sensitive-word#%E6%95%8F%E6%84%9F%E8%AF%8D%E6%A0%87%E7%AD%BE)

- [支持跳过一些特殊字符，让匹配更灵活](https://github.com/houbb/sensitive-word#%E5%BF%BD%E7%95%A5%E5%AD%97%E7%AC%A6)

## 变更日志

[CHANGE_LOG.md](https://github.com/houbb/sensitive-word/blob/master/CHANGE_LOG.md)

V0.16.0: 

- [x] 支持内存释放 [#53](https://github.com/houbb/sensitive-word/issues/53)

## 更多资料

### 敏感词控台

有时候敏感词有一个控台，配置起来会更加灵活方便。

> [java 如何实现开箱即用的敏感词控台服务？](https://mp.weixin.qq.com/s/rQo75cfMU_OEbTJa0JGMGg)

### 敏感词标签文件

梳理了大量的敏感词标签文件，可以让我们的敏感词更加方便。

这两个资料阅读可在下方文章获取：

> [v0.11.0-敏感词新特性及对应标签文件](https://mp.weixin.qq.com/s/m40ZnR6YF6WgPrArUSZ_0g)

# 快速开始

## 准备

- JDK1.7+

- Maven 3.x+

## Maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>sensitive-word</artifactId>
    <version>0.16.0</version>
</dependency>
```

## 核心方法

`SensitiveWordHelper` 作为敏感词的工具类，核心方法如下：

| 方法                                     | 参数                       | 返回值    | 说明           |
|:---------------------------------------|:-------------------------|:-------|:-------------|
| contains(String)                       | 待验证的字符串                  | 布尔值    | 验证字符串是否包含敏感词 |
| replace(String, ISensitiveWordReplace) | 使用指定的替换策略替换敏感词           | 字符串    | 返回脱敏后的字符串    |
| replace(String, char)                  | 使用指定的 char 替换敏感词         | 字符串    | 返回脱敏后的字符串    |
| replace(String)                        | 使用 `*` 替换敏感词             | 字符串    | 返回脱敏后的字符串    |
| findAll(String)                        | 待验证的字符串                  | 字符串列表  | 返回字符串中所有敏感词  |
| findFirst(String)                      | 待验证的字符串                  | 字符串    | 返回字符串中第一个敏感词 |
| findAll(String, IWordResultHandler)    | IWordResultHandler 结果处理类 | 字符串列表  | 返回字符串中所有敏感词  |
| findFirst(String, IWordResultHandler)  | IWordResultHandler 结果处理类 | 字符串    | 返回字符串中第一个敏感词 |
| tags(String)       | 获取敏感词的标签                 | 敏感词字符串 | 返回敏感词的标签列表   |



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

WordResultHandlers.raw() 可以保留对应的下标信息、类别信息：

```java
final String text = "骂人：你他妈; 邮箱：123@qq.com; mobile: 13088889999; 网址：https://www.baidu.com";
List<IWordResult> wordList3 = SensitiveWordHelper.findAll(text, WordResultHandlers.raw());
Assert.assertEquals("[WordResult{startIndex=3, endIndex=6, type='WORD'}, WordResult{startIndex=11, endIndex=21, type='EMAIL'}, WordResult{startIndex=31, endIndex=42, type='NUM'}, WordResult{startIndex=55, endIndex=68, type='URL'}]", wordList3.toString());
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
public class MyWordReplace implements IWordReplace {

    @Override
    public void replace(StringBuilder stringBuilder, final char[] rawChars, IWordResult wordResult, IWordContext wordContext) {
        String sensitiveWord = InnerWordCharUtils.getString(rawChars, wordResult);
        // 自定义不同的敏感词替换策略，可以从数据库等地方读取
        if("五星红旗".equals(sensitiveWord)) {
            stringBuilder.append("国家旗帜");
        } else if("毛主席".equals(sensitiveWord)) {
            stringBuilder.append("教员");
        } else {
            // 其他默认使用 * 代替
            int wordLength = wordResult.endIndex() - wordResult.startIndex();
            for(int i = 0; i < wordLength; i++) {
                stringBuilder.append('*');
            }
        }
    }

}
```

我们针对其中的部分词做固定映射处理，其他的默认转换为 `*`。

## IWordResultHandler 结果处理类

IWordResultHandler 可以对敏感词的结果进行处理，允许用户自定义。

内置实现见 `WordResultHandlers` 工具类：

- WordResultHandlers.word()

只保留敏感词单词本身。

- WordResultHandlers.raw()

保留敏感词相关信息，包含敏感词的开始和结束下标。

- WordResultHandlers.wordTags()

同时保留单词，和对应的词标签信息。

### 使用实例

所有测试案例参见 [SensitiveWordHelperTest](https://github.com/houbb/sensitive-word/blob/master/src/test/java/com/github/houbb/sensitive/word/core/SensitiveWordHelperTest.java)

1）基本例子

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

List<String> wordList = SensitiveWordHelper.findAll(text);
Assert.assertEquals("[五星红旗, 毛主席, 天安门]", wordList.toString());
List<String> wordList2 = SensitiveWordHelper.findAll(text, WordResultHandlers.word());
Assert.assertEquals("[五星红旗, 毛主席, 天安门]", wordList2.toString());

List<IWordResult> wordList3 = SensitiveWordHelper.findAll(text, WordResultHandlers.raw());
Assert.assertEquals("[WordResult{startIndex=0, endIndex=4}, WordResult{startIndex=9, endIndex=12}, WordResult{startIndex=18, endIndex=21}]", wordList3.toString());
```

2) wordTags 例子

我们在 `dict_tag_test.txt` 文件中指定对应词的标签信息。

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

// 默认敏感词标签为空
List<WordTagsDto> wordList1 = SensitiveWordHelper.findAll(text, WordResultHandlers.wordTags());
Assert.assertEquals("[WordTagsDto{word='五星红旗', tags=[]}, WordTagsDto{word='毛主席', tags=[]}, WordTagsDto{word='天安门', tags=[]}]", wordList1.toString());

List<WordTagsDto> wordList2 = SensitiveWordBs.newInstance()
        .wordTag(WordTags.file("dict_tag_test.txt"))
        .init()
        .findAll(text, WordResultHandlers.wordTags());
Assert.assertEquals("[WordTagsDto{word='五星红旗', tags=[政治, 国家]}, WordTagsDto{word='毛主席', tags=[政治, 伟人, 国家]}, WordTagsDto{word='天安门', tags=[政治, 国家, 地址]}]", wordList2.toString());
```

# 更多特性

后续的诸多特性，主要是针对各种针对各种情况的处理，尽可能的提升敏感词命中率。

这是一场漫长的攻防之战。

## 样式处理

### 忽略大小写

```java
final String text = "fuCK the bad words.";

String word = SensitiveWordHelper.findFirst(text);
Assert.assertEquals("fuCK", word);
```

### 忽略半角圆角

```java
final String text = "ｆｕｃｋ the bad words.";

String word = SensitiveWordHelper.findFirst(text);
Assert.assertEquals("ｆｕｃｋ", word);
```

### 忽略数字的写法

这里实现了数字常见形式的转换。

```java
final String text = "这个是我的微信：9⓿二肆⁹₈③⑸⒋➃㈤㊄";

List<String> wordList = SensitiveWordHelper.findAll(text);
Assert.assertEquals("[9⓿二肆⁹₈③⑸⒋➃㈤㊄]", wordList.toString());
```

### 忽略繁简体

```java
final String text = "我爱我的祖国和五星紅旗。";

List<String> wordList = SensitiveWordHelper.findAll(text);
Assert.assertEquals("[五星紅旗]", wordList.toString());
```

### 忽略英文的书写格式

```java
final String text = "Ⓕⓤc⒦ the bad words";

List<String> wordList = SensitiveWordHelper.findAll(text);
Assert.assertEquals("[Ⓕⓤc⒦]", wordList.toString());
```

### 忽略重复词

```java
final String text = "ⒻⒻⒻfⓤuⓤ⒰cⓒ⒦ the bad words";

List<String> wordList = SensitiveWordBs.newInstance()
        .ignoreRepeat(true)
        .init()
        .findAll(text);
Assert.assertEquals("[ⒻⒻⒻfⓤuⓤ⒰cⓒ⒦]", wordList.toString());
```

## 更多检测策略

### 邮箱检测

```java
final String text = "楼主好人，邮箱 sensitiveword@xx.com";

List<String> wordList = SensitiveWordHelper.findAll(text);
Assert.assertEquals("[sensitiveword@xx.com]", wordList.toString());
```

### 连续数字检测

一般用于过滤手机号/QQ等广告信息。

V0.2.1 之后，支持通过 `numCheckLen(长度)` 自定义检测的长度。

```java
final String text = "你懂得：12345678";

// 默认检测 8 位
List<String> wordList = SensitiveWordBs.newInstance().init().findAll(text);
Assert.assertEquals("[12345678]", wordList.toString());

// 指定数字的长度，避免误杀
List<String> wordList2 = SensitiveWordBs.newInstance()
        .numCheckLen(9)
        .init()
        .findAll(text);
Assert.assertEquals("[]", wordList2.toString());
```

### 网址检测

用于过滤常见的网址信息。

```java
final String text = "点击链接 www.baidu.com查看答案";

List<String> wordList = SensitiveWordBs.newInstance().init().findAll(text);
Assert.assertEquals("[链接, www.baidu.com]", wordList.toString());

Assert.assertEquals("点击** *************查看答案", SensitiveWordBs
                .newInstance()
                .init()
                .replace(text));
```

# 引导类特性配置

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
        .ignoreRepeat(false)
        .enableNumCheck(true)
        .enableEmailCheck(true)
        .enableUrlCheck(true)
        .enableWordCheck(true)
        .numCheckLen(8)
        .wordTag(WordTags.none())
        .charIgnore(SensitiveWordCharIgnores.defaults())
        .wordResultCondition(WordResultConditions.alwaysTrue())
        .init();

final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";
Assert.assertTrue(wordBs.contains(text));
```
## 配置说明

其中各项配置的说明如下：

| 序号 | 方法                   | 说明                           | 默认值   |
|:---|:---------------------|:-----------------------------|:------|
| 1  | ignoreCase           | 忽略大小写                        | true  |
| 2  | ignoreWidth          | 忽略半角圆角                       | true  |
| 3  | ignoreNumStyle       | 忽略数字的写法                      | true  |
| 4  | ignoreChineseStyle   | 忽略中文的书写格式                    | true  |
| 5  | ignoreEnglishStyle   | 忽略英文的书写格式                    | true  |
| 6  | ignoreRepeat         | 忽略重复词                        | false |
| 7  | enableNumCheck       | 是否启用数字检测。                    | true  |
| 8  | enableEmailCheck     | 是有启用邮箱检测                     | true  |
| 9  | enableUrlCheck       | 是否启用链接检测                     | true  |
| 10 | enableWordCheck      | 是否启用敏感单词检测                   | true  |
| 11 | numCheckLen          | 数字检测，自定义指定长度。                | 8     |
| 12 | wordTag          | 词对应的标签                       | none  |
| 13 | charIgnore          | 忽略的字符                        | none  |
| 14 | wordResultCondition          | 针对匹配的敏感词额外加工，比如可以限制英文单词必须全匹配 | 恒为真   |

## 内存的释放

v0.16.1 开始支持，有时候我们需要释放内存，可以如下：

> [关于内存回收问题](https://github.com/houbb/sensitive-word/issues/53)

```java
SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
                .init();
// 后续因为一些原因移除了对应信息，希望释放内存。
wordBs.destroy();
```

# wordResultCondition-针对匹配词进一步判断

## 说明

支持版本：v0.13.0

有时候我们可能希望对匹配的敏感词进一步限制，比如虽然我们定义了【av】作为敏感词，但是不希望【have】被匹配。

就可以自定义实现 wordResultCondition 接口，实现自己的策略。

系统内置的策略在 `WordResultConditions#alwaysTrue()` 恒为真，`WordResultConditions#englishWordMatch()` 则要求英文必须全词匹配。

## 入门例子

原始的默认情况：

```java
final String text = "I have a nice day。";

List<String> wordList = SensitiveWordBs.newInstance()
        .wordDeny(new IWordDeny() {
            @Override
            public List<String> deny() {
                return Collections.singletonList("av");
            }
        })
        .wordResultCondition(WordResultConditions.alwaysTrue())
        .init()
        .findAll(text);
Assert.assertEquals("[av]", wordList.toString());
```

我们可以指定为英文必须全词匹配。

```java
final String text = "I have a nice day。";

List<String> wordList = SensitiveWordBs.newInstance()
        .wordDeny(new IWordDeny() {
            @Override
            public List<String> deny() {
                return Collections.singletonList("av");
            }
        })
        .wordResultCondition(WordResultConditions.englishWordMatch())
        .init()
        .findAll(text);
Assert.assertEquals("[]", wordList.toString());
```

当然可以根据需要实现更加复杂的策略。

# 忽略字符

## 说明

我们的敏感词一般都是比较连续的，比如【傻帽】

那就有大聪明发现，可以在中间加一些字符，比如【傻!@#$帽】跳过检测，但是骂人等攻击力不减。

那么，如何应对这些类似的场景呢？

我们可以指定特殊字符的跳过集合，忽略掉这些无意义的字符即可。

v0.11.0 开始支持

## 例子

其中 charIgnore 对应的字符策略，用户可以自行灵活定义。

```java
final String text = "傻@冒，狗+东西";

//默认因为有特殊字符分割，无法识别
List<String> wordList = SensitiveWordBs.newInstance().init().findAll(text);
Assert.assertEquals("[]", wordList.toString());

// 指定忽略的字符策略，可自行实现。
List<String> wordList2 = SensitiveWordBs.newInstance()
        .charIgnore(SensitiveWordCharIgnores.specialChars())
        .init()
        .findAll(text);

Assert.assertEquals("[傻@冒, 狗+东西]", wordList2.toString());
```

# 敏感词标签

## 说明

有时候我们希望对敏感词加一个分类标签：比如社情、暴/力等等。

这样后续可以按照标签等进行更多特性操作，比如只处理某一类的标签。

支持版本：v0.10.0

## 入门例子

### 接口

这里只是一个抽象的接口，用户可以自行定义实现。比如从数据库查询等。

```java
public interface IWordTag {

    /**
     * 查询标签列表
     * @param word 脏词
     * @return 结果
     */
    Set<String> getTag(String word);

}
```

### 配置文件

我们可以自定义 dict 标签文件，通过 WordTags.file() 创建一个 WordTag 实现。

- dict_tag_test.txt

```
五星红旗 政治,国家
```

格式如下：

```
敏感词 tag1,tag2
```

### 实现

具体的效果如下，在引导类设置一下即可。

默认的 wordTag 是空的。

```java
String filePath = "dict_tag_test.txt";
IWordTag wordTag = WordTags.file(filePath);

SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
        .wordTag(wordTag)
        .init();

Assert.assertEquals("[政治, 国家]", sensitiveWordBs.tags("五星红旗").toString());;
```

后续会考虑引入一个内置的标签文件策略。

### 敏感词标签文件

梳理了大量的敏感词标签文件，可以让我们的敏感词更加方便。

这两个资料阅读可在下方文章获取：

> [v0.11.0-敏感词新特性及对应标签文件](https://mp.weixin.qq.com/s/m40ZnR6YF6WgPrArUSZ_0g)


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
        .wordDeny(WordDenys.defaults())
        .wordAllow(WordAllows.defaults())
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

IWordDeny wordDeny = WordDenys.chains(WordDenys.defaults(), new MyWordDeny());
IWordAllow wordAllow = WordAllows.chains(WordAllows.defaults(), new MyWordAllow());

SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
        .wordDeny(wordDeny)
        .wordAllow(wordAllow)
        .init();

Assert.assertEquals("[我的自定义敏感词]", wordBs.findAll(text).toString());
```

这里都是同时使用了系统默认配置，和自定义的配置。

注意：**我们初始化了新的 wordBs，那么用新的 wordBs 去判断。而不是用以前的 `SensitiveWordHelper` 工具方法，工具方法配置是默认的！**

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
                .wordAllow(WordAllows.chains(WordAllows.defaults(), myDdWordAllow))
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

# Benchmark

V0.6.0 以后，添加对应的 benchmark 测试。

> [BenchmarkTimesTest](https://github.com/houbb/sensitive-word/blob/master/src/test/java/com/github/houbb/sensitive/word/benchmark/BenchmarkTimesTest.java)

## 环境

测试环境为普通的笔记本:

```
处理器	12th Gen Intel(R) Core(TM) i7-1260P   2.10 GHz
机带 RAM	16.0 GB (15.7 GB 可用)
系统类型	64 位操作系统, 基于 x64 的处理器
```

ps: 不同环境会有差异，但是比例基本稳定。

## 测试效果记录

测试数据：100+ 字符串，循环 10W 次。

| 序号 | 场景               | 耗时 | 备注            |
|:----|:-----------------|:----|:--------------|
| 1 | 只做敏感词，无任何格式转换    | 1470ms，约 7.2W QPS | 追求极致性能，可以这样配置 |
| 2 | 只做敏感词，支持全部格式转换  | 2744ms，约 3.7W QPS | 满足大部分场景       |

# STAR

[![Stargazers over time](https://starchart.cc/houbb/sensitive-word.svg)](https://starchart.cc/houbb/sensitive-word)

# 后期 road-map

- [x] 移除单个汉字的敏感词，在中国，要把词组当做一次词，降低误判率。

- [ ] 支持单个的敏感词变化？

remove、add、edit?

- [x] 敏感词标签接口支持

- [x] 敏感词处理时标签支持

- [x] wordData 的内存占用对比 + 优化

- [x] 用户指定自定义的词组，同时允许指定词组的组合获取，更加灵活

FormatCombine/CheckCombine/AllowDenyCombine 组合策略，允许用户自定义。

- [ ] word check 策略的优化，统一遍历+转换

- [ ] 添加 ThreadLocal 等性能优化

# 拓展阅读

[01-开源敏感词工具入门使用](https://houbb.github.io/2020/01/07/sensitive-word-00-overview)

[02-如何实现一个敏感词工具？违禁词实现思路梳理](https://houbb.github.io/2020/01/07/sensitive-word-01-intro)

[03-敏感词之 StopWord 停止词优化与特殊符号](https://houbb.github.io/2020/01/07/sensitive-word-02-stopword)

[04-敏感词之字典瘦身](https://houbb.github.io/2020/01/07/sensitive-word-03-slim)

[05-敏感词之 DFA 算法(Trie Tree 算法)详解](https://houbb.github.io/2020/01/07/sensitive-word-04-dfa)

[06-敏感词(脏词) 如何忽略无意义的字符？达到更好的过滤效果](https://houbb.github.io/2020/01/07/sensitive-word-05-ignore-char)

[v0.10.0-脏词分类标签初步支持](https://juejin.cn/post/7308782855941292058?searchId=20231209140414C082B3CCF1E7B2316EF9)

[v0.11.0-敏感词新特性：忽略无意义的字符，词标签字典](https://mp.weixin.qq.com/s/m40ZnR6YF6WgPrArUSZ_0g)

[v0.12.0-敏感词/脏词词标签能力进一步增强](https://mp.weixin.qq.com/s/-wa-if7uAy2jWsZC13C0cQ)

![wechat](https://img-blog.csdnimg.cn/63926529df364f09bcb203a8a9016854.png)

# NLP 开源矩阵

[pinyin 汉字转拼音](https://github.com/houbb/pinyin)

[pinyin2hanzi 拼音转汉字](https://github.com/houbb/pinyin2hanzi)

[segment 高性能中文分词](https://github.com/houbb/segment)

[opencc4j 中文繁简体转换](https://github.com/houbb/opencc4j)

[nlp-hanzi-similar 汉字相似度](https://github.com/houbb/nlp-hanzi-similar)

[word-checker 拼写检测](https://github.com/houbb/word-checker)

[sensitive-word 敏感词](https://github.com/houbb/sensitive-word)

