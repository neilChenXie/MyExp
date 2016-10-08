---
layout: post
title: Hortonworks启动异常及解决
permalink: /:categories/hortonworks_start_fail/
date: 2016-10-08 17:20:15 +0800
category: Operation
tags: [hortonworks,  env, operation]
---

### Version

[官网](https://docs.hortonworks.com/HDPDocuments/HDP2/HDP-2.4.0/bk_HDP_RelNotes/content/ch_relnotes_v240.html)

### HBase

Region Server 启动不成功

> 服务器时间不统一，通过`ntpdate`校准

### Grafana

原因：Grafana is **running**, but ambari didn't has the right **pid**

> [第二个回答](https://community.hortonworks.com/questions/53493/grafana-failed-to-start-on-hdp242.html)

> Can you check if a Grafana instance is already running on that host, and Ambari is not picking it up?

> `ps aux | grep grafana`

> If it is running, please check if `cat /var/run/ambari-metrics-grafana/grafana-server.pid` matches the process id.

