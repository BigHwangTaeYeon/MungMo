환경 java 17, mysql 8.3
kafka_2.13-3.7.0
connect : http://packages.confluent.io/archive/7.5/confluent-community-7.5.0.tar.gz
connector : https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-connect-jdbc/versions/10.6.4/confluentinc-kafka-connect-jdbc-10.6.4.zip


### mysql 설정
log_bin -> 활성화
binlog(binary log) -> row-level
    binary log는 데이터의 변경사항을 저장하며 복제, 복구를 목적으로 사용합니다.
    대표적으로 replication 구성 시 binlog가 사용됩니다.
    binary log와 별개로 transaction log와 redo log도 존재합니다.

my.cnf 설정 정리

    [mysqld]
    server-id = 1
    log_bin = mysql-bin
    binlog_row_image = FULL
    
    # Statement-based replication 설정
    # binlog_format=STATEMENT
    
    # 또는 Row-based replication 설정
    binlog_format=ROW
    
    # 또는 Mixed replication 설정
    # binlog_format=MIXED
    
    log_bin = ON



### debezium
흐름
소스 커넥터(Debezium) :소스 데이터베이스에서 변경 사항을 캡처하고 이러한 변경 사항을 Kafka 토픽으로 스트리밍.
카프카 :캡처된 변경 사항을 저장하고 전달.
싱크 커넥터 :Kafka 토픽의 변경 사항을 사용하여 대상 데이터베이스에 기록.


설치(mysql 기준)
    wget https://repo1.maven.org/maven2/io/debezium/debezium-connector-mysql/1.9.6.Final/debezium-connector-mysql-1.9.6.Final-plugin.tar.gz
    tar -xzf debezium-connector-mysql-1.9.6.Final-plugin.tar.gz -C ~/kafka/connectors

***io.debezium.connector.mysql.MySqlConnector class 를 찾지 못하는 문제로인해 share/java/kafka 안에 connect 관련 소스를 모두 넣어줬다.***

Kafka Connect 실행 스크립트 (kafka/connect-distributed.properties)
```properties
    bootstrap.servers=localhost:9092
    group.id=connect-cluster
    key.converter=org.apache.kafka.connect.json.JsonConverter
    value.converter=org.apache.kafka.connect.json.JsonConverter
    key.converter.schemas.enable=true
    value.converter.schemas.enable=true
    offset.storage.topic=connect-offsets
    offset.storage.replication.factor=1
    config.storage.topic=connect-configs
    config.storage.replication.factor=1
    status.storage.topic=connect-status
    status.storage.replication.factor=1
    offset.flush.interval.ms=10000
    plugin.path=/Users/~/kafka_2.13-3.7.0/connect
```

```text
key.converter, value.converter
    데이터를 카프카에 저장할 때 혹은 카프카에서 데이터를 가져올 때 변환하는 데에 사용
    JsonConverter, StringConverter, ByteArrayConverter를 기본으로 제공
    만약 사용하고 싶지 않다면 key.converter.schemas.enable=false or value.converter.schemas.enable=false로 설정
offset.storage.file.filename
    단일 모드 커넥터는 로컬 파일에 오프셋 정보를 저장하며, 이 정보는 소스 커넥터 또는 싱크 커넥터가 데이터 처리 시점을 저장하기 위해 사용
    해당 정보는 다른 사용자나 시스템이 접근하지 않도록 주의해야 함
offset.flush.interval.ms
    태스크가 처리 완료한 오프셋을 커밋하는 주기
plugin.path 
    debezium 압축 해제 위치
```

##### source connect

curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d
```json
{
    "name": "mysql-debezium-connector_oauth_login",
    "config": {
        "connector.class": "io.debezium.connector.mysql.MySqlConnector",
        "database.hostname": "localhost",
        "database.port": "3306",
        "database.user": "mungmo",
        "database.password": "1234",
        "database.server.id": "184054",
        "database.server.name": "mungmoMember_server",
        "database.include.list": "mungmoMember",
        "table.include.list": "mungmoMember.oauth_login",
        "database.history.kafka.bootstrap.servers": "localhost:9092",
        "database.history.kafka.topic": "oauth_login_history",
        "schema.history.internal": "io.debezium.storage.file.history.FileSchemaHistory",
        "schema.history.internal.file.filename": "//Users/bighwang/Documents/workspace/MungMo/kafka_2.13-3.7.0/connect/storage/schemahistory.dat",
        "topic.prefix": "test",
        "include.schema.changes": "true",
        "transforms": "unwrap",
        "transforms.unwrap.type": "io.debezium.transforms.ExtractNewRecordState",
        "transforms.unwrap.drop.tombstones": "false"
    }
}
```
database.server.id: MySQL 복제 서버 ID. 다른 복제 서버와 고유해야 합니다.
database.server.name: Kafka 토픽 이름의 접두사로 사용됩니다.
database.history.kafka.topic: 스키마 변경 내역을 저장할 Kafka 토픽 이름.
transforms 및 transforms.unwrap.type: Debezium의 ExtractNewRecordState 변환을 사용하여 캡처된 데이터에서 새로운 레코드 상태를 추출.

서비스마다 sql 서버를 분리하여 사용할 경우, server.id 의 고유 식별자로 분리시켜야하지만.
    나는 하나의 sql 로 데이터베이스를 분리해서 사용하기에 통일해도 좋다.
대신 server.name 으로 구분이 가능하다.
database.history.kafka.topic 은 자동으로 생성되며, 수동으로도 가능하다.


- Failed to find any class that implements Connector and which name matches io.debezium.connector.mysql.MySqlConnector 에러
io.debezium.connector.mysql.MySqlConnector class 를 찾지 못하는 문제로인해 share/java/kafka 안에 connect 관련 소스를 모두 넣어줬다.

- Error configuring an instance of KafkaSchemaHistory;
"schema.history.internal": "io.debezium.storage.file.history.FileSchemaHistory",
"schema.history.internal.file.filename": "//Users/bighwang/Documents/workspace/MungMo/kafka_2.13-3.7.0/connect/storage/schemahistory.dat",
schema.history.internal.kafka.topic : include.schema.changes 속성 설정에 따른 스키마 변경사항(DDL)을 기록하는 것과 별도로
Debezium connector에서 별도로 스키마 변경 히스토리를 기록하는 토픽을 지정하는 속성 입니다.
참조 : https://debezium.io/documentation/reference/stable/development/engine.html

##### sink connect

http://localhost:8083/connectors POST
```json
{
    "name": "mysql-sink-connector_oauth_login",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSinkConnector",
        "tasks.max": "1",
        "topics": "service_oauth_login",
        "connection.url": "jdbc:mysql://localhost:3306/target_database",
        "connection.user": "mungmo",
        "connection.password": "1234",
        "auto.create": "true",
        "auto.evolve": "true",
        "insert.mode": "insert",
        "table.name.format": "oauth_login",
        "pk.mode": "none",
        "key.converter": "org.apache.kafka.connect.storage.StringConverter",
        "value.converter": "org.apache.kafka.connect.json.JsonConverter",
        "value.converter.schemas.enable": "false"
    }
}
```
auto.create: 테이블이 존재하지 않을 경우 자동으로 생성 (true).
auto.evolve: 스키마 변경 시 테이블을 자동으로 수정 (true).
insert.mode: 데이터 삽입 모드 (insert).
pk.mode: 기본 키 모드 (none).

insert.mode 종류
해당 테이블의 히스토리를 보고싶다 : insert
최신 데이터를 보고싶다 : upsert
ㅁ upsert
    "pk.mode": "record_key",
    "delete.enabled": "true",
    "insert.mode": "upsert",
ㅁ insert
"insert.mode": "insert",

- Access denied; you need (at least one of) the SUPER, REPLICATION CLIENT privilege(s) for this operation
GRANT RELOAD, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO `user`@`localhost`
이 GRANT RELOAD, REPLICATION SLAVE, REPLICATION CLIENT 세가지 권한이 필요했다.
  GRANT REPLICATION SLAVE ON *.* TO 'mungmo'@'localhost'
  SHOW GRANTS FOR 'mungmo'@'localhost';
  FLUSH PRIVILEGES;























