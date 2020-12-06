package test;

import org.junit.jupiter.api.*;

public class JupiterExample {

    @BeforeAll
    static void setUpBeforeAll(){
        System.out.println("Before all");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before each");

    }

    @Test
    void test1() {
        System.out.println("Test1");
    }

    @Test
    void test2() {
        System.out.println("Test2");
    }

    @Test
    void test3() {
        System.out.println("Test3");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }

    @AfterAll
    static void tearDownAfterAll() {
        System.out.println("After All");
    }
}
