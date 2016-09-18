---
layout: post
title: Kafka 入门
permalink: /:categories/kafka_entry/
date: 2016-08-16 11:30:15 +0800
category: Tutorial
tags: [entry, kafka]
---

### Version

* Kafka 0.9
* Kafka_2.10 0.9.0.0 (Java API)
* HDP 2.4.0

### Tutorial

* [tutorial point](http://www.tutorialspoint.com/apache_kafka/apache_kafka_cluster_architecture.htm)
* [cnblogs](http://www.cnblogs.com/likehua/p/3999538.html)

### Material

* [about云-资料汇总](http://www.aboutyun.com/forum.php?mod=viewthread&tid=19665&sukey=3997c0719f151520953d6bfb77c7e3a24045e1dcb668b73787740f83eff742e5e55994a5b5d068a03fb816541c4c7b0c)
* [Kafka深度分析](http://www.jasongj.com/2015/01/02/Kafka%E6%B7%B1%E5%BA%A6%E8%A7%A3%E6%9E%90/)

### Command

#### Start

```bash
# start zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties
# start kafka server
bin/kafka-server-start.sh config/server.properties
```

#### Topic

```bash
# create topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

# check topic
bin/kafka-topics.sh --list --zookeeper localhost:2181
```

#### Consumer

```bash
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test --from-beginning
```

### Config

#### server.properties

```
advertised.host.name = 172.16.2.51
advertised.port = 6667
```

在**Zookeeper**中查看

```
# Info will be there
get /brokers/ids/{brokerId}
```

### Problem

#### Metadata error

Two Kafka server(not same cluster) cannot be in same zookeeper cluster

两个Kafka Cluster 不能共享一个zookeeper cluster（利用同一个Zookeeper集群的Kafka必须配置为Kafka集群）， 否则导致**Metadata混乱**

**清除损坏元数据**

[stackoverflow](http://stackoverflow.com/questions/23228222/running-into-leadernotavailableexception-when-using-kafka-0-8-1-with-zookeeper-3)
