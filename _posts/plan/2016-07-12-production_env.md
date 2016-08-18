---
layout: post
title: 生产环境搭建
permalink: /:categories/production_env/
date: 2016-07-07 11:30:15 +0800
category: Plan
tags: [hortonworks]
---

### 数据平台搭建步骤

1. yum本地仓库
2. 安装Hortonworks自动部署[Official](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/index.html)
3. 验证各模块工作状况(HDFS,HBase,Zookeeper,Kafka,FlumeNG,Hive)
4. FlumeNG interceptor 编写-转换日志信息
5. FlumeNG 将原始日志写入HDFS
6. FlumeNG 写入Kafka
7. Kafka Consumer-结构化日志，写入HBase


### 电商业务平台

> 越快越好

1. Nginx服务器安装FlumeNG Client,制定定时取日志方式
  * JSON 格式的nginx日志
  * FlumeNG 监控文件夹
  * 定时将日志切片，复制到监控的文件夹
2. Shiro Session 外置 Redis
  * Session Timeout与Redis Expire时间分离
  * 登录在Session中写入UID
  * Redis Expire>日志处理间隙（当处理日志是能取回当时信息）
  * Shiro SessionDAO 的Delete不删除Redis中的Session

### 业务准备

1. HiveQL
2. Kafka Consumer
3. HBase API

### 资料

* [kafka->HBase,GitHub](https://github.com/weizhenyi/storm-kafka-Log-Consumer)
* [Hive 入门](http://www.aboutyun.com/forum.php?mod=viewthread&tid=7598&extra=page%3D1)
