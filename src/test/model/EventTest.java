package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
	private Date d;
    private Event f;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Test Event");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
        f = new Event("Test Event");
	}
	
	@Test
	public void testEvent() {
		assertEquals("Test Event", e.getDescription());
		assertEquals(d, e.getDate());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Test Event", e.toString());
	}

    @Test
    public void testHashCode() {
        assertTrue(e.hashCode()==f.hashCode());
    }

    @Test
    public void testEquals() {
        Object a = null;
        int b = 13;
        assertFalse(e.equals(a));
        assertFalse(e.equals(b));
    }
}

