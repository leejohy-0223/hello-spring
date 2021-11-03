# [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8/dashboard)
- 인프런 스프링 입문 무료 강의 실습 정리입니다.
  <br><br>
  
### 실습 환경
- IDE: IntelliJ
- OS: Mac OS

### 실습 내용
1. 프로젝트 환경 설정
   - Gradle을 통해 의존관계가 있는 라이브러리를 함께 다운로드 할 수 있다.
   - spring-boot-starter-seb
      - spring-boot-starter-tomcat: 톰캣(웹서버)
      - spring-webmvc: 스프링 웹 MVC
   - spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
   - spring-boot-starter(공통) : 스프링 부트 + 스프링 코어 + 로깅(logback, slf4j)
   
2. 빌드 후 실행하기
   1. ./gradlew build
   2. build/libs로 이동 후 java -jar ~-SNAPSHOT.jar로 실행
   
3. @ResponseBody
   - @ResponseBody를 사용하면 viewResolver가 사용되지 않고 http body에 문자 내용을 직접 반환한다.
   - 또한 객체를 반환하면 JSON으로 변환되어 반환된다.
   - viewResolver 대신 HttpMessageConverter가 선택되어 동작한다. 여기에는 기본 문자 및 객체를 처리할 수 있는 Converter가 각각 기본으로 등록되어 있다.
   
   
4. 웹 애플리케이션 계층 구조
   - Controller : 웹 MVC의 컨크롤러 역할
   - Service : 핵심 비즈니스 로직 구현
   - Repository : 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
   - Domain : 비즈니스 도메인 객체(회원, 주문 등등 주로 DB에 저장하고 관리됨)
   
5. 테스트 케이스 작성
   - JUnit Framework를 이용하여 반복 테스트 및 여러 테스트를 한번에 실행할 수 있다.
   - @AfterEach를 통해 각 테스트가 종료될 때마다 해당 기능을 실행할 수 있다. 테스트는 각각 독립적으로 실행되어야 하므로, 의존관계를 없애야 한다.
   
6. 스프링 빈과 의존관계
   - 생성자에 @Autowired가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection)이라고 한다.
   - 스프링 빈은 컴포넌트 스캔 또는 직접 자바 코드로 등록할 수 있다.
   - 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본적으로 싱글톤을 등록한다. 따라서 같은 스프링 빈은 모두 같은 인스턴스이다. 
   
7. 스프링 DB 접근 기술
   - 개발이나 테스트 용도로 가벼운 H2 Database를 사용하였다.
    1. 순수 JDBC API : 과거 사용 방식으로, CRUD 구현에 있어서 각각 SQL 작성이 필요하며, setting이 복잡하고 반복 코드가 많다.
    2. 스프링 JdbcTemplate : JDBC API에서의 반복코드를 대부분 제거해준다. 하지만 SQL은 직접 작성해야 한다.
    3. JPA : 기존의 반복코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다. 이를 통해 객체 중심의 설계로 패러다임을 전환할 수 있으며 개발 생산성을 높일 수 있다.
    4. Spring Data JPA : JPA를 편하게 다루도록 도와주는 기술이다. 구현 클래스 없이 인터페이스 만으로 개발이 가능해진다.
    5. Querydsl : 복잡한 동적 쿼리를 편리하게 작성할 수 있도록 도와주는 라이브러리이다. 쿼리도 자바 코드로 안전하게 작성이 가능하다.
    
8. AOP(Aspect Oriented Programming)
   - 공통 관심 사항과 핵심 관심 사항을 분리한다. 
   - 공통 관심 사항을 원하는 곳에 적용하여, 반복 코드를 제거할 수 있다. 컨트롤러, 서비스, 리포지토리 모든 곳에 적용할 수 있다.
