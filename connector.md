connector 설정

[source](#source)
[sink](#sink)

### source

```json
{
    "name": "source-mysql-mungmomember_oauth_login",
    "config": {
        "connector.class": "io.debezium.connector.mysql.MySqlConnector",
        "database.hostname": "localhost",
        "database.port": "3306",
        "database.user": "mungmo",
        "database.password": "1234",
        "database.server.id": "1",
        "database.server.name": "mungmomember_oauth_login",
        "database.include.list": "mungmomember",
        "table.include.list": "mungmomember.oauth_login",
        "database.history.kafka.bootstrap.servers": "localhost:9092",
        "database.history.kafka.topic": "oauth_login_history",
        "schema.history.internal": "io.debezium.storage.file.history.FileSchemaHistory",
        "schema.history.internal.file.filename": "/Users/bighwang/Documents/workspace/MungMo/kafka_2.13-3.7.0/connect/storage/schemahistory.dat",
        "topic.prefix": "cdc",
        "include.schema.changes": "true",
        "auto.leader.rebalance.enable": "true",
        "topic.creation.default.replication.factor": 3,
        "topic.creation.default.partitions": 3,

        "transforms": "unwrap",
        "transforms.unwrap.type": "io.debezium.transforms.ExtractNewRecordState",

        "primary.key.mode": "record_key",
        "primary.key.fields": "member_id",

        "snapshot.mode": "initial",
        "key.converter": "io.confluent.connect.avro.AvroConverter",
        "key.converter.schema.registry.url": "http://localhost:8081",
        "value.converter": "io.confluent.connect.avro.AvroConverter",
        "value.converter.schema.registry.url": "http://localhost:8081",
        "key.converter.schemas.enable": "true",
        "value.converter.schemas.enable": "true"
    }
}
```
```json
{
    "name": "source-mysql-mungmoadmin_public_code",
    "config": {
        "connector.class": "io.debezium.connector.mysql.MySqlConnector",
        "database.hostname": "localhost",
        "database.port": "3306",
        "database.user": "mungmo",
        "database.password": "1234",
        "database.server.id": "2",
        "database.server.name": "mungmoadmin_public_code_server",
        "database.include.list": "mungmoadmin",
        "table.include.list": "mungmoadmin.public_code",
        "database.history.kafka.bootstrap.servers": "localhost:9092",
        "database.history.kafka.topic": "public_code_history",
        "schema.history.internal": "io.debezium.storage.file.history.FileSchemaHistory",
        "schema.history.internal.file.filename": "/Users/bighwang/Documents/workspace/MungMo/kafka_2.13-3.7.0/connect/storage/schemahistory.dat",
        "topic.prefix": "cdc",
        "include.schema.changes": "true",
        "auto.leader.rebalance.enable": "true",
        "topic.creation.default.replication.factor": 3,
        "topic.creation.default.partitions": 3,

        "transforms": "unwrap",
        "transforms.unwrap.type": "io.debezium.transforms.ExtractNewRecordState",

        "primary.key.mode": "record_key",
        "primary.key.fields": "public_code_id",

        "snapshot.mode": "initial",
        "key.converter": "io.confluent.connect.avro.AvroConverter",
        "key.converter.schema.registry.url": "http://localhost:8081",
        "value.converter": "io.confluent.connect.avro.AvroConverter",
        "value.converter.schema.registry.url": "http://localhost:8081",
        "key.converter.schemas.enable": "true",
        "value.converter.schemas.enable": "true"
    }
}
```
```json
{
    "name": "source-mysql-mungmoadmin_chat_notification",
    "config": {
        "connector.class": "io.debezium.connector.mysql.MySqlConnector",
        "database.hostname": "localhost",
        "database.port": "3306",
        "database.user": "mungmo",
        "database.password": "1234",
        "database.server.id": "3",
        "database.server.name": "mungmoadmin_chat_notification_server",
        "database.include.list": "mungmoadmin",
        "table.include.list": "mungmoadmin.chat_notification",
        "database.history.kafka.bootstrap.servers": "localhost:9092",
        "database.history.kafka.topic": "chat_notification_history",
        "schema.history.internal": "io.debezium.storage.file.history.FileSchemaHistory",
        "schema.history.internal.file.filename": "/Users/bighwang/Documents/workspace/MungMo/kafka_2.13-3.7.0/connect/storage/schemahistory.dat",
        "topic.prefix": "cdc",
        "include.schema.changes": "true",
        "auto.leader.rebalance.enable": "true",
        "topic.creation.default.replication.factor": 3,
        "topic.creation.default.partitions": 3,

        "transforms": "unwrap",
        "transforms.unwrap.type": "io.debezium.transforms.ExtractNewRecordState",

        "primary.key.mode": "record_key",
        "primary.key.fields": "chat_notification_id",

        "snapshot.mode": "initial",
        "key.converter": "io.confluent.connect.avro.AvroConverter",
        "key.converter.schema.registry.url": "http://localhost:8081",
        "value.converter": "io.confluent.connect.avro.AvroConverter",
        "value.converter.schema.registry.url": "http://localhost:8081",
        "key.converter.schemas.enable": "true",
        "value.converter.schemas.enable": "true"
    }
}
```
```json
{
    "name": "source-mongodb-mungmochat_chat_room_participant",
    "config": {
        // "connector.class": "io.debezium.connector.mysql.MySqlConnector",
        "database.hostname": "localhost",
        "database.port": "3306",
        "database.user": "mungmo",
        "database.password": "1234",
        "database.server.id": "1",
        "database.server.name": "mungmochat_server",
        "database.include.list": "mungmochat",
        "table.include.list": "mungmochat.chat_room_participant",
        "database.history.kafka.bootstrap.servers": "localhost:9092",
        "database.history.kafka.topic": "chat_room_participant_history",
        "schema.history.internal": "io.debezium.storage.file.history.FileSchemaHistory",
        "schema.history.internal.file.filename": "/Users/bighwang/Documents/workspace/MungMo/kafka_2.13-3.7.0/connect/storage/schemahistory.dat",
        "topic.prefix": "cdc",
        "include.schema.changes": "true",
        "auto.leader.rebalance.enable": "true",
        "topic.creation.default.replication.factor": 3,
        "topic.creation.default.partitions": 3,

        "transforms": "unwrap",
        "transforms.unwrap.type": "io.debezium.transforms.ExtractNewRecordState",

        "primary.key.mode": "record_key",
        "primary.key.fields": "id",

        "snapshot.mode": "initial",
        "key.converter": "io.confluent.connect.avro.AvroConverter",
        "key.converter.schema.registry.url": "http://localhost:8081",
        "value.converter": "io.confluent.connect.avro.AvroConverter",
        "value.converter.schema.registry.url": "http://localhost:8081",
        "key.converter.schemas.enable": "true",
        "value.converter.schemas.enable": "true"
    }
}
```


### sink

```json
{
    "name": "sink-mysql-oauth_login-mungmoboard",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSinkConnector",
        "tasks.max": "1",
        "topics": "cdc.mungmomember.oauth_login",
        "connection.url": "jdbc:mysql://localhost:3306/mungmoboard",
        "connection.user": "mungmo",
        "connection.password": "1234",
        "auto.create": "true",
        "auto.evolve": "true",
        "insert.mode": "upsert",
        "pk.mode": "record_key",
        "pk.fields": "member_id",
        "table.name.format": "oauth_login",
        "delete.enabled": "true",
        "key.converter": "io.confluent.connect.avro.AvroConverter",
        "key.converter.schema.registry.url": "http://localhost:8081",
        "key.converter.schemas.enable": "true",
        "value.converter": "io.confluent.connect.avro.AvroConverter",
        "value.converter.schema.registry.url": "http://localhost:8081",
        "value.converter.schemas.enable": "true",

        "transforms": "dropFields,convertCreateDate,convertRecentDate",
        "transforms.dropFields.type": "org.apache.kafka.connect.transforms.ReplaceField$Value",
        "transforms.dropFields.blacklist": "op,ts_ns,transaction,ts_us,before,after,source.ts_ms,source.transaction,source.ts_ns,source",

        "transforms.convertCreateDate.type": "org.apache.kafka.connect.transforms.TimestampConverter$Value",
        "transforms.convertCreateDate.field": "create_date",
        "transforms.convertCreateDate.target.type": "Timestamp",
        "transforms.convertCreateDate.format": "yyyy-MM-dd HH:mm:ss.SSSSSS",
        "transforms.convertCreateDate.unix.precision": "microseconds",

        "transforms.convertRecentDate.type": "org.apache.kafka.connect.transforms.TimestampConverter$Value",
        "transforms.convertRecentDate.field": "recent_date",
        "transforms.convertRecentDate.target.type": "Timestamp",
        "transforms.convertRecentDate.format": "yyyy-MM-dd HH:mm:ss.SSSSSS",
        "transforms.convertRecentDate.unix.precision": "microseconds"
    }
}
```
```json
{
    "name": "sink-mysql-oauth_login-mungmochat",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSinkConnector",
        "tasks.max": "1",
        "topics": "cdc.mungmomember.oauth_login",
        "connection.url": "jdbc:mysql://localhost:3306/mungmochat",
        "connection.user": "mungmo",
        "connection.password": "1234",
        "auto.create": "true",
        "auto.evolve": "true",
        "insert.mode": "upsert",
        "pk.mode": "record_key",
        "pk.fields": "member_id",
        "table.name.format": "oauth_login",
        "delete.enabled": "true",
        "key.converter": "io.confluent.connect.avro.AvroConverter",
        "key.converter.schema.registry.url": "http://localhost:8081",
        "key.converter.schemas.enable": "true",
        "value.converter": "io.confluent.connect.avro.AvroConverter",
        "value.converter.schema.registry.url": "http://localhost:8081",
        "value.converter.schemas.enable": "true",

        "transforms": "dropFields,convertCreateDate,convertRecentDate",
        "transforms.dropFields.type": "org.apache.kafka.connect.transforms.ReplaceField$Value",
        "transforms.dropFields.blacklist": "op,ts_ns,transaction,ts_us,before,after,source.ts_ms,source.transaction,source.ts_ns,source",

        "transforms.convertCreateDate.type": "org.apache.kafka.connect.transforms.TimestampConverter$Value",
        "transforms.convertCreateDate.field": "create_date",
        "transforms.convertCreateDate.target.type": "Timestamp",
        "transforms.convertCreateDate.format": "yyyy-MM-dd HH:mm:ss.SSSSSS",
        "transforms.convertCreateDate.unix.precision": "microseconds",

        "transforms.convertRecentDate.type": "org.apache.kafka.connect.transforms.TimestampConverter$Value",
        "transforms.convertRecentDate.field": "recent_date",
        "transforms.convertRecentDate.target.type": "Timestamp",
        "transforms.convertRecentDate.format": "yyyy-MM-dd HH:mm:ss.SSSSSS",
        "transforms.convertRecentDate.unix.precision": "microseconds"
    }
}
```
```json
{
    "name": "sink-mysql-oauth_login-mungmoadmin",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSinkConnector",
        "tasks.max": "1",
        "topics": "cdc.mungmomember.oauth_login",
        "connection.url": "jdbc:mysql://localhost:3306/mungmoadmin",
        "connection.user": "mungmo",
        "connection.password": "1234",
        "auto.create": "true",
        "auto.evolve": "true",
        "insert.mode": "upsert",
        "pk.mode": "record_key",
        "pk.fields": "member_id",
        "table.name.format": "oauth_login",
        "delete.enabled": "true",
        "key.converter": "io.confluent.connect.avro.AvroConverter",
        "key.converter.schema.registry.url": "http://localhost:8081",
        "key.converter.schemas.enable": "true",
        "value.converter": "io.confluent.connect.avro.AvroConverter",
        "value.converter.schema.registry.url": "http://localhost:8081",
        "value.converter.schemas.enable": "true",

        "transforms": "dropFields,convertCreateDate,convertRecentDate",
        "transforms.dropFields.type": "org.apache.kafka.connect.transforms.ReplaceField$Value",
        "transforms.dropFields.blacklist": "op,ts_ns,transaction,ts_us,before,after,source.ts_ms,source.transaction,source.ts_ns,source",

        "transforms.convertCreateDate.type": "org.apache.kafka.connect.transforms.TimestampConverter$Value",
        "transforms.convertCreateDate.field": "create_date",
        "transforms.convertCreateDate.target.type": "Timestamp",
        "transforms.convertCreateDate.format": "yyyy-MM-dd HH:mm:ss.SSSSSS",
        "transforms.convertCreateDate.unix.precision": "microseconds",

        "transforms.convertRecentDate.type": "org.apache.kafka.connect.transforms.TimestampConverter$Value",
        "transforms.convertRecentDate.field": "recent_date",
        "transforms.convertRecentDate.target.type": "Timestamp",
        "transforms.convertRecentDate.format": "yyyy-MM-dd HH:mm:ss.SSSSSS",
        "transforms.convertRecentDate.unix.precision": "microseconds"
    }
}
```
```json
{
    "name": "sink-mysql-public_code-mungmomember",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSinkConnector",
        "tasks.max": "1",
        "topics": "cdc.mungmoadmin.public_code",
        "connection.url": "jdbc:mysql://localhost:3306/mungmomember",
        "connection.user": "mungmo",
        "connection.password": "1234",
        "auto.create": "true",
        "auto.evolve": "true",
        "insert.mode": "upsert",
        "pk.mode": "record_key",
        "pk.fields": "public_code_id",
        "table.name.format": "public_code",
        "delete.enabled": "true",
        "key.converter": "io.confluent.connect.avro.AvroConverter",
        "key.converter.schema.registry.url": "http://localhost:8081",
        "key.converter.schemas.enable": "true",
        "value.converter": "io.confluent.connect.avro.AvroConverter",
        "value.converter.schema.registry.url": "http://localhost:8081",
        "value.converter.schemas.enable": "true",

        "transforms": "dropFields",
        "transforms.dropFields.type": "org.apache.kafka.connect.transforms.ReplaceField$Value",
        "transforms.dropFields.blacklist": "op,ts_ns,transaction,ts_us,before,after,source.ts_ms,source.transaction,source.ts_ns,source"
    }
}
```
```json
{
    "name": "sink-mysql-chat_notification-mungmochat",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSinkConnector",
        "tasks.max": "1",
        "topics": "cdc.mungmoadmin.chat_notification",
        "connection.url": "jdbc:mysql://localhost:3306/mungmochat",
        "connection.user": "mungmo",
        "connection.password": "1234",
        "auto.create": "true",
        "auto.evolve": "true",
        "insert.mode": "upsert",
        "pk.mode": "record_key",
        "pk.fields": "chat_notification_id",
        "table.name.format": "chat_notification",
        "delete.enabled": "true",
        "key.converter": "io.confluent.connect.avro.AvroConverter",
        "key.converter.schema.registry.url": "http://localhost:8081",
        "key.converter.schemas.enable": "true",
        "value.converter": "io.confluent.connect.avro.AvroConverter",
        "value.converter.schema.registry.url": "http://localhost:8081",
        "value.converter.schemas.enable": "true",

        "transforms": "dropFields",
        "transforms.dropFields.type": "org.apache.kafka.connect.transforms.ReplaceField$Value",
        "transforms.dropFields.blacklist": "op,ts_ns,transaction,ts_us,before,after,source.ts_ms,source.transaction,source.ts_ns,source"
    }
}
```
