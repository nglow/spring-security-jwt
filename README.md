# JWT Authentication

## Appearance Background
* 서버 기반 인증 시스템을 사용할 때 발생하는 문제를 해결하기 위해서 등장
* 서버 기반 인증 시스템에는 쿠키/세션을 사용하는 방법이 있음
* 이 방식들은 보안 문제, 서버의 성능 저하 문제등이 존재함.

## JWT (Jason Web Token)
JWT란 인증에 필요한 정보를 암호화 시킨 Token을 의미 합니다.
쿠키/세션 인증 방식과 유사하게 토큰을 HTTP 헤더에 실어 서버가 클라이언트를 식별합니다.
### JWT의 구조
![jwt](./image/jwt.png)
JWT는 마침표(.)를 구분자로 가지는 세가지 문자열의 조합입니다.
실제 디코딩된 JWT는 다음과 같은 구조를 가집니다.
1. Header<br>
   ![header](./image/header.png)<br>
   Header는 해싱 알고리즘 및 토큰의 타입을 지정합니다.
2. Payload<br>
   ![payload](./image/payload.png)<br>
    Payload는 토큰에 담으 정보를 기억합니다.
    주로 클라이언트의 고유 ID값 및 유효기간 등이 포함되는 영역입니다.
    **Key-Value 형식으로 이루어진 한 쌍의 정보를 *Claim*이라고 칭합니다.**  
3. Signature<br>
    ![signature](./image/signature.png)<br>
    Signature는 인코딩된 header와 payload를 더한뒤, 비밀키로로 해싱하여 생성합니다.
    Header와 payload는 단순히 인코딩된 값이기 때문에 단순히 복호화 및 조작을 할 수 있지만, Signature는 서버 측에서 관리하는 비밀키가 유출되지
    않는한 복호화할 수 없습니다. 따라서 Signature는 토큰이 위변조 여부를 확인하는데 사용됩니다.
### 인증 과정

```text
{
  Authorization: <type> <access-token>  
}
```
1. 클라이언트로 로그인 요청이 들어오면, 서버는 검증 후 클라이언트의 고유 ID 등
payload에 필요한 정보들을 담습니다.
2. 비밀키를 사용하여 Access Token을 발급합니다.
3. 클라이언트는 전달받은 토큰을 저장해두고, 서버에 요청할 때 마다 토큰을 요청 헤더에
포함 시켜 요청을 전달 합니다.
4. 서버는 토큰의 Signature를 비밀키로 복호화한 다음, 위변조 여부 및 유효기간 등을 확인합니다.
5. 유효한 토큰이라면 요청에 응답합니다.

### 장점
1. Header와 Payload를 가지고 비밀키를 이용해 Signature를 생성 -> 데이터 위변조 방지
2. 인증 정보에 대해 서버 사이드에 별도의 저장소가 필요없음
3. 클라이언트 인증 정보를 서버에 저장해두는 세션과 다르게, 서버는 무상태(Stateless)가 됨 -> 확장성(Scale-out) 우수
4. 토큰 기반으로 다른 로그인 시스템(서버)에 접근 및 권한 공유가 가능
5. OAuth의 경우 Facebook, Google 등 소셜 계정을 이용해 다른 웹서비스에서도 로그인 가능
### 단점
1. 쿠키/세션과는 다르게 JWT 인증방식은 토큰의 길이가 길어, 인증 요청이 많아질수록 네트워크 부하가 심해짐
2. Payload 자체는 암호화되지 않음 -> 유저의 중요한 정보는 담을 수 없음
3. 토큰이 한 번 발급되면 유효기간이 만료될때까지 계속 사용 가능 -> 토큰을 탈취당하면 대처하기 어려움
4. 쿠키/세션 기반 인증은 서버 쪽에서 쉽게 세션을 삭제할 수 있으나, JWT 인증 방식은 특정 사용자의 접속을 강제로 만료하기 어려움
## 보안 전략
JWT 사용시 상기한 단점들을 극복하기 위해 다양한 전략을 채택할 수 있습니다. 각각의 전

## Reference
* [Spring Boot JWT Tutorial](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-jwt/dashboard)
* [Tecoble](https://tecoble.techcourse.co.kr/post/2021-05-22-cookie-session-jwt/)