# 最简单的Flume Interceptor开发

## 功能

在header中加入"chenxie=hello world"

## 运行

```bash
cp ./simple.properties $FLUME_HOME/conf/
cd $FLUME_HOME
bin/flume-ng agent --conf ./conf --conf-file ./conf/simple.properties --name a1 -Dflume.root.logger=DEBUG,console
```

