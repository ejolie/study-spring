package com.example.inflearnthejavatest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {
    /**
     * JUnit4: 클래스와 메서드 모두 public 이었어야 함
     * JUnit5: 상관 없음, 자바의 reflection 활용하여 default이나 private에도 접근 가능
     */
    @Test
    void create() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }

    @Test
    @Disabled
    void create1() {
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