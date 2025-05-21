# API 명세서

<details><summary> API 명세서 </summary>

| 기능 | 메서드 | URL |
|:---:|:---:|:---:|
|일정 생성|POST|/schedules|
|전체 일정 조회|GET|/schedules|
|선택 일정 조회|GET|/schedules/{scheduleId}|
|일정 수정|PUT|/schedules/{scheduleId}|
|일정 삭제|DELETE|/schedules/{scheduleId}|
|유저 생성(회원가입)|POST|/users|
|전체 유저 조회|GET|/users|
|선택 유저 조회|GET|/users/{userId}|
|유저 수정|PUT|/users/{userId}|
|유저 삭제|DELETE|/users/{userId}|
|로그인|POST|/users/login|

<details><summary> 일정 생성 API</summary>
  
# 일정 생성 API
 <p> URL : /schedules </p>
  HTTP Method : POST </p>
  설명 : 일정을 생성하는 API입니다. </p>

## 요청

### BODY

| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|title|String|일정 제목|
|schedule|String|일정 내용|

#### 예시
```
 {
  "title": "오늘 할 일",
  "schedule": "점심에 육회비빔밥 먹기"
}
 ```
## 응답

### 성공 시

| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|userId|long|유저 식별자|
|title|String|일정 제목|
|scheduleId|long|일정 식별자|
|schedule|String|일정 내용|
|createDate|LocalDateTime|생성 날짜/시간|
|updateDate|LocalDateTime|수정 날짜/시간|

#### 예시
```
{
  "userId": 17,
  "title": "오늘 할 일",
  "scheduleId": 2,
  "schedule": "점심에 육회비빔밥 먹기",
  "createDate": 2025-05-19  11:22:33,
  "updateDate": 2025-05-19  11:22:33
}
```

### 실패 시

| 키 | 타입 | 설명|
|:---:|:---:|:---:|
|code|int|오류 코드|
|message|String|오류에 관한 설명|

#### 예시
```
{
  "code": 400,
  "message": "잘못된 요청입니다."
}
```
</details>
<details> <summary>전체 일정 조회 API</summary>

# 전체 일정 조회 API
<p> URL : /schedules </p>
    HTTP Method : GET </p>
    설명 : 전체 일정 목록을 조회하는 API입니다. </p>
    
## 요청
필요한 값이 없습니다.

## 응답

### 성공 시

| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|userId|long|유저 식별자|
|title|String|일정 제목|
|scheduleId|long|일정 식별자|
|schedule|String|일정 내용|
|createDate|LocalDateTime|생성 날짜/시간|
|updateDate|LocalDateTime|수정 날짜/시간|

#### 예시
```
[
{
  "userId": 17,
  "title": "오늘 할 일",
  "scheduleId": 2,
  "schedule": "점심에 육회비빔밥 먹기",
  "createDate": 2025-05-19  11:22:33,
  "updateDate": 2025-05-19  11:22:33
},
{
  "userId": 18,
  "title": "내일 할 일",
  "scheduleId": 7,
  "schedule": "오전에 낮잠 자기",
  "createDate": 2025-05-19  11:15:33,
  "updateDate": 2025-05-19  12:23:45
}
]
```

### 실패 시

| 키 | 타입 | 설명|
|:---:|:---:|:---:|
|code|int|오류 코드|
|message|String|오류에 관한 설명|

#### 예시
```
{
  "code": 404,
  "message": "데이터가 존재하지 않습니다."
}
```
</details>
<details><summary>선택 일정 조회 API</summary>

# 선택 일정 조회 API

<p> URL : /schedules/{scheduleId} </p>
    HTTP Method : GET </p>
    설명 : 선택 일정 하나를 조회하는 API입니다. </p>

## 요청

### Param
| 키 | 타입 | 설명|
|:---:|:---:|:---:|
|scheduleId|long|일정 식별자|

#### 예시
```
http://localhost:8080/schedules/10
```
## 응답

### 성공 시

| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|userId|long|유저 식별자|
|title|String|일정 제목|
|scheduleId|long|일정 식별자|
|schedule|String|일정 내용|
|createDate|LocalDateTime|생성 날짜/시간|
|updateDate|LocalDateTime|수정 날짜/시간|

#### 예시
```
{
  "userId": 1,
  "title": "오늘 할 일",
  "scheduleId": 2,
  "schedule": "점심에 육회비빔밥 먹기",
  "createDate": 2025-05-19  11:22:33,
  "updateDate": 2025-05-19  11:22:33
}
```

### 실패 시

| 키 | 타입 | 설명|
|:---:|:---:|:---:|
|code|int|오류 코드|
|message|String|오류에 관한 설명|

