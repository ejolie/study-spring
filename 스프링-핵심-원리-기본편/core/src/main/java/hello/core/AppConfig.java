package hello.core;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * - 관심사의 분리
 * - AppConfig 의 등장으로 애플리케이션이 크게
 * 1. 객체를 생성하고 구성하는 영역과
 * 2. 사용 영역으로 분리된다.
 */
public class AppConfig {
    
    private MemoryMemberRepository memberRepository() { // 역할
        return new MemoryMemberRepository(); // 구현
    }

    private RateDiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
}
