---
layout: post
title: HBase 入门
permalink: /:categories/hbase_entry/
date: 2016-09-18 11:30:15 +0800
category: Tutorial
tags: [entry, hbase]
---

### Version

HBase 1.1.2

HBase-Client 1.2.2 (API)

HDP(Hortonworks Data Platform) 2.4.0

### Config

* [about云-配置说明](http://www.aboutyun.com/thread-7914-1-1.html)

### Material

* [HBase 0.9x to 1.x](http://www.slideshare.net/enissoz/meet-hbase-10)
* [官方API](http://hbase.apache.org/apidocs/index.html)

> API 34页

### Example&Tutorial

* [我的例子](https://github.com/neilChenXie/ChenTest/tree/master/hbase)
* [tutorialspoint](http://www.tutorialspoint.com/hbase/hbase_create_table.htm)
* [about云-上手工作线路指导](http://www.aboutyun.com/thread-8391-1-1.html)

### 专题

#### 连接池

[stackoverflow](http://stackoverflow.com/questions/29482803/whats-the-best-way-to-get-htable-handle-of-hbase)

[Hortonworks](https://community.hortonworks.com/articles/4091/hbase-client-application-best-practices.html)

#### 表设计

* [官方](http://hbase.apache.org/book.html#schema)
* [Introduction to HBase Schema Design]({{ site.baseurl}}/_Doc/Reference/hbase_design.pdf)
* [Intel](http://itpeernetwork.intel.com/discussion-on-designing-hbase-tables/)
* [stackoverflow](http://stackoverflow.com/questions/12908378/hbase-searching-by-part-of-a-key)

### 经验

#### HBase Region Server不能启动

服务器间时间有差值，需要ntp时间同步