#### 예시
```
{
  "code": 404,
  "message": "데이터가 존재하지 않습니다."
}
```
</details>

<details><summary> 일정 수정 API </summary>

# 일정 수정 API

<p> URL : /schedule/{scheduleId} </p>
    HTTP Method : PUT </p>
    설명 : 선택한 일정을 수정하는 API입니다. </p>

## 요청 

### Param

| 키 | 타입 | 설명 |
|:---:|:---|:---:|
|scheduleId|long|일정 식별자|


#### 예시 
```
http://localhost:8080/schedules/22
```

### Body

|키|타입|설명|
|:---:|:---:|:---:|
|title|String|일정 제목|
|schedule|String|일정 내용|
|password|String|비밀번호|

#### 예시
```
 {
  "title": "수정된 제목",
  "schedule": "수정된 일정 내용",
  "password": "유저 비밀번호"
}
 ```

## 응답

### 성공 시

| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|userId|long|유저 식별자|
|title|String|일정 제목|
|scheduleId|long|일정 식별자|
|schedule|String|일정 내용|
|createDate|LocalDateTime|생성 날짜/시간|
|updateDate|LocalDateTime|수정 날짜/시간|

## 예시
```
{
  "userId": 1,
  "title": "수정된 제목"
  "scheduleId": 2,
  "schedule": "수정된 내용",
  "createDate": 2025-05-19  11:22:33,
  "updateDate": 2025-05-19  14:44:44
}
```



### 실패 시

| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|code|int|오류 코드|
|message|String|오류 관련 내용|

#### 예시
```
{
  "code": 401,
  "message": "잘못된 비밀번호 입니다.
}
```

</details>

<details><summary>일정 삭제 API</summary>

# 일정 삭제 API

<p> URL : /schedules/{scheduleId} </p>
    HTTP Method : DELETE </p>
    설명 : 선택한 일정을 삭제하는 API입니다. </p>

## 요청

### Param
| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|scheduleId|long|일정 식별자|

#### 예시
```
http://localhost:8080/schedules/2
```

### Body
| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|password|String|비밀번호|

#### 예시
```
{
  "password": "password12"
}
```



## 응답

### 성공 시

| 키 | 타입 | 설명|
|:---:|:---:|:---:|
|code|int|HTTP 코드(200)|
|message|String|메시지|


#### 예시
```
{
  "code": 200,
  "message": "성공적으로 삭제되었습니다."
}
```

### 실패 시
| 키 | 타입 | 설명|
|:---:|:---:|:---:|
|code|int|HTTP 코드(400)|
|message|String|메시지|

#### 예시
```
{
  "code": 401,
  "message": "잘못된 비밀번호입니다."
}
```

</details>

<details><summary> 유저 생성(회원가입) API</summary>

# 유저 생성(회원가입) API
<p> URL : /users </p>
    HTTP Method : POST </p>
    설명 : 유저를 생성하는 API 입니다.</p>

## 요청
### BODY

| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|userName|String|유저 이름|
|email|String|이메일|
|password|String|비밀번호|

#### 예시
```
 {
  "userName": "사용자1",
  "email": "sayongja1@gmail.com",
  "password": "password1"
}
 ```

## 응답
| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|userId|long|유저 식별자|
|userName|String|유저 이름|
|email|String|이메일|
|createDate|LocalDateTime|생성 날짜/시간|

#### 예시
```
{
  "userId": 17,
  "userName": "사용자1",
  "email": "sayongja1@gmail.com",
  "createDate": 2025-05-19 15:13:13
}
```
</details>
<details> <summary>전체 유저 조회 API</summary>

# 전체 유저 조회 API
<p> URL : /users </p>
    HTTP Method : GET </p>
    설명 : 전체 유저 목록을 조회하는 API입니다. </p>
    
## 요청
필요한 값이 없습니다.

## 응답

### 성공 시

| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|userId|long|유저 식별자|
|userName|String|유저 이름|
|email|String|이메일|
|createDate|LocalDateTime|생성 날짜/시간|

#### 예시
```
[
{
  "userId": 17,
  "userName": "사용자1",
  "email": "sayomgja1@gmail.com",
  "createDate": 2025-05-19  15:13:13
},
{
  "userId": 18,
  "userName": "사용자9",
  "email": "sayomgja9@naver.com",
  "createDate": 2025-05-19  18:11:18
}
]
```

### 실패 시

| 키 | 타입 | 설명|
|:---:|:---:|:---:|
|code|int|오류 코드|
|message|String|오류에 관한 설명|

#### 예시
```
{
  "code": 404,
  "message": "데이터가 존재하지 않습니다."
}
```


