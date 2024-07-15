import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EventTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nazwa", "Data", "Czas"};
    private final List<Event> events;

    public EventTableModel(List<Event> events) {
        this.events = events;
    }

    @Override
    public int getRowCount() {
        return events.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Event event = events.get(rowIndex);
        switch (columnIndex) {
            case 0: return event.getName();
            case 1: return event.getDate();
            case 2: return event.getTime();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Event getEventAt(int rowIndex) {
        return events.get(rowIndex);
    }
}
