package com.andd.book.book;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperBkTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all method");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all method");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Inside before each method");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Inside the after each method");
    }

    @Test
    public void testMethod1() {
        System.out.println("My first test method");
    }

    @Test
    public void testMethod2() {
        System.out.println("My second test method");
    }
}