#!/bin/bash
TIME=`date +%Y-%m-%d/%H:%M:%S`
APPS=(airflow-worker airflow-service visual-query-service  livy-krb-cdh6 odyssey-datasource)

for i in "${APPS[@]}"; do

    INIT_APP=`kubectl  get pod | grep $i | awk '{print $1}'`
    kubectl  exec $INIT_APP -- bash -c "kinit -kt /opt/tw.keytab tw"
    if [ $? -eq 0 ];then
       echo " "
       echo -e "$TIME $INIT_APP init Successful....." >> /opt/stella-k8s/kerberos-init.log 2>&1 &
    else
       echo -e "\n"
       echo -e "$TIME $INIT_APP init Error....." >> /opt/stella-k8s/kerberos-init.log 2>&1 &
    fi

done
