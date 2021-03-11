# ⚠️  DEPRECATED - Loki-Stack Helm Chart

## 部署前环境准备

相关镜像在192.168.10.215 /opt/k8s-install-tool/prometheus-images/ 路径下

kubectl create namespace monitoring

给节点打lable标签，在启动chart时，pod会创建在lable标签的主机上

格式:kubectl label nodes <node-name> monitor=grafana-loki-prometheus

kubectl label nodes k8s-master1-offline-215 monitor=grafana-loki-prometheus

在打lable标签的节点创建该目录，用于创建loki日志存储

mkdir -p /opt/data/monitor/loki && mkdir -p /opt/organizationInfo && mkdir -p  /opt/data/activemq && mkdir -p /opt/data/arangodb  && mkdir -p /opt/data/redis && chmod -R 777 /opt/data && chmod -R 777 /opt/organizationInfo

## 部署prometheus  grafana  loki

helm install monitor  monitor-log  -n monitoring

## 检查
kubectl get pod -n monitoring


## 遗留问题
prometheus-alertmanager 、prometheus-server数据持久化存储使用ceph还是挂载到宿主机
