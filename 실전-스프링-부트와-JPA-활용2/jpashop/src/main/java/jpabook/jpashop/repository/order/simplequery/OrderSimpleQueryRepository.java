package jpabook.jpashop.repository.order.simplequery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    /**
     * V4
     * - SELECT 절에서 원하는 데이터를 직접 선택하므로
     *   DB -> 애플리케이션 네트워크 용량 최적화 (하지만 생각보다 미비)
     * - V3에 비해 재사용성 떨어짐
     *   API 스펙에 맞춘 코드가 Repository에 들어가는 단점
     *   Repository는 Entity를 조회하는데 사용되어야 함
     *   -> 별도 package로 관리할 수 있음
     */
    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                "select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderSimpleQueryDto.class
        ).getResultList();
    }
}
