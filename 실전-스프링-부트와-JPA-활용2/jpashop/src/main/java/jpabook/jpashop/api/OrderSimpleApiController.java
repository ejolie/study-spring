package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    /**
     * V1. Entity를 직접 노출
     * - 양방향 연관 관계로 무한 루프 발생
     *   -> 한 쪽에는 @JsonIgnore 써줘야 함
     * - 지연 로딩(fetch = LAZY) 문제
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

    /**
     * V2. 엔티티를 DTO로 변환
     * -> N + 1 문제
     * - 쿼리가 총 1 + N + N번 실행된다. (v1와 쿼리수 결과는 같다.)
     *  - ORDER 조회 1번 -> 조회 결과 수 N
     *  - ORDER -> MEMBER 지연 로딩 조회 N
     *  - ORDER -> DELIVERY 지연 로딩 조회 N
     *
     * -> 즉, ORDER 조회 결과가 2개면 최악의 경우 1 + 2 + 2번 실행된다.
     *  - 지연 로딩은 기본적으로 N번이긴 한데 DB 쿼리로 조회하는 것이 아니라
     *    영속성 컨텍스트에서 조회하므로, 이미 조회된 경우 쿼리를 생략한다.
     */
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        List<SimpleOrderDto> result = orders.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());

        return result;
    }

    /**
     * V3. 엔티티를 DTO로 변환 + Fetch Join으로 성능 최적화
     * - 쿼리 1번 실행
     * -> 대부분의 성능 이슈가 해결된다.
     */
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();

        List<SimpleOrderDto> result = orders.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());

        return result;
    }

    /**
     * V4. JPA에서 DTO로 바로 조회
     */
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); // LAZY 초기화 (DB Member Query)
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); // LAZY 초기화
        }
    }
}
