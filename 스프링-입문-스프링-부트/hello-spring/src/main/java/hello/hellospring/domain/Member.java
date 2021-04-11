package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * JPA: 인터페이스
 * Hibernate: 구현체
 */
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity: DB가 생성해줌
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
