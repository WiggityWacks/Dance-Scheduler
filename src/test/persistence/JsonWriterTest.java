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


public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            DanceClassList dcl = new DanceClassList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDanceClassList() {
        try {
            DanceClassList dcl = new DanceClassList();
            JsonWriter writer = new JsonWriter(("./data/testWriterEmptyDanceClassList.json"));
            writer.open();
            writer.write(dcl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDanceClassList.json");
            dcl = reader.read();
            assertEquals(0, dcl.getWeeklySchedule().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDanceClassList() {
        List<Dancer> dancers1 = new ArrayList<>();
        List<Dancer> dancers2 = new ArrayList<>();
        try {
            DanceClassList dcl = new DanceClassList();
            DanceClass dc1 = new DanceClass("Tech Class", "friday");
            DanceClass dc2 = new DanceClass("Sexy Street", "saturday");
            dc1.addDancer(new Dancer("Jason", 19));
            dc1.setDanceType(new DanceType("Hip Hop"));
            dcl.addClass(dc1);
            dc2.addDancer(new Dancer("Marcus", 45));
            dc2.setDanceType(new DanceType("House"));
            dcl.addClass(dc2);
            JsonWriter writer = new JsonWriter(("./data/testWriterGeneralDanceClassList.json"));
            writer.open();
            writer.write(dcl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDanceClassList.json");
            dcl = reader.read();
            List<DanceClass> classes = dcl.getWeeklySchedule();
            assertEquals(2, dcl.getWeeklySchedule().size());
            dancers1.add(new Dancer("Jason", 19));
            dancers2.add(new Dancer("Marcus", 45));
            checkDanceClass("Tech Class", "friday", dancers1, new DanceType("Hip Hop"), classes.get(0));
            checkDanceClass("Sexy Street", "saturday", dancers2, new DanceType("House"), classes.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
