---
layout: post
title: FlumeNG Kafka Sink
permalink: /:categories/flume_kafka_sink/
date: 2016-07-27 11:30:15 +0800
category: Tutorial
tags: [flume]
---

### Kafka Sink

```bash
a1.sources = r1
a1.sinks = k1
a1.channels = c1 c2

# Describe/configure the source
a1.sources.r1.type = spooldir
a1.sources.r1.spoolDir = /home/hadoop/tmp
a1.sources.r1.deserializer.maxLineLength = 4096
a1.sources.r1.deserializer.outputCharset = UTF-8

a1.sinks.k1.type= org.apache.flume.sink.kafka.KafkaSink
a1.sinks.k1.brokerList=localhost:9092
a1.sinks.k1.topic=chen


a1.sinks.k2.type = logger

a1.channels.c1.type = memory
a1.channels.c1.capacity = 1000
a1.channels.c1.transactionCapacity = 100

# Bind the source and sink to the channel
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1
a1.sinks.k2.channel = c1
```
