# MungMo

기능
로그인 (로그인, 내정보, 회원 사진 클릭, 친구, 신고)
    - 최초 로그인 시, 성향 동네인증 필수
    - 내정보
        1. 소셜
            정보에 대해 비공개 처리 가능
            - 내정보 : 나이, 성별, 닉네임
            - 강아지 : 입마개, 짖음, 마운팅, 소 중 대형견(강아지 종)
        2. 주인 성향
            내향적 적극적 ...
        3. 강아지 성향
            내향적 적극적 ...
        4. 동네 인증
        5. 매너 온도

회원 사진 클릭 -> 정보
    0. 매너 온도
    1. 공개 처리 한 내정보
    2. 강아지 정보
    3. 주인 강아지 성향
    4. 친구 하기
    5. 신고 하기

친구기능
    - 페이지 따로
    - 친구들 리스트
    - 삭제
신고기능
    - 페이지 따로
    - 닉네임 기준
    - 신고 종류 셀렉트박스, 내용 텍스트, 증거(사진, 동영상)

게시판(주제, 인기글, 유머, 강아지자랑, 번개후기)
    - 제목
    - 내용
    - 사진
    - 동영상
    - 댓글

번개 미팅 ( 캘린더 식으로 되어있으면 사용자 입장에서 더 편하지 않을까..? 내가 원하는 날짜를 한 눈에 확인 가능하니 )
    필터링 : 시간, 장소, 인원
    전 : 시간, 장소, 참여인원/모집인원, 참여 회원들 사진(클릭하면 정보)
    후 : 번개 후기 작성(미작성 가능, 참여 회원 리스트{해당 회원에 대한 후기})

소모임(개설 시, 정보 입력만)
    - 정보(제목, 텍스트, 모임 일정{날짜 시간 장소})
    - 게시판(공지{관리자, 매니저}, 자유{모두})
    - 사진첩{모두}
    - 채팅{모두}
        게시판 사진첩 등 등록자 이외 관리자, 매니저 삭제 권한

    소모임 관리자
        - 권한 관리
        - 모임 일정 관리
            - 생성(시간, 장소, 참여인원/모집인원)
            - 수정
            - 삭제
        - 일반 회원 퇴출(재가입 가능, 재가입 불가 선택 가능)
        - 매니저 추가/삭제 (회원 퇴출을 제외한 모든 권한)

병원
    필터링 : 24시, 지역
    - 병원이름, 전화번호, 주소



정책
점수에 따른 온도 변화 정책(온도는 너무 따라한 것 같은데 다른걸로 생각해보자 !)
    디폴트 온도 30 최대 100
    온도 70점 까지
        신고
        이성 문제 -5
        강아지 문제 -3
        후기 (넣을지 고민) 별점 1~5점으로 별점/2 적용 (반올림)
        번개 미참여 -3
    온도 70 ~ 100
        신고
        이성 문제 -7
        강아지 문제 -5
        후기 (넣을지 고민) 별점 1~5점으로 별점/3 적용 (반올림)
        번개 미참여 -5
    어려운 맛이 있어야 올리는 맛도 있을 듯. 대략 30번 정도 참여하면 100
    - 온도 0 회원 30일 정지 (30일 후 온도 10으로 변경)

후기에 대해 온도 올라갈 수 있음

영구 퇴출 회원 관리
    - 페이지 따로
    - 신고 내용이 너무 심하다면

신고에 따른 온도 적용
    페이지를 따로 만들어서 우리끼리 내용 확인 후 적용할지
    아니면 자동으로 할지

==========================================================================================

Kafka 사용 이유
서비스 간의 통신
기존에는 RestApi 통신으로, 커플링되어 응답 서버에 문제가 있으면 요청한 서버에서 진행이 안되기에 디커플링이 필요하다.
    (MSA 의 의존성 낮추는 장점이 무너짐)
I/O 데이터 구조 변경이 어려움
    (연결되는 서비스가 모두 다 같이 변경 해야한다.)
부하분산 필요
메시지 큐(Rabbit MQ, Active MQ)
    메모리에서 관리하고 반환하면 기존 데이터를 삭제한다.(여러 서비스에 전송 불가)
    낮은 처리량(through put)
    높은 레이턴시(latency)

