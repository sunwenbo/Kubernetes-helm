# cfg-center部署方法
前提条件k8s拥有cephfs文件存储。且storageClassName为rook-cephfs

* 获取到cfg-center-0.1.0.tgz chart包解压
```shell
tar xf cfg-center-0.1.0.tgz 
进入目录
cd cfg-center
创建pvc，默认为2G，可在创建前修改合适的pv大小值
kubectl apply -f cfg-center-pvc.yaml 
```

* 安装cfg-center，默认使用宿主机2120端口

```shell
helm install -f values.yaml cfg-center .
```

# cfg-center使用
地址：http://192.168.10.216:2120/conf
登陆集群服务器可以与k8s apiserver进行交互的
```shell
kubectl  get pod | grep cfg

将yaml文件或者目录拷贝到pod的/root/conf_data/ 下即可
kubectl  cp Chart.yaml cfg-center-f745ff96c-zpsng:/root/conf_data/
```
