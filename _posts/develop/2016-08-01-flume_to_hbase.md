---
layout: post
title: FlumeNG to HBase
permalink: /:categories/flume_to_hbase/
date: 2016-08-01 11:30:15 +0800
category: Develop
tags: [develop, flume, hbase]
---

### Component

* [flume kafka sink](https://flume.apache.org/FlumeUserGuide.html#kafka-sink)

### Start

```bash
# kafka, 1st zookeeper, 2nd kafka
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```
