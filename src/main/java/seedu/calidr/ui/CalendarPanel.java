package seedu.calidr.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.DetailedWeekView;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import seedu.calidr.commons.core.LogsCenter;
import seedu.calidr.commons.util.TaskEntryUtil;
import seedu.calidr.model.ReadOnlyTaskList;
import seedu.calidr.model.TaskEntry;


/**
 * Panel containing the CalendarFX DetailedWeekView.
 */
public class CalendarPanel extends UiPart<Region> {

    private static final String FXML = "CalendarPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private final CalendarSource calendarSource = new CalendarSource("My Calendars");

    private final Calendar<TaskEntry> calendar = new Calendar<>("My Calendar");

    @FXML
    private DetailedWeekView calendarView; // TODO: Command to navigate different months

    private Thread updateTimeThread;

    /**
     * Instantiates a new CalendarPanel.
     */
    public CalendarPanel() {
        super(FXML);
        calendar.setReadOnly(true);
        calendar.setStyle(Calendar.Style.STYLE1); // TODO: Command to chanege style
        calendarSource.getCalendars().add(calendar);
        calendarView.addEventFilter(MouseEvent.MOUSE_CLICKED, Event::consume);
        calendarView.getCalendarSources().add(calendarSource);
        calendarView.setBackground(Background.fill(Paint.valueOf("#ffffff")));
        calendarView.setRequestedTime(LocalTime.now());

        spawnUpdateThread(); // update the calendar's "current time" every 10 seconds
    }

    private void spawnUpdateThread() {
        updateTimeThread = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    calendarView.setToday(LocalDate.now());
                    calendarView.setTime(LocalTime.now());
                });

                try {
                    // update every 10 seconds
                    //noinspection BusyWait
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
    }

    /**
     * Update the calendar entries with the given task list.
     *
     * @param taskList the task list
     */
    public void updateCalendar(ReadOnlyTaskList taskList) {
        // TODO: Asynchronous / lazy loading
        calendar.clear();
        calendar.addEntries(taskList.getTaskList()
                .stream().map(TaskEntryUtil::convert)
                .collect(Collectors.toUnmodifiableList()));
    }
}
