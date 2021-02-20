package com.example.inflearnthejavatest;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * - @DisplayNameGeneration
 * - @DisplayName: 권장
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {
    /**
     * JUnit4: 클래스와 메서드 모두 public 이었어야 함
     * JUnit5: 상관 없음, 자바의 reflection 을 활용하여 default 나 private 에도 접근 가능
     */
    @Test
    @DisplayName("스터디 만들기 😀")
    void create_new_study() {
        // assertTimeout: 시간 오래 걸릴 수 있음
        // assertTimeoutPreemtively: 조심해서 사용해야 함 (cf. ThreadLocal)
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

        // assertThrows
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();
        assertEquals("limit은 0보다 커야 한다.", message);

        System.out.println("create");

        /**
         * assertAll
         * - 연관된 테스트를 다 묶어줌
         * - 뒤에서 테스트가 깨지는 거 다 알 수 있음
         */
        Study study = new Study(-10);
        assertAll(
                () -> assertNotNull(study),
                /**
                 * assertEquals의 세 번째 인자
                 * - new Supplier()
                 * - 람다 () -> {}: 테스트가 실패했을 때만 연산 수행하므로 성능에 유리함
                 * - 문자열 "": 테스트 결과에 상관없이 문자열 연산 수행함
                 */
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                        () -> "스터디를 처음 만들면 " + StudyStatus.DRAFT + " 상태여야 한다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
        );

    }

    @Test
    @Disabled
    void create_new_study_again() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create1");
    }

    /**
     * BeforeAll, AfterAll: 모든 메서드를 실행하기 전후에 딱 한 번만 실행됨
     * - static void
     * - static 사용, private 불가능, default 가능, return type 없어야 함
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    /**
     * BeforeEach, AfterEach: 각 메서드를 실행하기 전후에 실행됨
     * - static일 필요는 없음
     */
    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }
}