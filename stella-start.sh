#!/bin/bash

NUM1=`docker images | grep odyssey | wc -l`

IMGS=(image.senses-ai.com/odyssey/user-server  image.senses-ai.com/odyssey/bos-plugin  image.senses-ai.com/odyssey/visual-query-service  image.senses-ai.com/odyssey/datasource-service image.senses-ai.com/odyssey/gateway  image.senses-ai.com/odyssey/eureka  image.senses-ai.com/odyssey/airflow-service image.senses-ai.com/nginx image.senses-ai.com/odyssey/airflow-template image.senses-ai.com/odyssey/livy-cdh6-krb image.senses-ai.com/activemq-centos redis img.senses.com/busybox mysql:5.7.31)

stella_install() {

   MASTER=${HOSTNAME}
   kubectl label node $MASTER apache=livy-cdh6-krb
   kubectl apply -f filesystem-cephfs.yaml
   kubectl apply -f storageclass-cephfs.yaml
   kubectl apply -f storageclass-rbd.yaml
   
   kubectl apply -f deploy-ceph-pvc.yaml
   echo "正在创建stella pvc,请稍等......"
   sleep 5
   docker-compose -f ./bos-plugin/docker-compose.yml up -d
   docker-compose -f ./bos-plugin/docker-compose.yml ps
   
   helm install stella-deploy stella-odyssey
   helm install livy-deploy livy-krb-cdh6

}

if [ $NUM1 -eq 0 ];then

   for image_file in `ls /opt/stella-images/*.tar`
       do
         image_name=$(docker load -i $image_file | awk '/Loaded image:/ {print $3}')
         echo $image_name
         docker push $image_name
     done
    
   for img in "${IMGS[@]}"; do
   
       docker push $img
   
   done

    stella_install
else
    stella_install
    
fi

NUM2=`crontab -l | grep kerber | wc -l`
if [ $NUM2 -eq 1 ];then

   echo "The timer task already exists"

else
   cron_job="00 00 * * * /opt/stella-k8s/cron_init_kerberos.sh"
    ( crontab -l | grep -v "$cron_job"; echo "$cron_job" ) | crontab -

fi

