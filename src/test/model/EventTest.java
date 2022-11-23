package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Sensor open at door");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", e.getDescription());

        // e.getDate().equals(d) does not work because there would be a small lag, so the value
        //     on the right side of the inequality should be carefully set
        assertTrue(Math.abs(e.getDate().getTime() - d.getTime()) < 80);
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
    }
}
