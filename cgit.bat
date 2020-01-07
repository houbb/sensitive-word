:: 用于提交当前变更(windows)
:: author: houbb
:: LastUpdateTime:  2018-11-22 09:08:52
:: 用法：双击运行，或者当前路径 cmd 直接输入 .\cgit.bat

git pull
git add .
git commit -m "[Feature] add for new"
git push
git status

