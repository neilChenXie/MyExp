---
layout: post
title: Flume 开发环境 部署
permalink: /:categories/flume_env_setup/
date: 2016-08-18 17:30:15 +0800
category: Operation
tags: [flume, setup, env, operation]
---

### 环境目的

为开发提供测试Flume Interceptor单模块测试的环境。

此环境提供给开发

* 输入操作：
  * 加载测试jar包
  * 加载测试日志文件
* 执行操作：
  * 启动Flume
* 输出文件:
  * 日志文件

### 开发权限

#### **固定名字**Jar包到**指定目录**

> 指定目录**不能**是Flume自己的lib目录

目录修改通过conf/flume-env.sh文件中的FLUME_CLASSPATH变量

目的：

* 对Flume的Jar没有权限，避免损坏运行环境
* 固定名字，**每次**替换，**避免遗留**Jar包影响运行

#### **任意**测试日志到**指定目录**

注意：

* 每次加载前，**清空**整个目录，避免遗留文件影响运行

#### 启停Flume

> 按需测试

### 三个脚本

#### Flume-loadLog.sh

> 将要测试的日志导入已经监控的文件夹

```bash
# 例子
Flume-loadLog.sh nginx.log
```

#### Flume-loadJar.sh

> 将sekorm-flume.jar包放入flume的载入目录

```bash
# 例子
Flume-loadJar.sh
```

#### Flume-start.sh

> 启动Flume

```bash
# 例子
Flume-start.sh
```

### 运维工作

#### 修改sekorm-flume.properties

> 主要是在拓扑变化是，增加interceptor时，或调整运行顺序时。

```properties
# 按照i1->i2顺序执行
a1.sources.r1.interceptors = i1 i2 

# 每一个interceptor对应的类
a1.sources.r1.interceptors.i1.type = com.sekorm.flume.interceptor.TrashFilter$Builder
```

#### 修改log4j.properties

> 在需要增加支持的类，或一个类下增加日志级别时

每一个类下**两个**日志文件，一个INFO级别，一个DEBUG级别。打印到/home/{user}/logs/下面，方便**开发**获取

> * 前者信息用于验证模块功能正确性
> * 后者用于开发信息

例子

```
# Logger (log4j.logger.{类名})
## com.sekorm.flume.interceptor.TrashFilter
log4j.logger.com.sekorm.flume.interceptor.TrashFilter = DEBUG, trashInfo, trashDebug

# Appenders (logger声明的名字：trashInfo, trashDebug)
## Info level
log4j.appender.trashInfo = org.apache.log4j.RollingFileAppender  
log4j.appender.trashInfo.Threshold = INFO
log4j.appender.trashInfo.encoding = UTF-8
log4j.appender.trashInfo.MaxFileSize = 100MB
log4j.appender.trashInfo.MaxBackupIndex = 10
log4j.appender.trashInfo.File = ${user.log.dir}/TrashFilterInfo.log
log4j.appender.trashInfo.layout = org.apache.log4j.PatternLayout
log4j.appender.trashInfo.layout.ConversionPattern = %d{dd MMM yyyy HH:mm:ss,SSS} %-5p [%t] (%C.%M:%L) %x - %m%n

## Debug level
log4j.appender.trashDebug = org.apache.log4j.RollingFileAppender  
log4j.appender.trashInfo.Threshold = DEBUG
log4j.appender.trashDebug.encoding = UTF-8
log4j.appender.trashDebug.MaxFileSize = 100MB
log4j.appender.trashDebug.MaxBackupIndex = 10
log4j.appender.trashDebug.File = ${user.log.dir}/TrashFilterDebug.log
log4j.appender.trashDebug.layout = org.apache.log4j.PatternLayout
log4j.appender.trashInfo.layout.ConversionPattern=%d{dd MMM yyyy HH:mm:ss,SSS} %-5p [%t] (%C.%M:%L) %x - %m%n
```

#### 增加依赖

> 当Interceptor引入新依赖时，需要将新Jar包拷贝到lib/目录下

### 资料索引

#### log4j

> log4j版本时1.2.7

* [Log4j.properties配置详解](http://it.oyksoft.com/log4j/)
* [Log4j不同等级输入不同文件的配置](http://www.tuicool.com/articles/Y7RZvaQ)
