:: 用于 release 当前项目(windows)
:: author: houbb
:: LastUpdateTime:  2018-1-22 09:08:52
:: 用法：双击运行，或者当前路径 cmd 直接输入 release.bat

:: 关闭回显
@echo OFF

ECHO "============================= RELEASE START..."

:: 版本号信息(需要手动指定)
:::: 旧版本名称
SET version=0.24.1
:::: 新版本名称
SET newVersion=0.25.0
:::: 组织名称
SET groupName=com.github.houbb
:::: 项目名称
SET projectName=sensitive-word

:: release 项目版本
:::: snapshot 版本号
SET snapshot_version=%version%"-SNAPSHOT"
:::: 新的版本号
SET release_version=%version%

call mvn versions:set -DgroupId=%groupName% -DartifactId=%projectName% -DoldVersion=%snapshot_version% -DnewVersion=%release_version%
call mvn -N versions:update-child-modules
call mvn versions:commit
call echo "1. RELEASE %snapshot_version% TO %release_version% DONE."


:: 推送到 github
git add .
git commit -m "release branch %version%"
git push
git status

ECHO "2. PUSH TO GITHUB DONE."

:: 推送到 maven 中央仓库
call mvn clean deploy -P release
ECHO "3 PUSH TO MVN CENTER DONE."
