---
layout: post
title: 验证与授权
permalink: /:categories/shiro_session_redis
date: 2016-07-14 11:30:15 +0800
category: 经验
tags: [shiro, session, redis]
---

### 多项目单点登录

> 两种方式。可能两种混合，但最后要回归其中一种

1. 基于Session，多项目需要共享
2. 基于Cookie，加密信息传输，无状态服务器

### Ajax权限控制

> 统一命名规则,可以基于URI验证

补救

> Cookie中加密存储IP，时间等，每次登录验证相关信息。

