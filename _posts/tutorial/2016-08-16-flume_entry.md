---
layout: post
title: FlumeNG 入门
permalink: /:categories/flume_entry/
date: 2016-08-16 11:30:15 +0800
category: Tutorial
tags: [flume]
---

### 环境及安装

| Name | Version | Check |
| ---- | ------- | ----- |
| JDK  | >= 1.7  | `java -version` |
| FlumeNG | 1.6.0 | 无 |

#### 安装

```bash
tar -zxvf {flume_pkt}.tar.gz
```

#### 安装测试

```bash
# 进入FlumeNG文件夹

# 拷贝附带的测试配置文件
cp conf/flume-conf.properties.template conf/unitTest.properties

# 在Flume根目录下运行（因为路径都是相对路径）
bin/flume-ng agent --conf conf --conf-file conf/flume-conf.properties --name agent -Dflume.root.logger=INFO,console
```

### 运行脚本及配置

#### 运行脚本

> 基于**安装测试**脚本的解释

```bash
bin/flume-ng agent --conf {flume配置文件夹} --conf-file {自定义配置文件相对路径} --name {自定义配置中Agent的名字} -Dflume.root.logger={日志等级及位置,不写查看conf/log4j.properties配置}
```

#### 常用场景

##### Telnet手动输入

配置文件

```
# Name the components on this agent
a1.sources = r1
a1.sinks = k1
a1.channels = c1

# Describe/configure the source
a1.sources.r1.type = netcat
a1.sources.r1.bind = localhost
a1.sources.r1.port = 44444

# Describe the sink
a1.sinks.k1.type = logger

# Use a channel which buffers events in memory
a1.channels.c1.type = memory
a1.channels.c1.capacity = 1000
a1.channels.c1.transactionCapacity = 100

# Bind the source and sink to the channel
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1
```   

在Flume机器上执行

> 需要telnet

```bash
$ telnet localhost 44444
Trying 127.0.0.1...
Connected to localhost.localdomain (127.0.0.1).
Escape character is '^]'.
Hello world! <ENTER>
OK
```

##### Avro 通信验证

Client（Avro Sink）

```
# Name the components on this agent
a1.sources = r1
a1.sinks = k1
a1.channels = c1

# Describe/configure the source
a1.sources.r1.type = netcat
a1.sources.r1.bind = localhost
a1.sources.r1.port = 44444

# Describe the sink
a1.sinks.k1.type = avro
a1.sinks.k1.hostname = 172.16.2.51
a1.sinks.k1.port = 4141

# Use a channel which buffers events in memory
a1.channels.c1.type = memory
a1.channels.c1.capacity = 1000
a1.channels.c1.transactionCapacity = 100

# Bind the source and sink to the channel
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1
```
Collector

```
# Flume agent config
a1.sources = r1
a1.sinks = k1
a1.channels = c1

# Describe/configure the source
a1.sources = r1
a1.sources.r1.type = avro
a1.sources.r1.bind = 172.16.2.51 # must be the same as avro sink
a1.sources.r1.port = 4141

# Describe the sink
a1.sinks.k1.type = logger

# Use a channel which buffers events in memory
a1.channels.c1.type = memory
a1.channels.c1.capacity = 1000
a1.channels.c1.transactionCapacity = 100

# Bind the source and sink to the channel
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1
```
