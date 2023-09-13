package persistence;

import model.DanceClass;
import model.DanceClassList;

import model.DanceType;
import model.Dancer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DanceClassList dcl = reader.read();
            fail("IO Exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDanceClassList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDanceClassList.json");
        try {
            DanceClassList dcl = reader.read();
            assertEquals(0, dcl.getWeeklySchedule().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDanceClassList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDanceClassList.json");
        List<Dancer> dancers1 = new ArrayList<>();
        List<Dancer> dancers2 = new ArrayList<>();
        try {
            DanceClassList dcl = reader.read();
            List<DanceClass> classes = dcl.getWeeklySchedule();
            assertEquals(2, classes.size());
            dancers1.add(new Dancer("Jason", 19));
            dancers2.add(new Dancer("Marcus", 45));
            checkDanceClass("Tech Class", "friday", dancers1, new DanceType("Hip Hop"), classes.get(0));
            checkDanceClass("Sexy Street", "saturday", dancers2, new DanceType("House"), classes.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}


