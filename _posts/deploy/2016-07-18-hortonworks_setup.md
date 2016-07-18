---
layout: post
title: Hortonworks 安装
permalink: /:categories/Hortonworks_setup
date: 2016-07-18 11:30:15 +0800
category: Env
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

[软件列表](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_software_requirements.html)

[JDK](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_jdk_requirements.html)

[数据库](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_database_requirements.html)

[内存](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_memory_requirements.html)

帐号及网络

[hosts&hostname](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_check_dns.html)

[必要SSH](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_set_up_password-less_ssh.html)

[时间一致](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_enable_ntp_on_the_cluster_and_on_the_browser_host.html)

> 时间不一致会导致任务超时等异常

[防火墙](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_configuring_iptables.html)

本地yum安装

[创建使用本地仓库](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/_using_a_local_repository.html)

### Ambari 安装

> ambari 是动态部署的工具,需要MySQL数据库存元信息。[官方文档](http://docs.hortonworks.com/HDPDocuments/Ambari-2.2.2.0/bk_Installing_HDP_AMB/content/ch_Installing_Ambari.html)
