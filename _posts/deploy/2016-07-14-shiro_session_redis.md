---
layout: post
title: Shiro Session 外置Redis
permalink: /:categories/shiro_session_redis
date: 2016-07-14 11:30:15 +0800
category: Deploy
tags: [shiro, session, redis]
---

### 方案

[![arch]({{ site.baseurl }}/img/deploy/RedisShiroSession.png)]({{ site.baseurl }}/img/deploy/RedisShiroSession.png)

#### Redis 存在单点问题时

* 负载均衡：ip_hash；
* HA：内存，Redis。 创建 和 读 Session时, 同步
* 单机验证：两个浏览器，两个端口，在登录，使用，登出时，断开或连接Redis

#### Redis HA保证时

* 负载均衡：轮训，负载均分；
* HA: 依赖Redis HA
* 单机验证：一个浏览器，两个端口，在登录，使用，登出时，断开或连接Redis

### 实现

`RedisSessionDAO` 继承 `AbstractSessionDAO`

需要实现：

`doCreate`

> 1. 获取uuid，作sessionID
> 2. 存入redis

`update`

> 1. 直接存入redis，新的值写入即更新

`doReadSession`

> 1. 通过SessionID从 **内存** 和 **Redis** 读取Session值
> 2. 若Redis没有,内存有，将Redis写入内存的值
> 3. 若Redis有，以Redis的值为准，更新内存里的值

`delete`

> 将Session Timeout（内存和Redis）设为0,以马上超时代替删除

`getActiveSessions`

> 获取所有Session

重要私有：

`saveSession`

> Redis,内存同时存入session,实现热备
