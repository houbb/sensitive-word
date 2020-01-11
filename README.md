# sensitive-word

[sensitive-word](https://github.com/houbb/sensitive-word) 基于 DFA 算法实现的高性能敏感词工具。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/sensitive-word/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/sensitive-word)

[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/sensitive-word/blob/master/LICENSE.txt)

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

## 变更日志

[CHANGE_LOG.md](https://github.com/houbb/sensitive-word/blob/master/doc/CHANGE_LOG.md)

# 快速开始

## 准备

- JDK1.7+

- Maven 3.x+

## Maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>sensitive-word</artifactId>
    <version>0.0.8</version>
</dependency>
```

## api 概览

`SensitiveWordBs` 作为敏感词的引导类，核心方法如下：

| 方法 | 参数 | 返回值| 说明 |
|:---|:---|:---|:---|
| newInstance() | 无 | 引导类 | 初始化引导类 |
| contains(String) | 待验证的字符串 | 布尔值 | 验证字符串是否包含敏感词 |
| findAll(String) | 待验证的字符串 | 字符串列表 | 返回字符串中所有敏感词 |
| replace(String, char) | 使用指定的 char 替换敏感词 | 字符串 | 返回脱敏后的字符串 |
| replace(String) | 使用 `*` 替换敏感词 | 字符串 | 返回脱敏后的字符串 |

## 使用实例

所有测试案例参见 [SensitiveWordBsTest](https://github.com/houbb/sensitive-word/blob/master/src/test/java/com/github/houbb/sensitive/word/bs/SensitiveWordBsTest.java)

### 判断是否包含敏感词

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

Assert.assertTrue(SensitiveWordBs.newInstance().contains(text));
```

### 返回第一个敏感词

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

String word = SensitiveWordBs.newInstance().findFirst(text);
Assert.assertEquals("五星红旗", word);
```

### 返回所有敏感词

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
Assert.assertEquals("[五星红旗, 毛主席, 天安门]", wordList.toString());
```

### 默认的替换策略

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";
String result = SensitiveWordBs.newInstance().replace(text);
Assert.assertEquals("****迎风飘扬，***的画像屹立在***前。", result);
```

### 指定替换的内容

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";
String result = SensitiveWordBs.newInstance().replace(text, '0');
Assert.assertEquals("0000迎风飘扬，000的画像屹立在000前。", result);
```

# 更多特性

后续的诸多特性，主要是针对各种针对各种情况的处理，尽可能的提升敏感词命中率。

这是一场漫长的攻防之战。

## 忽略大小写

```java
final String text = "fuCK the bad words.";

String word = SensitiveWordBs.newInstance().findFirst(text);
Assert.assertEquals("fuCK", word);
```

## 忽略半角圆角

```java
final String text = "ｆｕｃｋ the bad words.";

String word = SensitiveWordBs.newInstance().findFirst(text);
Assert.assertEquals("ｆｕｃｋ", word);
```

## 忽略数字的写法

这里实现了数字常见形式的转换。

```java
final String text = "这个是我的微信：9⓿二肆⁹₈③⑸⒋➃㈤㊄";

List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
Assert.assertEquals("[9⓿二肆⁹₈③⑸⒋➃㈤㊄]", wordList.toString());
```

## 忽略繁简体

```java
final String text = "我爱我的祖国和五星紅旗。";

List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
Assert.assertEquals("[五星紅旗]", wordList.toString());
```

## 忽略英文的书写格式

```java
final String text = "Ⓕⓤc⒦ the bad words";

List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
Assert.assertEquals("[Ⓕⓤc⒦]", wordList.toString());
```

## 忽略重复词

```java
final String text = "ⒻⒻⒻfⓤuⓤ⒰cⓒ⒦ the bad words";

List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
Assert.assertEquals("[ⒻⒻⒻfⓤuⓤ⒰cⓒ⒦]", wordList.toString());
```

## 邮箱检测

```java
final String text = "楼主好人，邮箱 sensitiveword@xx.com";

List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
Assert.assertEquals("[sensitiveword@xx.com]", wordList.toString());
```

# 用户自定义

## 敏感词和白名单

直接在 resource 目录下新建文件，每一行对应一个敏感词。

`sensitive_word_deny.txt` 代表用户自定义敏感词文件。

`sensitive_word_allow.txt` 代表用户自定义白名单文件。

## 测试

我们在敏感词文件中加入一行，内容为 `自定义敏感词`，同时在白名单文件中加入一行，
内容为 `gender` 作为用户不认为是敏感词的信息。

- 测试代码

```java
final String text = "gender 我们认为应该通过，自定义敏感词我们认为应该拒绝。";

List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
Assert.assertEquals("[自定义敏感词]", wordList.toString());
```

# 后期 road-map

- 停顿词

- 同音字处理

- 形近字处理

- 文字镜像翻转

- 文字降噪处理

- 敏感词标签支持

- 邮箱后缀检测

# 拓展阅读

[敏感词工具实现思路](https://houbb.github.io/2020/01/07/sensitive-word)

[DFA 算法讲解](https://houbb.github.io/2020/01/07/sensitive-word-dfa)

[敏感词库优化流程](https://houbb.github.io/2020/01/07/sensitive-word-slim)

[停止词的思考记录](https://houbb.github.io/2020/01/07/sensitive-word-stopword)