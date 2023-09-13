package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DanceClassTest {
    DanceClass testClass;

    @BeforeEach
    void setup() {
        testClass = new DanceClass("Test Class", "Monday");
    }

    @Test
    void testConstructor() {
        assertEquals("Test Class", testClass.getClassName());
        assertEquals("Monday", testClass.getClassDate());
        assertNull(testClass.getDanceType());
        assertEquals(0, testClass.getDancers().size());
    }

    @Test
    void testSetNameOne() {
        testClass.setClassName("Dummy Class");
        assertEquals("Dummy Class", testClass.getClassName());
    }

    @Test
    void testSetNameMultiple() {
        testClass.setClassName("Dummy Class");
        testClass.setClassName("Dummy Class 2");
        assertEquals("Dummy Class 2", testClass.getClassName());
    }

    @Test
    void testSetClassDateOne() {
        testClass.setClassDate("Wednesday");
        assertEquals("Wednesday", testClass.getClassDate());
    }

    @Test
    void testSetClassDateWeek() {
        testClass.setClassDate("Tuesday");
        assertEquals("Tuesday", testClass.getClassDate());
        testClass.setClassDate("Monday");
        assertEquals("Monday", testClass.getClassDate());
        testClass.setClassDate("Thursday");
        assertEquals("Thursday", testClass.getClassDate());
        testClass.setClassDate("Friday");
        assertEquals("Friday", testClass.getClassDate());
        testClass.setClassDate("Saturday");
        assertEquals("Saturday", testClass.getClassDate());
        testClass.setClassDate("Sunday");
        assertEquals("Sunday", testClass.getClassDate());
    }

    @Test
    void addOneDancer() {
        Dancer testDancer = new Dancer("Joaquin", 19);
        testClass.addDancer(testDancer);
        assertEquals(1, testClass.getDancers().size());
        assertEquals(testDancer, testClass.getDancers().get(0));
    }

    @Test
    void addMultipleDancer() {
        Dancer testDancer1 = new Dancer("Joaquin", 19);
        Dancer testDancer2 = new Dancer("Aaron", 10);
        testClass.addDancer(testDancer1);
        testClass.addDancer(testDancer2);
        assertEquals(2, testClass.getDancers().size());
        assertEquals(testDancer1, testClass.getDancers().get(0));
        assertEquals(testDancer2, testClass.getDancers().get(1));
    }
}
