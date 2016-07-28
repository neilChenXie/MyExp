---
layout: post
title: Ambari-HDP 环境准备
permalink: /:categories/Hortonworks_setup
date: 2016-07-18 11:30:15 +0800
category: Deploy
tags: [hortonworks, setup]
---

### 版本

| 组件                           | 版本|
| -----------------------------  | --- |
| HDP(Hortonworks Data Platform) | 2.4 |
| Ambari | 2.2.2.0 |

### 索引

[官方文档](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/ch_Getting_Ready.html)


### 环境准备

系统环境

1. [软件列表](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_software_requirements.html)
2. [JDK](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_jdk_requirements.html)
3. [数据库](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_database_requirements.html)
4. [内存](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_memory_requirements.html)

帐号及网络

1. [hosts&hostname](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_check_dns.html)
2. [必要SSH](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_set_up_password-less_ssh.html)
3. [时间一致](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_enable_ntp_on_the_cluster_and_on_the_browser_host.html)

> 时间不一致会导致任务超时等异常

4. [防火墙](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_configuring_iptables.html)

> `systemctl stop firewall` & `systemctl disable firewall` 彻底关闭防火墙，在断电重起后不用再次关闭防火墙。
