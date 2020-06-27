package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    /**
     * 문제
     * 1. 양방향 연관 관계로 무한 루프 발생
     *   -> 한 쪽에는 @JsonIgnore 써줘야 함
     * 2. 지연 로딩(fetch = LAZY) 문제
     *   - Order에서 프록시 맴버 객체로 갖고 있다가 나중에 진짜 멤버 객체 정보 가져옴
     *   -> Jackson DataType Hibernate5 모듈 추가
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
//        for (Order order : all) {
//            order.getMember().getName(); // LAZY 강제 초기화
//            order.getDelivery().getAddress(); // LAZY 강제 초기화
//        }
        return all;
    }
}
