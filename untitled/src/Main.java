import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static List<Event> events = new ArrayList<>();
    private static Timer reminderTimer = new Timer(true);
    private static EventTableModel eventTableModel;
    private static JTable eventTable;

    public static void main(String[] args) {
        // Tworzenie głównego okna
        JFrame frame = new JFrame("Interaktywny Kalendarz Osobisty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Tworzenie panelu głównego
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Model tabeli
        eventTableModel = new EventTableModel(events);
        eventTable = new JTable(eventTableModel);
        JScrollPane scrollPane = new JScrollPane(eventTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Dodanie przycisków
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Dodaj wydarzenie");
        addButton.addActionListener(e -> addEvent(frame));
        buttonPanel.add(addButton);

        JButton editButton = new JButton("Edytuj wydarzenie");
        editButton.addActionListener(e -> editEvent(frame));
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("Usuń wydarzenie");
        deleteButton.addActionListener(e -> deleteEvent(frame));
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Dodanie panelu do okna
        frame.add(panel);

        // Wyświetlenie okna
        frame.setVisible(true);
    }

    private static void addEvent(JFrame frame) {
        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField timeField = new JTextField();

        Object[] fields = {
                "Nazwa wydarzenia:", nameField,
                "Data (YYYY-MM-DD):", dateField,
                "Czas (HH:MM):", timeField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Dodaj wydarzenie", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String date = dateField.getText();
            String time = timeField.getText();

            if (!name.isEmpty() && !date.isEmpty() && !time.isEmpty()) {
                Event event = new Event(name, date, time);
                events.add(event);
                eventTableModel.fireTableDataChanged();
                scheduleReminder(event);
                JOptionPane.showMessageDialog(frame, "Wydarzenie '" + name + "' zostało dodane!");
            } else {
                JOptionPane.showMessageDialog(frame, "Wszystkie pola muszą być wypełnione.");
            }
        }
    }

    private static void editEvent(JFrame frame) {
        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Wybierz wydarzenie do edytowania.");
            return;
        }

        Event selectedEvent = eventTableModel.getEventAt(selectedRow);

        JTextField nameField = new JTextField(selectedEvent.getName());
        JTextField dateField = new JTextField(selectedEvent.getDate());
        JTextField timeField = new JTextField(selectedEvent.getTime());

        Object[] fields = {
                "Nazwa wydarzenia:", nameField,
                "Data (YYYY-MM-DD):", dateField,
                "Czas (HH:MM):", timeField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Edytuj wydarzenie", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            selectedEvent.setName(nameField.getText());
            selectedEvent.setDate(dateField.getText());
            selectedEvent.setTime(timeField.getText());
            eventTableModel.fireTableDataChanged();
            JOptionPane.showMessageDialog(frame, "Wydarzenie zostało zaktualizowane!");
        }
    }

    private static void deleteEvent(JFrame frame) {
        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Wybierz wydarzenie do usunięcia.");
            return;
        }

        Event selectedEvent = eventTableModel.getEventAt(selectedRow);
        events.remove(selectedEvent);
        eventTableModel.fireTableDataChanged();
        JOptionPane.showMessageDialog(frame, "Wydarzenie zostało usunięte!");
    }

    private static void scheduleReminder(Event event) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime eventTime = LocalDateTime.parse(event.getDate() + " " + event.getTime(), formatter);
            LocalDateTime reminderTime = eventTime.minusMinutes(5);

            TimerTask reminderTask = new TimerTask() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, "Przypomnienie: " + event.getName() + " za 5 minut!");
                }
            };

            reminderTimer.schedule(reminderTask, java.sql.Timestamp.valueOf(reminderTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
