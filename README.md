# preTask

1. 최상위 경로에 있는 src폴더와 pom.xml을 다운받습니다.

2. STS or Intellij 등의 IDE에서 메이븐 프로젝트를 생성합니다.

3. 생성한 프로젝트 내의 src폴더와 pom.xml 파일을, 다운받은 위 2개의 파일로 덮어쓰기합니다.(교체)

4. Maven이 실행에 필요한 모든 파일을 다운로드 받습니다.

5. Maven이 모든 파일을 다운로드 받으면 서버를 실행합니다.

6. 서버가 실행되면 localhost:8080/login/auth.do 로 접속합니다.

7. 아이디와 패스워드는 다음과 같습니다.
   ID : nice
   PW : 1234
   
8. 아이디와 패스워드를 정확히 입력하면 파일업로드 화면으로 이동합니다.

9. 파일선택 버튼을 눌러 실행자의 PC 내 csv파일을 선택하여 확인버튼을 누릅니다.(utf-8버전)

10. 첨부 버튼을 클릭하면 DB Insert API가 실행되며, 적재에 성공하면
    '파일 적재 성공, 조회화면으로 이동합니다.'라는 문구와 함께 조건별 조회화면으로 넘어갑니다.
    
11. 요구사항에 따른 3가지의 조회조건이 나오며 각각의 조회조건 옆에 조회버튼이 보입니다.

12. 조회버튼을 각각의 API가 실행되며 클릭하면 하단 < 조 회 결 과> 아래에 결과가 조회됩니다.
