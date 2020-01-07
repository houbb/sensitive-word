#!/usr/bin/env bash
echo "============================= RELEASE START..."

## 版本号信息(需要手动指定)
version="0.0.1"
newVersion="0.0.2"
projectName="sisyphus"

# release 项目版本
## snapshot 版本号
snapshot_version=${version}"-SNAPSHOT"
## 新的版本号
release_version=${version}

mvn versions:set -DgroupId=com.github.houbb -DartifactId=${projectName} -DoldVersion=${snapshot_version} -DnewVersion=${release_version}
mvn -N versions:update-child-modules
mvn versions:commit
echo "1. RELEASE ${snapshot_version} TO ${release_version} DONE."


# 推送到 github
git add .
git commit -m "release branch ${version}"
git push
git status

echo "2. PUSH TO GITHUB DONE."


# 推送到 maven 中央仓库
mvn clean deploy -P release

echo "3. PUSH TO MAVEN CENTER DONE."

# 合并到 master 分支
branchName="release_"${version} # 分支名称
git checkout master
git pull
git checkout ${branchName}
git rebase master
git checkout master
git merge ${branchName}
git push

echo "4. MERGE TO MASTER DONE."


# 拉取新的分支
newBranchName="release_"${newVersion}
git branch ${newBranchName}
git checkout ${newBranchName}
git push --set-upstream origin ${newBranchName}

echo "5. NEW BRANCH DONE."

# 修改新分支的版本号
## snapshot 版本号
snapshot_new_version=${newVersion}"-SNAPSHOT"
mvn versions:set -DgroupId=com.github.houbb -DartifactId=${projectName} -DoldVersion=${release_version} -DnewVersion=${snapshot_new_version}
mvn -N versions:update-child-modules
mvn versions:commit

git add .
git commit -m "modify branch ${release_version} TO ${snapshot_new_version}"
git push
git status
echo "6. MODIFY ${release_version} TO ${snapshot_new_version} DONE."

echo "============================= RELEASE END..."


# 使用方式：
# 1. 赋值权限： chmod +x ./release.sh
# 2. 执行： ./release.sh
# Last Update Time: 2018-01-20 13:17:06
# Author:   houbb


