---
layout: post
title: Shiro Session 外置Redis
permalink: /:categories/shiro_session_redis
date: 2016-07-14 11:30:15 +0800
category: Env
tags: [shiro, session, redis]
---

### `RedisSessionDAO`

继承 `AbstractSessionDAO`

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
