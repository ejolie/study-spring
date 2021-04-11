package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

    /**
     * 스프링의 객체 지향
     * - 다형성: 인터페이스는 그대로 두고, 구현체 바꿔치기 쉽게 가능
     *   -> 스프링 컨테이너가 DI를 통해 "기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경해준다."
     * - 애플리케이션의 구현 코드는 그대로 두고, 조립 코드(assembly)만 손 대면 됨
     * - 개방-폐쇄 원칙(OCP, Open-Closed Principle): "확장에는 열려있고, 수정/변경에는 닫혀 있다."
     *   -> 객체 지향의 다형성을 잘 활용하면 기능을 완전히 변경해도 애플리케이션 전체를 수정할 필요 없다.
     */
//    @Bean
//    public MemberRepository memberRepository() {
//        // return new MemoryMemberRepository();
//        // return new JdbcMemberRepository(dataSource);
//        // return new JdbcTemplateMemberRepository(dataSource);
//        // return new JpaMemberRepository(em);
//    }
}
