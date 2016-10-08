---
layout: post
title: Sqoop-HDP安装
permalink: /:categories/sqoop_setup/
date: 2016-10-08 15:30:15 +0800
category: Operation
tags: [sqoop, setup, env, operation]
---

### Version

* HDP-2.4.2.0
* Sqoop-1.4.6

### Steps

在Ambari界面选择add service，增加Sqoop。但是，不知为何，这样安装不能直接使用，有几个问题:

### 问题一 JDBC连接

一. 配置中driver填写

  > **jdbc_drivers**: `com.mysql.jdbc.Driver`
  
  > 原因查看 /var/lib/ambari-agent/cache/common-services/SQOOP/1.4.4.2.0/package/scripts/params_linux.py

二. 在$SQOP_HOME下增加`lib`目录，添加mysql-connector-java-5.1.37.jar

### 问题二 需要sqoop-1.4.6.jar

`Could not find or load main class org.apache.sqoop.Sqoop`

* Reason: [需要sqoop.jar](http://blog.csdn.net/seven_zhao/article/details/44199373)

> 下载 https://repo1.maven.org/maven2/org/apache/sqoop/sqoop/1.4.6/

> *-hadoop200 for HDP-2.4.2.0
> 23 for 0.23, 100 for 1.\*, 200 for 2.\*
