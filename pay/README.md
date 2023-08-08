# 설정

프로젝트를 실행시키기 위해서 아래 파일을 만들고 설정을 추가해야 한다.
- /src/main/resources/application-local-api.yml
- /src/test/resources/application-test-api.yml
```yaml
kakaopay:
  admin-key: 카카오 어플리케이션 Admin 키 
  host: https://kapi.kakao.com
  cid: 가맹점 코드 (테스트 : TC0ONETIME)
```
