---
layout: post
title: Ambari-HDP Yum本地仓库及使用
permalink: /:categories/ambari_hdp_yum_local_repo/
date: 2016-07-20 17:30:15 +0800
category: Deploy
tags: [yum, repo, ambari, hdp]
---

### 索引

* [官方文档](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_using_a_local_repository.html)

所建仓库有两部分:

一、为ambari，hdp，hdp-utils这三个Hortonworks的官方库

二、为本地工具包仓库，用于增加安装时缺少的依赖包。

### 本地建立官方仓库

>以Updates-ambari-2.2.2.0 仓库为例,HDP和HDP-2.4同理

第一步、创建仓库位置

```bash
# 在Apache服务器的文件夹中建立仓库位置

cd /var/www/html/
mkdir -p ambari/centos7/
cd ambari/centos7
```

第二步、创建仓库位置

> 本地仓库有Internet情况

```bash
# 按rpm包下载

reposync -r Updates-ambari-2.2.2.0
```

> 无Internet连接时,

```bash
# 下载tar包，并在仓库中解压

wget http://public-repo-1.hortonworks.com/HDP/centos7/2.x/updates/2.4.2.0/HDP-2.4.2.0-centos7-rpm.tar.gz
```

第三步、创建仓库

```bash
createrepo /var/www/html/ambari/centos7/Updates-ambari-2.2.2.0
```

第四步、打开apache服务器

```bash
systemctl start httpd
```

### 工具仓库

第一、三、四步同上

第二步

> 不管是否处于有网的状态，很多时候我们想支持要用到的rpm包。但是，会有依赖的问题，也就是需要很多依赖的rpm包，手动会死人的...

> 可以利用repotrack工具，在有网的机器中下载所需包及其依赖包

```bash
## 用到工具集，包含 reposync，repotrack，show-installed，yumdownloader
yum install yum-utils
```

最需要的就是`repotrack`，下载包及所有依赖

```bash
# 在仓库中下载指定包及所有依赖
repotrack -a {version} -p {path to save package} {package} 
# -a 默认本机框架x86_64
# -p 默认当前目录
```

### 客户端配置（集群中机器配置）

#### \*.repo

> 模板

```
[my-tool]
name=my-tool
baseurl=http://172.16.9.145/mytool
enabled=1
priority=1
gpgcheck=0 # diable gpgcheck
gpgkey=http://public-repo-1.hortonworks.com/ambari/centos6/RPM-GPG-KEY/RPM-GPG-KEY-Jenkins
```

#### 操作

```bash
# 每次仓库更新后
yum clean all
yum repolist
yum install {package}
```
