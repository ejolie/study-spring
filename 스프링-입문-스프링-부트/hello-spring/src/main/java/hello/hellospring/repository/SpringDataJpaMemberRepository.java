package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository extends 하는 인터페이스 하나 만들어놓으면,
 * Spring Data JPA가 구현체를 알아서 만들고 스프링 빈에 등록한다.
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