</details>
<details><summary>선택 유저 조회 API</summary>

# 선택 유저 조회 API

<p> URL : /users/{userId} </p>
    HTTP Method : GET </p>
    설명 : 선택 유저 한 명을 조회하는 API입니다. </p>

## 요청

### Param
| 키 | 타입 | 설명|
|:---:|:---:|:---:|
|userId|long|유저 식별자|

#### 예시
```
http://localhost:8080/users/17
```
## 응답

### 성공 시

| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|userId|long|유저 식별자|
|userName|String|유저 이름|
|email|String|이메일|
|createDate|LocalDateTime|생성 날짜/시간|

#### 예시
```
{
  "userId": 17,
  "userName": "사용자1",
  "email": "sayongja1@gmail.com",
  "createDate": 2025-05-19  15:13:13
}
```

### 실패 시

| 키 | 타입 | 설명|
|:---:|:---:|:---:|
|code|int|오류 코드|
|message|String|오류에 관한 설명|

#### 예시
```
{
  "code": 404,
  "message": "유저가 존재하지 않습니다."
}
```
</details>
<details><summary> 유저 수정 API </summary>

# 유저 수정 API

<p> URL : /users/{userId} </p>
    HTTP Method : PUT </p>
    설명 : 선택한 유저 이름을 수정하는 API입니다. </p>

## 요청 

### Param

| 키 | 타입 | 설명 |
|:---:|:---|:---:|
|userId|long|유저 식별자|


#### 예시 
```
http://localhost:8080/users/17
```

### Body

|키|타입|설명|
|:---:|:---:|:---:|
|userName|String|유저 이름|
|password|String|비밀번호|

#### 예시
```
 {
  "userName": "수정된 사용자 이름",
  "password": "유저 생성 시 입력한 비밀번호"
}
 ```

## 응답

### 성공 시

| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|userId|long|유저 식별자|
|userName|String|유저 이름|
|email|String|이메일|
|createDate|LocalDateTime|생성 날짜/시간|

## 예시
```
{
  "userId": 1,
  "userName": "수정된 사용자 이름",
  "email": sayongja1@gamil.com,
  "createDate": 2025-05-19  15:13:13
}
```



### 실패 시

| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|code|int|오류 코드|
|message|String|오류 관련 내용|

#### 예시
```
{
  "code": 401,
  "message": "잘못된 비밀번호 입니다.
}
```

</details>

<details><summary>유저 삭제 API</summary>

# 유저 삭제 API

<p> URL : /users/{userId} </p>
    HTTP Method : DELETE </p>
    설명 : 선택한 유저를 삭제하는 API입니다. </p>

## 요청

### Param
| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|userId|long|유저 식별자|

#### 예시
```
http://localhost:8080/users/17
```

### Body
| 키 | 타입 | 설명 |
|:---:|:---:|:---:|
|password|String|비밀번호|

#### 예시
```
{
  "password": "password1"
}
```


## 응답

### 성공 시

| 키 | 타입 | 설명|
|:---:|:---:|:---:|
|code|int|HTTP 코드(200)|
|message|String|메시지|


#### 예시
```
{
  "code": 200,
  "message": "성공적으로 삭제되었습니다."
}
```

### 실패 시
| 키 | 타입 | 설명|
|:---:|:---:|:---:|
|code|int|HTTP 코드(400)|
|message|String|메시지|

#### 예시
```
{
  "code": 401,
  "message": "잘못된 비밀번호입니다."
}
```

</details>
<details><summary> 로그인 API</summary>

# 로그인 API
<p> URL : /users/login </p>
    HTTP Method : POST </p>
    설명 : 로그인 API 입니다. </p>

## 요청

### Body

|키|타입|설명|
|:---:|:---:|:---:|
|userName|String|유저 이름|
|password|String|비밀번호|

#### 예시
```
{
  "userName": "사용자1",
  "password": "password1"
}
```


## 응답

### 성공 시

|키|타입|설명|
|:---:|:---:|:---:|
|userId|long|유저 식별자|
|userName|String|유저 이름|

#### 예시
```
{
  "userId": 17,
  "userName": "사용자1"
}
```

### 실패 시

|키|타입|설명|
|:---:|:---:|:---:|
|code|int|HTTP코드|
|message|String|메시지|

#### 예시
```
{
  "code": 401,
  "message": "잘못된 비밀번호입니다.
}
```

</details>

</details>


# ERD 다이어그램
<details><summary>ERD 다이어그램</summary>

<img src="https://github.com/user-attachments/assets/608729f4-1e23-416a-b888-b102c12f5f1e" weiht="600" higeth="400">
  
</details>




