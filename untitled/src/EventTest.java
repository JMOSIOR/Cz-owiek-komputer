import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {

    @Test
    public void testEventCreation() {
        Event event = new Event("Test Event", "2024-07-07", "12:00");
        assertEquals("Test Event", event.getName());
        assertEquals("2024-07-07", event.getDate());
        assertEquals("12:00", event.getTime());
    }

    @Test
    public void testSetters() {
        Event event = new Event("Test Event", "2024-07-07", "12:00");
        event.setName("Updated Event");
        event.setDate("2024-08-08");
        event.setTime("14:00");

        assertEquals("Updated Event", event.getName());
        assertEquals("2024-08-08", event.getDate());
        assertEquals("14:00", event.getTime());
    }

    @Test
    public void testToString() {
        Event event = new Event("Test Event", "2024-07-07", "12:00");
        String expected = "Test Event - 2024-07-07 12:00";
        assertEquals(expected, event.toString());
    }
}
