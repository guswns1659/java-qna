# Step3 

## step2 추가 학습 
- Controller에서 View로 모델로 데이터를 한 개만 넘기는 상황. 그 때 view에서 해당 model 데이터를 사용하려면 코드를 {{#user}} ... {{/user}}로 감싸야 한다. 기존에는 {{user.name}} 이렇게 접근함. 
- 개인정보 수정 시 user 객체에 update 메서드 추가한다. update에서 개인정보 수정하고 DB에 넣기. 훨씬 코드가 간결해짐. 

## 자가 피드백 
- 자바지기 영상보니 모르면 검색하는 게 일상. 두려워하지 않고 검색한다. 
- Bad Request일 경우 mapping URL이 "/users/login"처럼 /users를 붙였는 지 확인.
## 전체적 어려움 
- 코드 수정하면 자동으로 웹페이지에 적용이 되야 하는데 안된다. 매번 서버를 재시작해야 한다. 

## 로그인 기능 구현
- 메인에서 로그인 버튼 누르면 /users/login으로 이동, 관리하는 login Controller 생성
- 로그인 창에서 전달된 아이디, 패스워드를 처리하는 Controller 생성, 
    - userId로 DB에서 데이터를 가져온다. UserRepository에 findByUserId() 생성
    - 만약, user==null이거나 패스워드가 틀리면 다시 로그인 페이지 로드 return "/login"
    - 아이디, 비밀번호 인증 성공 시 HttpSesstion에 user 저장하고 return "/"; 
- 로그인 로직 처리 후 메인 페이지로 이동.

## 로그인 상태에 따른 메뉴 처리 및 로그아웃 기능 구현
- import.sql 파일을 통해 회원 데이터 하나 초기화. 위치는 resources 패키지 아래에. 
- 로그아웃 상태면 로그인, 회원가입 메뉴만, 로그인 상태면 로그아웃, 개인정보수정 메뉴만 나오게 하기
    - mushtach 문법 중 if - else와 비슷한 문법 사용. {{^user}} , {{#user}} .. 
    - httpSession 정보를 mushtach한테 전달할 수 있게 properties를 수정해야 한다.
- 로그아웃 시 session에 저장된 user 제거한다. 제거하면 muschtach는 세션에서 user가 안 넘어오니 로그인, 회원가입 메뉴만 남긴다.
   
 
