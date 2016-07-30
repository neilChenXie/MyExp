---
layout: post
title: FlumeNG 模块验证
permalink: /:categories/flume_verify/
date: 2016-07-27 11:30:15 +0800
category: Verify
tags: [flume]
---

### 基本功能验证

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

```bash
# monitor

tail -f /var/log/flume/flume_{agentName}.log
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

### Avro 通信验证

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

```bash
# run

bin/flume-ng agent --conf conf --conf-file {path to config_file} --name a1 -Dflume.root.logger=INFO,console
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

```bash
# monitor

tail -f /var/log/flume/flume_{agentName}.log
```
