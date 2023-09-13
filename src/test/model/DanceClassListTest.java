package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DanceClassListTest {
    DanceClassList testStudioSchedule;
    DanceClass testClassSunday;
    DanceClass testClassMonday1;
    DanceClass testClassMonday2;
    DanceClass testClassTuesday;
    DanceClass testClassWednesday;
    DanceClass testClassThursday;
    DanceClass testClassFriday;
    DanceClass testClassSaturday;
    DanceType tech;
    DanceType ballet;
    DanceType hipHop;
    DanceType zumba;
    DanceType tap;

    @BeforeEach
    void setup(){
        testStudioSchedule = new DanceClassList();
        testClassMonday1 = new DanceClass("Tech Class", "Monday");
        testClassMonday2 = new DanceClass("Zumba Class", "Monday");
        testClassTuesday = new DanceClass("Zumba Class", "Tuesday");
        testClassWednesday = new DanceClass("Hip Hop Class", "Wednesday");
        testClassThursday = new DanceClass("Ballet Class", "Thursday");
        testClassFriday = new DanceClass("Hip Hop Class", "Friday");
        testClassSaturday = new DanceClass("Tap Class", "Saturday");
        testClassSunday = new DanceClass("Ballet Class", "Sunday");
        tech = new DanceType("Tech");
        zumba = new DanceType("Zumba");
        ballet = new DanceType("Ballet");
        hipHop = new DanceType("Hip Hop");
        tap = new DanceType("Tap");
        testClassMonday1.setDanceType(tech);
        testClassMonday2.setDanceType(zumba);
        testClassTuesday.setDanceType(zumba);
        testClassWednesday.setDanceType(hipHop);
        testClassThursday.setDanceType(ballet);
        testClassFriday.setDanceType(hipHop);
        testClassSaturday.setDanceType(tap);
        testClassSunday.setDanceType(ballet);

    }

    @Test
    void testConstructor(){
        assertEquals(0, testStudioSchedule.getDailySchedule("Monday").size());
        assertEquals(0, testStudioSchedule.getWeeklySchedule().size());
        assertEquals(0, testStudioSchedule.getDanceTypeList(hipHop).size());
    }

    @Test
    void testGetDailySchedule() {
        testStudioSchedule.addClass(testClassMonday1);
        testStudioSchedule.addClass(testClassMonday2);
        testStudioSchedule.addClass(testClassTuesday);
        testStudioSchedule.addClass(testClassWednesday);
        testStudioSchedule.addClass(testClassThursday);
        testStudioSchedule.addClass(testClassFriday);
        testStudioSchedule.addClass(testClassSaturday);
        testStudioSchedule.addClass(testClassSunday);
        assertEquals(2, testStudioSchedule.getDailySchedule("Monday").size());
        assertEquals(1, testStudioSchedule.getDailySchedule("Tuesday").size());
        assertEquals(1, testStudioSchedule.getDailySchedule("Wednesday").size());
        assertEquals(1, testStudioSchedule.getDailySchedule("Thursday").size());
        assertEquals(1, testStudioSchedule.getDailySchedule("Friday").size());
        assertEquals(1, testStudioSchedule.getDailySchedule("Saturday").size());
        assertEquals(1, testStudioSchedule.getDailySchedule("Sunday").size());
    }

    @Test
    void testGetWeeklySchedule() {
        testStudioSchedule.addClass(testClassMonday1);
        testStudioSchedule.addClass(testClassMonday2);
        testStudioSchedule.addClass(testClassTuesday);
        testStudioSchedule.addClass(testClassWednesday);
        testStudioSchedule.addClass(testClassThursday);
        testStudioSchedule.addClass(testClassFriday);
        testStudioSchedule.addClass(testClassSaturday);
        testStudioSchedule.addClass(testClassSunday);
        assertEquals(8, testStudioSchedule.getWeeklySchedule().size());
    }

    @Test
    void testGetDanceList() {
        testStudioSchedule.addClass(testClassMonday1);
        testStudioSchedule.addClass(testClassMonday2);
        testStudioSchedule.addClass(testClassTuesday);
        testStudioSchedule.addClass(testClassWednesday);
        testStudioSchedule.addClass(testClassThursday);
        testStudioSchedule.addClass(testClassFriday);
        testStudioSchedule.addClass(testClassSaturday);
        testStudioSchedule.addClass(testClassSunday);
        assertEquals(1, testStudioSchedule.getDanceTypeList(tech).size());
        assertEquals(testClassMonday1, testStudioSchedule.getClass(0));
        assertEquals(2, testStudioSchedule.getDanceTypeList(hipHop).size());
        assertEquals(2, testStudioSchedule.getDanceTypeList(ballet).size());
        assertEquals(2, testStudioSchedule.getDanceTypeList(zumba).size());
        assertEquals(1, testStudioSchedule.getDanceTypeList(tap).size());
    }

    @Test
    void testRemoveClass() {
        testStudioSchedule.addClass(testClassMonday1);
        testStudioSchedule.removeClass(0);
        assertEquals(0, testStudioSchedule.getWeeklySchedule().size());
    }

    @Test
    void testRemoveClassCorrectIndex() {
        testStudioSchedule.addClass(testClassMonday1);
        testStudioSchedule.addClass(testClassMonday2);
        testStudioSchedule.removeClass(1);
        assertEquals(1, testStudioSchedule.getWeeklySchedule().size());
        assertEquals(testClassMonday1, testStudioSchedule.getClass(0));
    }

    @Test
    void testRemoveMultipleClasses() {
        testStudioSchedule.addClass(testClassMonday1);
        testStudioSchedule.addClass(testClassMonday2);
        testStudioSchedule.removeClass(1);
        testStudioSchedule.removeClass(0);
        assertEquals(0, testStudioSchedule.getWeeklySchedule().size());
    }

    @Test
    void testScheduleToString() {
        testStudioSchedule.addClass(testClassMonday1);
        List<String> testSchedule = testStudioSchedule.scheduleToString(testStudioSchedule.getWeeklySchedule());
        assertEquals(1, testSchedule.size());
        assertEquals("Tech Class", testSchedule.get(0));

        testStudioSchedule.addClass(testClassMonday2);
        List<String> testSchedule2 = testStudioSchedule.scheduleToString(testStudioSchedule.getWeeklySchedule());
        assertEquals(2, testSchedule2.size());
        assertEquals("Zumba Class", testSchedule2.get(1));

    }

}
