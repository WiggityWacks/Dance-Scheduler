package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DanceTypeTest {
    DanceType testDanceType;

    @BeforeEach
    void setup() {
        testDanceType = new DanceType("Hip Hop");
    }

    @Test
    void testConstructor() {
        assertEquals("Hip Hop", testDanceType.getName());
    }

    @Test
    void testSet() {
        testDanceType.setName("Ballet");
        assertEquals("Ballet", testDanceType.getName());
    }

}
