---
layout: post
title: FlumeNG to HBase
permalink: /:categories/flume_to_hbase/
date: 2016-08-01 11:30:15 +0800
category: Develop
tags: [develop, flume, hbase]
---

### Start

> 启动Zookeeper，Kafka，FlumeNG

```bash
# kafka, 1st zookeeper, 2nd kafka
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties

# flume
flume-ng agent --conf ./conf/ --conf-file=./conf/kafka_sink.properties --name a1 -Dflume.root.logger=INFO,console
```

### Kafka


### FlumeNG


针对用到的Spooling Directory Source，Avro Sink/Source, HDFS Sink，Kafka Sink。

#### Spooling Directory Source

\* [官方文档](https://flume.apache.org/FlumeUserGuide.html#spooling-directory-source)

##### 基本设置

```
a1.sources.src-1.type = spooldir
a1.sources.src-1.spoolDir = /var/log/apache/flumeSpool
```

##### 经验

大文件传输，中间产生*.tmp文件，此文件不断增大，Flume报错

> 通过ignorePattern参数忽略中间文件。

#### Avro Sink

\* [官方文档](https://flume.apache.org/FlumeUserGuide.html#avro-sink)

##### 基本设置

```
a1.sinks.k1.type = avro
a1.sinks.k1.hostname = 172.16.2.51
a1.sinks.k1.port = 4141
```

##### 经验


#### Avro Sink

\* [官方文档](https://flume.apache.org/FlumeUserGuide.html#avro-source)

##### 基本设置

```
a1.sinks.k1.type = avro
a1.sinks.k1.bind = 172.16.2.51
a1.sinks.k1.port = 4141
```

##### 经验

#### HDFS Sink

\* [官方文档](https://flume.apache.org/FlumeUserGuide.html#hdfs-sink)

##### 基本设置

```
a1.sinks.k1.type = hdfs
a1.sinks.k1.hdfs.path = /flume/events/%y-%m-%d/%H%M/%S
```

##### 经验

根据header分别存入不同文件夹

> 利用escape sequences生成动态hdfs.path。(基本配置已经用到）

#### Kafka Sink

\* [官方文档](https://flume.apache.org/FlumeUserGuide.html#kafka-sink)

##### 基本设置

```
a1.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink
a1.sinks.k1.topic = mytopic
a1.sinks.k1.brokerList = localhost:9092
```

##### 经验

#### File Channel

\* [官方文档](https://flume.apache.org/FlumeUserGuide.html#file-channel)

##### 基本设置

```
a1.channels.c1.type = file
a1.channels.c1.checkpointDir = /mnt/flume/checkpoint
a1.channels.c1.dataDirs = /mnt/flume/data
```

##### 经验
