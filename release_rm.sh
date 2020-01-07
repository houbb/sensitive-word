#!/usr/bin/env bash
echo "============================= RELEASE START..."

## 版本号信息(需要手动指定)
oldVersion="1.0.0"
newVersion="1.0.0"
projectName="sisyphus"

# 删除分支
oldBranchName="release_"${oldVersion}
git branch -d ${oldBranchName}
git push origin --delete ${oldBranchName}

echo "1. Branch remove success..."

# 拉取新的分支
newBranchName="release_"${newVersion}
git branch ${newBranchName}
git checkout ${newBranchName}
git push --set-upstream origin ${newBranchName}

echo "2. NEW BRANCH DONE."

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
echo "3. MODIFY ${release_version} TO ${snapshot_new_version} DONE."

echo "============================= BRANCH RE-CREATE END..."

echo "============================= BRANCH LIST ============================="
git branch -a

# 使用方式：
# 注意：本脚本用于删除分支，谨慎使用!
# 1. 赋值权限： chmod +x ./release_rm.sh
# 2. 执行： ./release_rm.sh
# Last Update Time: 2018-06-21 11:10:42
# Author:   houbb