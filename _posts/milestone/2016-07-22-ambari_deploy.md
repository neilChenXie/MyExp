---
layout: post
title: Ambari 部署
permalink: /:categories/ambari_setup
date: 2016-07-27 11:30:15 +0800
category: Milestone
tags: [ambari, setup]
---

### 版本

ambari-2.2.2
HDP-2.4.2(for CentOS 7)

### 坑

CentOS系统需要英文版（据说CentOS 6不用，但未验证）

> [oschina帖子](http://www.oschina.net/question/2684511_2159089?fromerr=LweXJhlz)

### 步骤

1. [环境检查]({{ site.baseurl }}/deploy/Hortonworks_setup)
2. [ssh无密码]({{ site.baseurl }}/deploy/passwordless_ssh)
3. [本地repo搭建]({{ site.baseurl }}/deploy/ambari_hdp_yum_local_repo )
4. 按照步骤选择集群机器，安装所需模块。（建议先只安装HDFS和Zookeeper，其它后面增加）

### 注意事项

#### 包依赖

> 两种情况：缺少和冲突。
> 缺少的通过工具仓库补充；
> 冲突的删除已经安装的包（`rpm -e `）

#### 启动Warnning

> 因为Ambari还不够完善，会有一些模块未启动导致warnning。
> 没关系，在控制台一个一个单独启动即可。
