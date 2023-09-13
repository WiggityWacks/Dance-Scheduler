package persistence;

import model.DanceClass;
import model.DanceType;
import model.Dancer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkDanceClass(String name, String weekday, List<Dancer> dancers, DanceType danceType, DanceClass danceClass) {
        assertEquals(name, danceClass.getClassName());
        assertEquals(weekday, danceClass.getClassDate());
        checkDancers(dancers, danceClass.getDancers());
        checkDanceType(danceType, danceClass.getDanceType());
    }

    private void checkDanceType(DanceType danceType, DanceType danceType1) {
        assertEquals(danceType.getName(), danceType1.getName());
    }

    private void checkDancers(List<Dancer> dancers, List<Dancer> dancers1) {
        int i = 0;
        for (Dancer d: dancers) {
            checkDancer(d, dancers1.get(i));
            i++;
        }
    }

    private void checkDancer(Dancer d, Dancer dancer) {
        assertEquals(d.getName(), dancer.getName());
        assertEquals(d.getAge(), dancer.getAge());
    }
}