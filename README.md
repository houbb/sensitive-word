# sensitive-word

[sensitive-word](https://github.com/houbb/sensitive-word) 基于 DFA 算法实现的敏感词工具。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/sensitive-word/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/sensitive-word)

[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/sensitive-word/blob/master/LICENSE.txt)

## 创作目的

实现一款好用敏感词工具。

基于 DFA 算法实现，目前敏感词库内容收录 18W+ 感觉过于臃肿。

后期将进行相关优化，降低字典的数量。

希望可以细化敏感词的分类，感觉工作量比较大，暂时没有太好的思路。

## 后期目标

- 持续扩容对应的敏感词（如合法的数据抓取）

- 添加英文大小写忽略，全角半角忽略

- 中文添加拼音相关转换，添加繁简体转换忽略

- 允许用户自定义敏感词和白名单

# 快速开始

## 准备

- JDK1.7+

- Maven 3.x+

## Maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>sensitive-word</artifactId>
    <version>0.0.1</version>
</dependency>
```

## 使用实例

所有测试案例参见 [SensitiveWordBsTest]()

### 判断是否包含敏感词

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。。";

Assert.assertTrue(SensitiveWordBs.getInstance().contains(text));
```

### 返回第一个敏感词

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

String word = SensitiveWordBs.getInstance().findFirst(text);
Assert.assertEquals("五星红旗", word);
```

### 返回所有敏感词

```java
final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

List<String> wordList = SensitiveWordBs.getInstance().findAll(text);
Assert.assertEquals("[五星红旗, 毛主席, 天安门]", wordList.toString());
```
