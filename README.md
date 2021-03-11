# ⚠️  DEPRECATED - Stella-Stack Helm Chart

## 创建redis，activemq arangodb organizationInfo loki 数据持久化存储目录

```mkdir -p /opt/data/monitor/loki && mkdir -p /opt/organizationInfo && mkdir -p  /opt/data/activemq && mkdir -p /opt/data/arangodb  && mkdir -p /opt/data/redis && chmod -R 777 /opt/data && chmod -R 777 /opt/data/organizationInfo```

```chmod -R 777 /opt/data/activemq  && chmod -R 777 /opt/data/monitor ```

## 给集群第一台机器打标签

```kubectl label node $MASTER apache=livy-cdh6-krb```


## 部署前环境准备

相关镜像在192.168.10.215 /opt/k8s-install-tool/prometheus-images/ 路径下

kubectl create namespace monitoring

给节点打lable标签，在启动chart时，pod会创建在lable标签的主机上

格式:kubectl label nodes <node-name> monitor=grafana-loki-prometheus

```kubectl label nodes k8s-master1-offline-215 monitor=grafana-loki-prometheus```

在打lable标签的节点创建该目录，用于创建loki日志存储

```mkdir -p /opt/data/monitor/loki && mkdir -p /opt/organizationInfo && mkdir -p  /opt/data/activemq && mkdir -p /opt/data/arangodb  && mkdir -p /opt/data/redis && chmod -R 777 /opt/data && chmod -R 777 /opt/organizationInfo```

## 部署prometheus  grafana  loki

```helm install monitor  monitor-log  -n monitoring```

## 检查

```kubectl get pod -n monitoring```

## helm repo仓库

http://192.168.10.174:30002/harbor/projects/3/helm-charts

## 添加repo仓库

``helm repo add  --username sunwenbo --password Sunwenbo620523 myrepo http://192.168.10.174:30002/chartrepo/helm_repo ``


## 查看release

```helm list``` 


## 查看pod、service、configmap、pv、pvc

```kubectl get pod ```

```kubectl get service```
