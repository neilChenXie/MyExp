---
layout: post
title: 日志分类
permalink: /:categories/log_category
date: 2016-06-15 13:20:15 +0800
category: Input
tags: [input, flume, log]
---

### Read

#### bash

```bash
# valid information
cat access_20160613.log | grep 'GET\ \/news\/' | grep -v 'get\|404\|loadMore' | grep 'detail\|html\|share'
# filter everything
cat access_20160613.log | grep 'GET\ \/news\/' | grep -v 'detail\|get\|html\|share\|404\|loadMore'
```

#### info

| info | web | api |
| ---- | --- | --- |
| url | /news/*.html, /doc/*.html | /news/detail, /docs/detail |
| param | | newId, docId |

\*.html=newId,docId

#### problem

/news/share

> `/news/share;jsessionid=9BD9D9FF5B3B02D8355CB4F4C7099DAF?newId=1379`
是否会有*.html请求

### Download

#### bash

```bash
cat access_20160613.log | grep 'GET\ \/doc\/downloadDoc\|GET\ \/wmsApi\/docOuter\/download'
```

#### info

| info  | web | api |
| -----  | --- | --- |
| url | /doc/downloadDoc | /wmsApi/docOuter/download |
| param | docId | key & value & readSource |

#### problem

移动段需要解密

### Favorite

#### info

| info | web | api |
| ---- | --- | --- |
| url | /memFavorite/webCollect | /memberApi/memFavorite/add |

#### problem

POST request

### share

#### info

| info | web | api |
| ---- | --- | --- |
| url | /share/shareLog | /statApi/share/shareLog |

#### problem

POST request
