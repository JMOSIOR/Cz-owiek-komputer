import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventTableModelTest {

    private List<Event> events;
    private EventTableModel eventTableModel;

    @Before
    public void setUp() {
        events = new ArrayList<>();
        events.add(new Event("Event 1", "2024-07-07", "12:00"));
        events.add(new Event("Event 2", "2024-07-08", "13:00"));
        eventTableModel = new EventTableModel(events);
    }

    @Test
    public void testGetRowCount() {
        assertEquals(2, eventTableModel.getRowCount());
    }

    @Test
    public void testGetColumnCount() {
        assertEquals(3, eventTableModel.getColumnCount());
    }

    @Test
    public void testGetValueAt() {
        assertEquals("Event 1", eventTableModel.getValueAt(0, 0));
        assertEquals("2024-07-07", eventTableModel.getValueAt(0, 1));
        assertEquals("12:00", eventTableModel.getValueAt(0, 2));

        assertEquals("Event 2", eventTableModel.getValueAt(1, 0));
        assertEquals("2024-07-08", eventTableModel.getValueAt(1, 1));
        assertEquals("13:00", eventTableModel.getValueAt(1, 2));
    }

    @Test
    public void testGetColumnName() {
        assertEquals("Nazwa", eventTableModel.getColumnName(0));
        assertEquals("Data", eventTableModel.getColumnName(1));
        assertEquals("Czas", eventTableModel.getColumnName(2));
    }

    @Test
    public void testGetEventAt() {
        Event event = eventTableModel.getEventAt(0);
        assertEquals("Event 1", event.getName());
        assertEquals("2024-07-07", event.getDate());
        assertEquals("12:00", event.getTime());
    }
}
