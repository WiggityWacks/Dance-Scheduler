package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DancerTest {
    Dancer testDancer;

    @BeforeEach
    void setup() {
        testDancer = new Dancer("Tommy", 5);
    }

    @Test
    void testConstructor(){
        assertEquals("Tommy", testDancer.getName());
        assertEquals(5, testDancer.getAge());
    }
}
