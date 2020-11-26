#!/usr/bin/env python
# coding=utf-8

ACTIVEMQHOST = "activemq"
ACTIVEMQPORT = 61613
ACTIVEMQUSERNAME = "admin"
ACTIVEMQPASSWORD = "admin"
QUEUE_NAME = "/queue"

AIRFLOWSERVERHOST = "airflow-service"
AIRFLOWSERVERPORT = 8080

TERADATASPECIAL_DATATYPE = ["VARCHAR", "CHAR", "BIGINT", "DECIMAL"]
OTHER_TERADATA = ["INTEGER", "TIMESTAMP", "DATE"]
HIVE_DATATYPE = []

DRIVERMAP = {
    "mysql": "com.mysql.cj.jdbc.Driver",
    "oracle": "oracle.jdbc.OracleDriver",
    "teradata": "com.teradata.jdbc.TeraDriver",
}

TARGET_TYPE_NONE = "NONE"
TARGET_TYPE_TRANSFORMATION = "TRANSFORMATION"
TARGET_TYPE_CUSTOM_SCHEMA = "CUSTOM_SCHEMA"
TARGET_TYPE_SQOOP_MIGRATE = "SQOOPMIGRATE"
TARGET_TYPE_INGEST = "INGEST"
TARGET_TYPE_VQI = "VQI"

# livy_server_url
LIVY_SERVER_URL = "http://192.168.10.215:9113"

# SPARK_JAR = "/user/taodongjie/spark-service-1.0-SNAPSHOT-jar-with-dependencies.jar"
SPARK_JAR = "/data/stella/jras/spark-service-0.0.1-SNAPSHOT-jar-with-dependencies.jar"
DEPENDENCY_JARS = ["/data/stella/jras/mysql-connector-java-8.0.18.jar"]

KERBEROS_ENABLED = True
KERBEROS_KEYTAB = "/opt/tw.keytab"
KERBEROS_PRINCIPAL = "tw@CDH6.COM"

TIMEZONE_OFFSET = 8

LIVY_STATE_CONTINUE = ["starting", "running"]
LIVY_STATE_BREAK = ["dead", "killed"]
LIVY_STATE_SUCCESS = ["success"]

AUTH_SUPER_TOKEN = "Bearer eeeeJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkbGFkbWluIiwiZXhwIjoxNjAzMzM1ODU4LCJpYXQiOjE2MDI3MzEwNTh9.klAtZo7AbC6ksShHOT0TubO_6BKT9J4TErdyVRdCd3yCcT88NgBInyvpiua79oTcfP1ydYVtOb8jL2yR5Wpatg"

UNSIGNED_LIST = [
    "INT",
    "BIGINT",
    "TIMESTAMP",
    "DATETIME",
    "BOOLEAN",
    "STRING",
    "DATE",
    "DOUBLE",
    "INTEGER",
]

DATATYPE_MYSQL_TO_HIVE = {
    "MEDIUMINT": "INT",
    "NUMERIC": "DECIMAL",
    "VARCHAR": "STRING",
    "CHAR": "STRING",
    "VARBINARY": "BINARY",
    "DATETIME": "DATE",
    "TEXT": "STRING",
}

# TODO: 有精度的oracle到hive
# NUMBER(3,0) TINYINT
# NUMBER(5,0) SMALLINT
# NUMBER(7,0) INT
# NUMBER(20,0) BIGINT
DATATYPE_ORACLE_TO_HIVE = {
    "BINARY_FLOAT": "FLOAT",
    "BINARY_DOUBLE": "DOUBLE",
    "NUMBER": "DECIMAL",
    "VARCHAR": "STRING",
    "VARCHAR2": "STRING",
    "CHAR": "STRING",
    "NCHAR": "STRING",
    "NVARCHAR3": "NVARCHAR3",
    "CLOB": "STRING",
    "BLOB": "STRING",
    "NCLOB": "STRING",
}

DATATYPE_TERADATA_TO_HIVE = {
    "BYTEINT": "TINYINT",
    "NUMERIC": "DECIMAL",
    "VARCHAR": "STRING",
    "CHARACTER": "CHAR",
    "VARBYTE": "BINARY",
    "BYTE": "BINARY",
    "BLOB": "BINARY",
    "TIMESTAMP": "STRING",
    "DATE": "STRING",
}

URI_CONF_LIST = ["useUnicode", "characterEncoding"]

ORACLE_FETCH_SIZE = 100
