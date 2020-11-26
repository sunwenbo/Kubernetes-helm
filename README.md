## helm repo仓库

http://192.168.10.174:30002/harbor/projects/3/helm-charts

## 安装方法

首先安装nfs网络文件共享服务，创建/database，该目录下是存储各组件的数据及日志，具体/database创建的目录,请参考 stella-service目录下的stella-deploy-pv-pvc.yaml文件



## 添加repo仓库


``helm repo add  --username sunwenbo --password Sunwenbo620523 myrepo http://192.168.10.174:30002/chartrepo/helm_repo ``


安装stella组件pv-pvc


```kubectl apply -f stella-deploy-pv-pvc.yaml```


## 一键安装stella及平台组件

```helm install [sever name] [chart name]```

```helm install helm-stella-services  myrepo/helm-stella-services```

## 查看release

```helm list``` 


## 查看pod、service、configmap、pv、pvc

```kubectl get pod ```

```kubectl get service```