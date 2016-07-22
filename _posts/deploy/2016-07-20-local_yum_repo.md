---
layout: post
title: 本地yum仓库 
permalink: /:categories/local_yum_repo
date: 2016-07-18 11:30:15 +0800
category: Deploy
tags: [yum, repo, setup]
---

### 查看仓库列表

`yum repolist`

### 同步远程仓库

> 基于apache服务器

```bash
# 1. 进入apacche服务器文件夹
cd /var/www/html

# 2. 创建一个包位置，名字，层级皆随意
mkdir {name}/{os_version}
cd {name}/{os_version}

# 3. 同步现有仓库（所有rpm包），同步单独包在后面
reposync -r {指定仓库}

# 4. 生效
createrepo {path}/{to}/{package}
```

### 支持单独包


> 不管是否处于有网的状态，很多时候我们想支持要用到的rpm包。但是，会有依赖的问题，也就是需要很多依赖的rpm包，手动会死人的...

#### 工具

```bash
# 下载工具集，包含 reposync，repotrack，show-installed，yumdownloader
yum install yum-utils
```

最需要的就是`repotrack`，下载包及所有依赖

```bash
# 在仓库中下载指定包及所有依赖
repotrack -a {version} -p /var/www/html/{path}/{to}/{repo} {package} 

# 生效
createrepo /var/www/html/{path}/{to}/{package}
```

version:

> default: 现在系统的

* x86_64
* i386
* ...

