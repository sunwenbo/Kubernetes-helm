## 安装部署
* 使用hostpath方式挂载postgres的数据需要在集群的各个节点创建目录
```
mkdir -p /opt/pg_data
```

* 安装postgres Chart

```
helm install postgres .
```

* 初始化数据库密码，需要进入pod容器内手动修改,注意在patroni-0健康的情况下需要进入该pod，如果该pod发生故障，手动执行postgres命令需要进入patroni-1，依次类推(第一次修改成功后就不用修改密码了)。。。

```例如：
kubectl  exec -it postgres-patroni-0 bash
psql -U "postgres"
ALTER USER postgres WITH PASSWORD 'postgres';  #密码为 "postgres"
```

* 卸载postgres

```
helm uninstall  release=$release
```

* 相关资料 Patroni Helm Chart

```
This directory contains a Kubernetes chart to deploy a five node [Patroni](https://github.com/zalando/patroni/) cluster using a [Spilo](https://github.com/zalando/spilo) and a StatefulSet.
```