API 명세서 : https://documenter.getpostman.com/view/26372368/2sA3XQhN8y

# MungMo

Restful API 구현<br>
MSA 아키텍쳐 구축<br>
kafka connector(debezium)를 활용하여 cdc를 통해 데이터 동기화<br>
kafka 를 이용한 대규모 분산 처리 채팅 서비스 구현<br>
DDD(Domain Driven Design)<br>
<br>
기술 스택<br>
Java, Spring Boot, Spring Cloud, WebSocket<br>
MySQL, MongoDB, kafka<br>
<br>
상황 : 서비스간의 디커플링을 위해 데이터베이스를 분리하였고, 데이터 동기화가 필요했습니다.<br>
    또한 동기화를 통해 얻은 데이터로 모두 처리해도 될까? 라는 궁금증이 생겼습니다.<br>
실행 : 비동기, 분산처리, 스트리밍의 장점을 가진 카프카를 택하였고, 카프카 커넥터를 통해 동기화를 구축했습니다.<br>
    하지만 confluent로는 기존 데이터의 변경감지를 하지 못한다는 것을 확인하고 bulk는 오버헤드라 생각되어 debezium으로 설정하였습니다.<br>
    데이터 정합성이 중요한 로직에서 FeignClient 를 이용해 통신을 하였습니다.<br>
결과 : cdc를 통해 데이터를 동기화하여 다른 데이터베이스의 데이터를 가져올 수 있었고,<br>
    데이터 정합성이 중요한 매너 점수 연산에서 통신으로 데이터를 가져와 문제 없이 처리하였습니다.<br>
<br>
상황 : 채팅 서비스를 구현하며 메세지를 저장하고 발행한 회원의 정보와 같이 메세지츨 출력해야하며<br>
    휴대폰 앱 사용자에게 알림을 저장하고 전송해야하는데,<br>
    매번 메세지 한번 보낼 때 마다 웹소켓 하나로 모두 처리하는 것이 걱정되었습니다.<br>
실행 : 웹소켓으로 메세지가 들어오면 두 개의 kafka producer에 저장하고 consumer 로 메세지가 들어오면 로직을 처리할 수 있도록 구현하였습니다.<br>
결과 : 하나의 메세지에 처리할 로직이 많고 다수의 사용자가 가볍게 사용되는 메세지의 무게를 낮출 수 있었습니다.<br>
<br>
상황 : 유저 매너 점수를 반영하는 기능은 언제든 정책이 바뀌면 쉽게 반영될 수 있도록 설계가 필요했습니다.<br>
실행 : 확장성을 고려해서 추상화하여 기능을 구현하고, 도메인 비즈니스 로직의 기능과 객체와의 느슨한 결합도가 필요하여 바로 생각난 것이 Spring DI 였고, Factory Method Pattern 을 통해 클라이언트 객체와 느슨한 결합을 완성하였습니다.<br>
결과 : 정책이 변경이 되어도 추상화를 통해 새로운 구현체를 바로 생성할 수 있고, 클라이언트 객체가 아닌 팩토리에서 하나만 변경해주면 됩니다. 이로써 객체지향 설계원칙, OCP 를 지켜낼 수 있었습니다.<br>
<br>
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







































