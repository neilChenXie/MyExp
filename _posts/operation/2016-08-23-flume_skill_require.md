---
layout: post
title: Flume 运维知识点
permalink: /:categories/flume_env_setup/
date: 2016-08-23 15:30:15 +0800
category: Operation
tags: [flume, setup, env, operation]
---

### 模块配置

#### Source

* SpoolDir
* Avro

#### Sink

生产

* HDFS
* Kafka

测试&开发

* File Roll
* Logger

### 拓扑配置

* Source Interceptor
* Source Selector
* Sink group
