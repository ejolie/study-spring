# [인프런] 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술

https://www.inflearn.com/course/스프링-입문-스프링부트/dashboard

## 프로젝트 환경설정

### 라이브러리 살펴보기

#### 스프링 부트 라이브러리

- spring-boot-starter-web
  - spring-boot-starter-tomcat
  - spring-webmvc
- spring-boot-starter-thymeleaf
- spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
  - spring-boot
    - spring-core
  - spring-boot-starter-logging
    - logback: logging 구현체
    - slf4j: logging facade, 로깅 추상 레이어, interface

#### 테스트 라이브러리

- spring-boot-starter-test
  - junit
  - mockito
  - assertj
  - spring-test



### View 환경설정

- Welcome Page: `static/index.html`
- thymeleaf 템플릿 엔진 사용 시 동작 환경
  - 컨트롤러에서 리턴 값으로 반환한 view 이름 -> ViewResolver가 화면을 찾아 처리한다.
  - `resources:templates/` + {view 이름} + `.html`



### 빌드하고 실행하기

```shell
./gradlew build
cd build/libs
java -jar hello-spring-0.0.1-SNAPSHOT.jar
```



## 스프링 웹 개발 기초

### 정적 컨텐츠

- static 폴더에 있는 정적 컨텐츠 경로 입력 시 (예: `localhost:8080/hello-static.html`)
  1. **해당 경로로 맵핑된 컨트롤러가 있는지 먼저 확인한다.**
  2. 없다면 `resources:static/hello-static.html` 리턴



### MVC와 템플릿 엔진

- MVC: Model, View, Controller
  - cf. 과거 Model 1: View(JSP) 안에서 모든 일을 다 함



### API

- `@ResponseBody`
  - HTTP Response Body 에 내용을 직접 작성하게 해준다.
  - **ViewResolver 대신 HttpMessageConverter 가 동작한다.**
    -  클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보를 조합해 적합한 HttpMessageConverter가 선택된다.
  - 기본 문자 처리: StringHttpMessageConverter
  - 기본 객체 처리: MappingJackson2HttpMessageConverter
    - cf. JSON 처리 대표 라이브러리
      - Jackson: 스프링 기본
      - Gson
  - 기타 여러 HttpMessageConverter가 기본으로 등록되어 있다.



## 회원관리 예제 - 웹 MVC 개발

- 일반적인 웹 애플리케이션 계층 구조
  - 컨트롤러: 웹 MVC의 컨트롤러 역할
  - 서비스: 핵심 비즈니스 로직 구현
  - 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
  - 도메인: 비즈니스 도메임 객체
- **테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.**
  - `@AfterEach`: 각 테스트 실행 후 호출
  - `@BeforeEach`: 각 테스트 실행 전 호출
- **자바 빈 프로퍼티 규약**
  - 객체의 데이터는 private 으로 보호하고, 접근할 때는 공개된 getter/setter 메서드를 통해서 접근한다.

- Domain != VO

  - VO

    1. 단순히 데이터 값을 전달하기 위한 용도로 사용되는 객체

       DTO와 같은 뜻으로 혼용해서 사용. <u>DTO = 데이터를 전송하는 목적으로 사용하는 객체</u>

    2. <u>도메인 주도 설계에서 이야기하는 값 객체(Value Object)</u>

- Repository := DAO (비슷함)

  - Repository: 엔티티 객체를 보관하고 관리하는 저장소
  - DAO: 데이터에 접근하도록 DB 접근 관련 로직을 모아둔 객체



## 스프링 빈과 의존 관계

스프링 빈을 등록하는 두 가지 방법

1. 컴포넌트 스캔과 자동 의존관계 설정
2. 자바 코드로 직접 스프링 빈 등록하기



### 컴포넌트 스캔과 자동 의존관계 설정

- **생성자에 `@Autowired` 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다. 이렇게 객체 의존관계를 (개발자가 직접 넣어주는 게 아니라) 외부(스프링)에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입이라 한다.**
  - 생성자가 1개만 있으면 `@Autowired` 를 생략할 수 있다.
- 컴포넌트 스캔 원리
  - `@Component` 애노테이션이 있으면 스프링 빈으로 자동 등록된다.
  - `@Controller`, `@Service`, `@Repository` 모두 `@Component` 를 포함하는 애노테이션이다.



### 자바 코드로 직접 스프링 빈 등록하기

- `@Configuration`

- DI 방법

  1. 필드 주입

  2. setter 주입

  3. 생성자 주입

- **의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.**

  cf. 테스트 실행시 스프링 컨테이너의 도움 없이 MemberService가 가지고 있는 여러 Repository를 자유롭게 변경하면서 테스트 할 수 있어야 한다. 그런데 **필드 주입을 사용하면, 스프링 컨테이너가 없을 때 의존하는 객체를 변경할 수 있는 방법이 없다.** **반면 생성자 주입에서는 스프링 컨테이너의 도움 없이 직접 `new MemberService(new XxxRepository)`와 같이 원하는 객체를 변경해서 테스트하거나 실행할 수 있다.**

- 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드: 주로 컴포넌트 스캔을 사용
- **정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 하는 경우: 자바 설정 코드를 사용**
- 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 `@Autowired` 가 동작하지 않는다.



## 스프링 DB 접근 기술

### 스프링 DB 접근 기술

- 순수 Jdbc
- 스프링 JdbcTemplate
- JPA
- 스프링 데이터 JPA



### 순수 Jdbc

- JDBC API로 직접 코딩



### 스프링 통합 테스트

- 스프링 컨테이너와 DB까지 연결한 통합 테스트
- **`@SpringBootTest`: 스프링 컨테이너와 테스트를 함께 실행**
- `@Transactional`: 테스트 시작 전에 트랜잭션 실행, 테스트 완료 후 롤백



### 스프링 JdbcTemplate

- 스프링 JdbcTemplate & Mybatis: JDBC API의 반복 코드를 대부분 제거, 하지만 SQL은 직접 작성해야 함



### JPA

- 기존 반복 코드는 물론, 기본적인 SQL도 JPA가 대신 만들어 실행해줌
- SQL과 데이터 중심의 설계 -> 객체 중심의 설계로 패러다임 전환
- `MemberService`에 `@Transactional` 추가
  - **해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커밋. 런타임 예외 발생 시 롤백**
  - **JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.**
- JPA: 인터페이스
- Hibernate: 구현체



### 스프링 데이터 JPA

- 스프링 데이터 JPA: JPA를 편리하게 사용하도록 도와주는 기술
- **리포지토리에 구현 클래스 없이 인터페이스만으로 개발 가능**
  - 스프링 데이터 JPA가 구현체를 알아서 만들고 스프링 빈으로 등록해줌



## AOP(Aspect-Oriented Programming)

- **공통 관심사항(cross-cutting concern)** vs. 핵심 관심사항(core concern)을 분리

- 원하는 곳에 공통 관심사항을 쉽게 적용

- Proxy 라는 가상의 빈 인스턴스로 작동됨

  ```
  프록시 helloController -> helloController
  -> 프록시 memberService -> memberService
  -> 프록시 memberRepository -> memberRepository
  ```

- **cf. AOP vs. Interceptor** 차이?

  둘다 공통 관심사를 해결하기 위한 목적을 갖고 있음

  - Interceptor: 웹과 관련된 기능을 해결하는 것에 특화됨. 특히 HTTP Request/Response 정보를 받아서 해결함. 웹과 관련 없는 기술에 대해서는 범용성이 없음
  - AOP: 범용성이 큼 (예: `@Transactional`, `@Async`)