그래서 이벤트 기반 MSA 아키텍쳐 Apache kafka 사용

REST API 호출이 적합한 경우:
    실시간 데이터 접근이 필요할 때
    동기적 처리와 강한 일관성이 필요할 때
이벤트 기반 통신이 적합한 경우:
    비동기적 처리가 가능하고 일관성 요구가 낮을 때
    서비스 간의 결합도를 낮추고 확장성을 높이고 싶을 때





    환경
kafka_2.13-3.7.0
connect : curl -O http://packages.confluent.io/archive/7.5/confluent-community-7.5.0.tar.gz
connector : curl -O https://d1i4a15mxbxib1.cloudfront.net/api/plugins/confluentinc/kafka-connect-jdbc/versions/10.6.4/confluentinc-kafka-connect-jdbc-10.6.4.zip

    실행
$ bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
$ bin/kafka-server-start.sh -daemon config/server.properties
$ bin/connect-distributed -daemon ./etc/kafka/connect-distributed.properties

bin/connect-distributed ./etc/kafka/connect-distributed.properties

Zookeeper: 2181
Kafka Broker: 9092
Kafka Connect REST API: 8083




$ bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
$ bin/kafka-server-start.sh -daemon config/server.properties

Zookeeper 의 기본 포트는 2181이고 Kafka 의 기본 포트는 9092
$ lsof -i :9092     (pid 확인)
$ kill -9 1523

$ bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
$ bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic HereTopicName --partitions 1
$ bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic topicname --partitions 1 --replication-factor 1

$ bin/kafka-topics.sh --delete --topic HereTopicName --bootstrap-server localhost:9092

Producer 등록
$ bin/kafka-console-producer.sh --broker-list localhost:9092 --topic HereTopicName
$ bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topicname

Consumer 등록
$ bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic HereTopicName --from-beginning
$ bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topicname --from-beginning




connect
$ bin/connect-distributed -daemon ./etc/kafka/connect-distributed.properties
$ lsof -i :8083

mariadb-java-client-2.7.2.jar 파일을 kafka connect → share/java/kafka로 복사함

connect-distributed.properties 파일은 Apache Kafka 의 Connect 분산 모드에서 사용되는 설정 파일입니다.
```properties
plugin.path=/usr/share/java
```
위 경로를 jdbc.jar 파일 경로로 변경해야한다.
```properties
plugin.path=/Users/userName/Documents/workspace/MungMo/confluentinc-kafka-connect-jdbc-10.6.4/lib/
```



1. 토픽 생성
POST localhost:8083/connectors
{
"name" : "test-connect-mungmo-test001",
"config" : {
"connector.class" : "io.confluent.connect.jdbc.JdbcSourceConnector",
"connection.url":"jdbc:mysql://localhost:3306/mungMo",
"connection.user":"mungmo",
"connection.password":"1234",
"mode": "incrementing",
"incrementing.column.name" : "seq",
"table.whitelist":"test001",
"topic.prefix" : "my_topic_",
"tasks.max" : "1"
}
}

2. sink connect 생성
POST localhost:8083/connectors
{
"name":"my-sink-connect-member-test001",
"config":{
"connector.class":"io.confluent.connect.jdbc.JdbcSinkConnector",
"connection.url":"jdbc:mysql://localhost:3306/mungmoMember",
"connection.user":"mungmo",
"connection.password":"1234",
"auto.create":"true",
"auto.evolve":"true",
"delete.enabled":"false",
"tasks.max":"1",
"topics":"my_topic_test001"
}
}

3. table 또는 데이터 생성 수정 삭제
mungMo 데이터베이스에서 dml 에 의해 mungmoMember 에 데이터 복제



topic 삭제를 하여면 먼저 생성한 connector 부터 삭제해야한다.

커넥터 삭제 : DELETE http://localhost:8083/connectors/my-sink-connect-member

토픽 삭제 : bin/kafka-topics.sh --delete --topic my_topic_test001 --bootstrap-server localhost:9092

확인 : bin/kafka-topics.sh --bootstrap-server localhost:9092 --list






==========================================================================================

트러블 슈팅

rest api vs kafka cdc 
데이터 정합







































