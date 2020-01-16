# 是否为邮箱 check


暂时先使用基本的正则表达式，

==================

网址等等

URL 初期可以不做。

Image-URL 检测，避免替换错误。

如果 image-url 中包含数字，直接替换就会导致问题。

针对不同的信息脱敏，则需要知道对应的检测代码是什么。

jpg
png
jpeg
gif

## 是否脱敏的配置

- 敏感词 √

- url ×

- 数字 √

# 是否为 URL check

可以直接开辟另一道验证方式。

直接 regex+全文检索实现。

# 前提

首先实现 Regex

这里也可以支持 allow_regex/deny_regex

