---
layout: post
title: 无密码SSH配置
permalink: /:categories/passwordless_ssh/
date: 2016-07-21 13:30:15 +0800
category: Operation
tags: [ssh, setup]
---

### 意义

部署新机器的时候，Setup无密码的ssh是件繁琐的事，来回`ssh`和`scp`,以下是一个固定步骤。

核心是基于管理机器：
1. 修改hosts
2. 收集id_rsa.pub
3. 修改authorized_keys，
4. 分发新的authorized_keys

### 具体步骤

#### 新机器

一、生成sshkey

```
# 进入root权限, 无论ssh root@{new_ip} 或者 su
cd
ssh-keygen -t rsa

# 修改自己的hostname
vim /etc/hostname
```

#### 管理机器

> 以下，当管理机器已经生成sshkey时, 管理机器生成sshkey在下一个section

```
# enter root user

# modify hosts
vim /etc/hosts

# enter ~/.ssh
cd ~/.ssh

# get new machines' id_rsa.pub
scp {new_host}:.ssh/id_rsa.pub new_host_id

# add to authorized_keys
cat new_host_id >> authorized_keys

# spread new authorized_keys and hosts
scp authorized_keys {all_ip_s}:.ssh/authorized_keys
scp /etc/hsots {all_ip_s}:/etc/hosts
```

#### 管理机器第一次生成SSH

```
su
cd
ssh-keygen -t rsa
cd .ssh
cat id_rsa.pub >> authorized_keys
chmod 600 authorized_keys

# test, no password needed means working
ssh localhost
```
