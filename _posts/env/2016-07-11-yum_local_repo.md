---
layout: post
title: yum本地仓库
permalink: /:categories/yum_local_repo
date: 2016-07-11 11:30:15 +0800
category: Env
tags: [yum]
---

### 索引

* [hortonworks](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_setting_up_a_local_repository_with_no_internet_access.html)

### 摘要

下载repo内容

> 本地仓库由Internet情况

```bash
cd /var/www/html/
mkdir -p ambari/centos7/
cd ambari/centos7
reposync -r Updates-ambari-2.2.2.0
```

创建仓库

```bash
createrepo <web.server.directory>/ambari/<OS>/Updates-ambari-2.2.2.0
```

打开apache服务器

```bash
systemctl start httpd
```
