---
layout: post
title: GitLab工作流
permalink: /:categories/gitlab_workflow/
date: 2016-08-04 15:30:15 +0800
category: Develop
tags: [gitlab, workflow]
---

### 整体图

[![gitlab_workflow]({{site.baseurl}}/img/arch/GitLab_Flow.png)]({{site.baseurl}}/img/arch/GitLab_Flow.png)

### 安装

[GitLab图形化软件]({{site.baseurl}}/file/GitLab图形化软件.zip)

步骤

1. git
2. mercurial
3. SourceTree

### 职责工作

#### 一 开发人员

一 设置

`工具->选项->一般->默认用户信息`

> 用户信息涉及到仓库贡献统计等

`工具->选项->git` 勾选 `对追踪分支默认使用衍合代替合并`

> rebase instead of merge。因为分支到master是合并，master到分支是rebase，我们合并由Pull Request取代。

全名：{世强用户名}
电子邮件地址：{世强邮箱}

二 克隆仓库

> 将仓库下载到本地

三 提issue

> 无论修复Bug，新增功能，都需要新建Issue。

四 新建分支

> 分支因Issue而建

分支命名规则：

> 多个编号以`_`分割
> 名字中不要有`#`等特殊符号，会导致GitLab页面出错。

* feature-{issue编号}
* fixbug-{issue编号}

五 本地开发

步骤：

1. 写代码
2. stage all/selected (`git add -A .`)
3. 提交 (`git commit -m "{注释}"`)
4. 查看master状态，如果master由提交，合并master到分支（`git rebase master`)
5. 推送：提交到服务器 (`git push origin {分支名}`)

六 Pull Request

> 开发完毕，通过网站给msater分支发Pull Request

七 Code Review及讨论

> 讨论完毕，Pull Request被接受或者被驳回。

#### 二 开发库管理员

一 审核对开发库master分支发起的Pull Request

二 大版本完成后，同步主库的master（主库master会有bug修复的新增代码），解决冲突，给主库master发起Pull Request。

#### 三 主库管理员

一 审核对主库master分支发起的Pull Request

二 将主库master合并到pre-production分支

三 将主库pre-production分支合并到production分支
