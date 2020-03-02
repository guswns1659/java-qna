# Step3 

## Step3 진행하며 배운 것 
- 자바지기 영상보니 모르면 검색하는 게 일상. 두려워하지 않고 검색한다. 
- Bad Request일 경우 mapping URL에 @RequestMapping에 해당하는 url이 있나 확인 ex)/users/{{id}}
- 사용자일 땐 몰랐던 기능들이 로그인 하나로 복잡해질 수 있음을 깨달음. 
- getter, setter로 객체의 속성에 직접 접근하지 말고 메세지를 보내서 자율성을 높이는 중요성을 깨달음. 과거 호눅스가 getter가 적으면 좋다는 피드백을 했는데 이해가 된다. 
- @ManyToOne은 Many에 해당하는 객체에 매핑을 한다.

## 해결 못한 것 
- auto reload 기능 : 코드 변경하면 서버 재시작하지 않고 바로 반영하게 하고 싶은데 검색대로 했는데 기능 동작안함. 

## 로그인 기능 구현
### 어려운 점 
- 보안을 위해 로그인한 사용자인지 매번 확인하는 로직이 처음엔 복잡했다. 

## 중복제거 및 리팩토링 
- user의 속성을 getter로 가져오지 않고 메세지를 보내서 자율적으로 구현하는 것이 객체 지향 프로그래밍이라는 걸 다시 느낌 ex) user.notMatchPassword(password)   
### 어려운 점
- 반복되는 로그인된 유저 확인 로직을 메서드로 분리하고 싶었는데 하지 못함. 다른 클래스에서 redirect하면 실행이 안됐다. 더 공부가 필요하다.

## 질문 기능 구현
### 어려움 
- 질문 수정 및 삭제 시 작성한 사용자만 가능한 코드를 작성이 어려웠다. 
- Writer 1명 당 question은 여러개를 작성할 수 있다는 건 당연한 건데 Join으로 매핑된다는 점을 이해하는 데 시간이 걸림.

## Answer 구현
### 어려움
- 답변하기 버튼의 type을 button이 아니라 submit으로 해야 반응.

- 답변하기 클릭하면 입력된 정보를 처리하는 컨트롤러에서 contents와 writer와 question을 answer에 넣는다.  그 뒤 answerRepository에 save한다. 
- show에서 questionId에 해당하는 answers를 찾고 있다면 보여주고 없다면 기본 show를 보여준다. 
- 수정 버튼 누르면 로그인 여부와 글쓴이인지 검사하고 updateForm으로 이동.
- 수정할 답변 입력 후 '수정하기' 버튼 누르면 입력된 contents로 answer를 update하고 answerRepository에 저장한다.
- 삭제 버튼 누르면 로그인 여부와 글쓴이인지 검사하고 일치하면 삭제.   
