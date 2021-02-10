# Board_Project_V3-ADMIN(VERSION 3)

< 추가 >
### 관리자 모드

### 검색 서비스

### 조회수 기반 인기 게시글 서비스
<hr>

## 1. 관리자 모드

![image](https://user-images.githubusercontent.com/66015002/107471860-322b1600-6bb1-11eb-9154-60ca3582351e.png)

> Spring Security 기반으로 한 권한 관리를 이용한다.

### 1.1
![image](https://user-images.githubusercontent.com/66015002/107472175-d1500d80-6bb1-11eb-8572-c8fc8062d461.png)

> 유저 도메인에 authority라는 임의의 권한 컬럼을 넣어준다.

### 1.2
![image](https://user-images.githubusercontent.com/66015002/107472307-107e5e80-6bb2-11eb-8e43-0f9cba60e06e.png)

> 로그인을 처리하는 인증객체에(아이디 , 비밀번호 , 권한) 권한 부분에 아까 적은 authority 값을 넣어준다.
그렇게 되면 로그인 할 때 자동으로 그 권한 값을 가진 유저가 로그인하게 되는 것이다. 그렇다면 그 authority는 어떻게 값을 주는 것일까?

### 1.3 
![image](https://user-images.githubusercontent.com/66015002/107472638-a6b28480-6bb2-11eb-832a-fa57921fa00f.png)
![image](https://user-images.githubusercontent.com/66015002/107472692-bdf17200-6bb2-11eb-93d6-7f17a7e97e76.png)

> 관리자 권한을 가진 아이디로 접속하면 이렇게 유저들을 확인할 수 있고, 여기서 권한을 부여할 수 있게 된다.

### 1.4 
![image](https://user-images.githubusercontent.com/66015002/107472955-35270600-6bb3-11eb-8b22-bb504ac14303.png)

> 관리자 페이지에 대한 접근 권한은 다음과 같이 작성하였다. "/admin" 으로 시작되는 모든 주소에 대한 접근에 대해서, 권한이 "ROLE_ADMIN"인 사용자만
그 주소로 들어갈 수 있는 것이다. 당연히 이외의 권한을 가진 사용자들은 접속이 안된다.

### 추가 설명



> 이렇게 권한 설정을 할 때 꼭 해줘야할 것이 있다. 바로 유저들이 **회원가입할 때 자동으로 ROLE_USER 권한을 갖도록 service에 지정을** 해줘야 하는 것이다.
만약 그렇지 않다면, 새로 회원가입한 유저들은 권한이 null 값이기 때문에 로그인 자체가 안될 것이다.



<hr/>



## 2. 검색 서비스
![image](https://user-images.githubusercontent.com/66015002/107474113-24778f80-6bb5-11eb-9bad-6ab7fbd36bba.png)
![image](https://user-images.githubusercontent.com/66015002/107474289-71f3fc80-6bb5-11eb-925f-2fe8e1f5e82c.png)

> Query DSL을 사용하여 검색 쿼리문을 만들다. 검색어를 입력하면 게시글 제목, 게시글 내용, 작성자에 대한 내용이 있다면 검색이 된다.

### 2.1
![image](https://user-images.githubusercontent.com/66015002/107474493-c7300e00-6bb5-11eb-9603-af0084aa2f7a.png)

> build.gradle에 query dsl을 추가하고 컴파일을 하면 이렇게 쿼리dsl 객체들이 생긴다.

### 2.2
![image](https://user-images.githubusercontent.com/66015002/107474639-0d856d00-6bb6-11eb-8f15-c6f8733f21b3.png)

> 메인 클래스에 QueryFactory Bean을 주입하고 이제 작성하면 된다.

### 2.3
![image](https://user-images.githubusercontent.com/66015002/107474770-4d4c5480-6bb6-11eb-956c-ad293571b416.png)

> 검색결과를 페이징 처리를 하여 나타내었다. 게시글의 제목에(subject) 검색어가(keyword) 있다면 혹은 게시글의 내용에(text) 검색어가 있다면 혹은
 게시글의 글쓴이(nickname)에 검색어가 있다면 다 찾아서 리스트화 해서 나타내는 코드이다.
 
 ### 2.4
 ![image](https://user-images.githubusercontent.com/66015002/107475104-f09d6980-6bb6-11eb-9bfa-517d49f27ec2.png)
 
 > 검색어로 '병각이'를 검색했을 때 나오는 결과물이다.
 
 
 <hr/>
 
 
 
 ## 3. 인기 게시글
 
 ![image](https://user-images.githubusercontent.com/66015002/107475241-2b070680-6bb7-11eb-8944-31d03f8c2389.png)
 
 > v2에서 만들었던 조회수를 기반으로 하여 top 3를 보여주는 인기 게시판 기능이다. query dsl을 이용하여 작성하였다.
 
 
 ### 3.1
 ![image](https://user-images.githubusercontent.com/66015002/107475409-6c97b180-6bb7-11eb-911d-8f46583b9353.png)
 
 > 게시글들을 viewcount(조회수)를 기준으로 내림차순하여 큰것부터 limit(3) 3개까지만 보여준다는 뜻이다.
 
 ### 3.2
 ![image](https://user-images.githubusercontent.com/66015002/107475597-bda7a580-6bb7-11eb-8f30-535e6a2279c3.png)
 
 > controller로 넘겨서 view로 리스트 형식으로 보여주면 된다.
