
keytool -genkeypair -help       
keytool -genkeypair [OPTION]...
Generates a key pair
Options:
-alias <alias>                  alias name of the entry to process
-keyalg <keyalg>                key algorithm name
-keysize <keysize>              key bit size
-sigalg <sigalg>                signature algorithm name
-destalias <destalias>          destination alias
-dname <dname>                  distinguished name
-startdate <startdate>          certificate validity start date/time
-ext <value>                    X.509 extension
-validity <valDays>             validity number of days
-keypass <arg>                  key password
-keystore <keystore>            keystore name
-storepass <arg>                keystore password
-storetype <storetype>          keystore type
-providername <providername>    provider name
-providerclass <providerclass>  provider class name
-providerarg <arg>              provider argument
-providerpath <pathlist>        provider classpath
-v                              verbose output
-protected                      password through protected mechanism
Use "keytool -help" for all available commands


### keytool 생성

keytool -genkeypair -alias mungmoEncryptionKey -keyalg RSA -keypass "test" -keystore mungmoEncryptionKey.jks -storepass "test"

다음에 대해 유효 기간이 90일인 2,048비트 RSA 키 쌍 및 자체 서명된 인증서(SHA256withRSA)를 생성하는 중
: CN=bighwang, OU=mungmo, O=develop, L=incheon, ST=seogu, C=82

```text
$ ls  
mungmoEncryptionKey.jks
```

```text
$ keytool -list -keystore ./mungmoEncryptionKey.jks -v
키 저장소 비밀번호 입력:  
키 저장소 유형: PKCS12
키 저장소 제공자: SUN

키 저장소에 1개의 항목이 포함되어 있습니다.

별칭 이름: mungmoencryptionkey
생성 날짜: 2024. 7. 28.
항목 유형: PrivateKeyEntry
인증서 체인 길이: 1
인증서[1]:
소유자: CN=bighwang, OU=mungmo, O=develop, L=incheon, ST=seogu, C=82
발행자: CN=bighwang, OU=mungmo, O=develop, L=incheon, ST=seogu, C=82
일련 번호: 25911825b476e876
적합한 시작 날짜: Sun Jul 28 15:38:17 KST 2024 종료 날짜: Sat Oct 26 15:38:17 KST 2024
인증서 지문:
SHA1: E8:0F:75:DA:FF:F5:C4:41:17:E3:A5:AE:8F:46:C0:6A:C4:E9:16:B1
SHA256: 51:88:90:94:F8:52:C7:E8:48:11:97:9F:A2:FC:B1:ED:79:C9:21:9B:42:9E:A5:B4:8A:53:EB:B3:DE:47:6B:7D
서명 알고리즘 이름: SHA256withRSA
주체 공용 키 알고리즘: 2048비트 RSA 키
버전: 3

확장:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 7B 45 A1 13 7B 3B FB 94   48 BA E1 2A 6C 06 4E 37  .E...;..H..*l.N7
0010: B2 23 AF 4F                                        .#.O
]
]

*******************************************
*******************************************
```

### public key 생성

```text
$ keytool -export -alias mungmoEncryptionKey -keystore mungmoEncryptionKey.jks -rfc -file trustServer.cer
```

### public key를 인증서로 import

```text
$ keytool -import -alias trustServer -file ./trustServer.cer -keystore publickey.jks
```

### 암호화

POST curl localhost:8888/encrypt -d mysecretvalue
자동으로 엔드포인트 설정되어 text 보내면 암호화 되어 반환된다.