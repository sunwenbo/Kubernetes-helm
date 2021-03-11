#!/bin/bash
#0 = false , 1 = true 
HOST=$HOSTNAME


check_livy_port() {

    nc -w 1 ${HOST} 9113 < /dev/null
    if [ $? -eq 0 ];then
        echo 'check_livy_port 1' | curl --data-binary @- http://${HOST}:9091/metrics/job/check_livy_port
    else
        echo 'check_livy_port 0' | curl --data-binary @- http://${HOST}:9091/metrics/job/check_livy_port
    fi

}


check_stella_port() {

    nc -w 1 ${HOST} 8888 < /dev/null
    if [ $? -eq 0 ];then
        echo 'check_stella_port 1' | curl --data-binary @- http://${HOST}:9091/metrics/job/check_stella_port
    else
        echo 'check_stella_port 0' | curl --data-binary @- http://${HOST}:9091/metrics/job/check_stella_port
    fi

}

check_vqi_port() {

    nc -w 1 ${HOST} 9999 < /dev/null
    if [ $? -eq 0 ];then
        echo 'check_vqi_port 1' | curl --data-binary @- http://${HOST}:9091/metrics/job/check_vqi_port
    else
        echo 'check_vqi_port 0' | curl --data-binary @- http://${HOST}:9091/metrics/job/check_vqi_port
    fi

}

check_airflow_web_port() {

    nc -w 1 ${HOST} 8085 < /dev/null
    if [ $? -eq 0 ];then
        echo 'check_airflow_web_port 1' | curl --data-binary @- http://${HOST}:9091/metrics/job/check_airflow_web_port
    else
        echo 'check_airflow_web_port 0' | curl --data-binary @- http://${HOST}:9091/metrics/job/check_airflow_web_port
    fi

}

check_livy_port
check_stella_port
check_vqi_port
check_airflow_web_port
