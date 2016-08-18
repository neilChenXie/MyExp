---
layout: post
title: 数据平台-用户画像
permalink: /:categories/plap_user_portrait/
date: 2016-08-04 10:30:15 +0800
category: Architecture
tags: [olap, portrait]
---

### Purpose(目标)

More lightweight OLTP, focus on transaction

make full of collected data, support operation and production department

### Relationship(关系)

[![oltp&olap]({{ site.baseurl }}/img/milestone/OLTP&OLAP.png)]({{ site.baseurl }}/img/milestone/OLTP&OLAP.png)

### Physic（环境）

#### Maintainance(运维)

[![maintain]({{ site.baseurl }}/img/milestone/maintain.png)]({{ site.baseurl }}/img/milestone/maintain.png)

### Logic(业务）

> ETL -> model -> statistics -> analysis -> data mining

[![logic]({{ site.baseurl }}/img/env/logical.png)]({{ site.baseurl }}/img/env/logical.png)

#### ETL

[![etl]({{ site.baseurl }}/img/milestone/ETL.png)]({{ site.baseurl }}/img/milestone/etl.png)

#### Model

建模的前提有：数据存储方式，分析业务特点

[![etl]({{ site.baseurl }}/img/milestone/model_think.png)]({{ site.baseurl }}/img/milestone/model_think.png)

#### Analysis
