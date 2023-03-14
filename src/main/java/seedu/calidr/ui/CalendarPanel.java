package seedu.calidr.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.EntryViewBase;
import com.calendarfx.view.MonthView;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import seedu.calidr.commons.core.LogsCenter;
import seedu.calidr.commons.util.TaskEntryUtil;
import seedu.calidr.model.ReadOnlyTaskList;
import seedu.calidr.model.TaskEntry;
import seedu.calidr.model.task.Event;
import seedu.calidr.model.task.Task;
import seedu.calidr.model.task.ToDo;

/**
 * Panel containing the CalendarFX DetailedWeekView.
 */
public class CalendarPanel extends UiPart<Region> {

    private static final String FXML = "CalendarPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    private final CalendarSource calendarSource = new CalendarSource("My Calendars");

    private final Calendar<TaskEntry> calendarTodos = new Calendar<>("Todos");

    private final Calendar<TaskEntry> calendarEvents = new Calendar<>("Events");

    private final Map<Class<? extends Task>, Calendar<TaskEntry>> taskEntryCalendarMap = new HashMap<>();

    @FXML
    private MonthView calendarView; // TODO: Command to navigate different months

    private Thread updateTimeThread;

    /**
     * Instantiates a new CalendarPanel.
     */
    public CalendarPanel() {
        super(FXML);

        calendarTodos.setReadOnly(true);
        calendarEvents.setReadOnly(true);
        calendarTodos.setStyle(Calendar.Style.STYLE1);
        calendarEvents.setStyle(Calendar.Style.STYLE2);

        taskEntryCalendarMap.put(ToDo.class, calendarTodos);
        taskEntryCalendarMap.put(Event.class, calendarEvents);

        calendarSource.getCalendars().addAll(calendarTodos, calendarEvents);

        calendarView.setEntryContextMenuCallback(param -> {
            EntryViewBase<?> entryView = param.getEntryView();
            TaskEntry entry = (TaskEntry) entryView.getEntry();

            ContextMenu contextMenu = new ContextMenu();

            MenuItem informationItem = new MenuItem("Information");
            informationItem.setOnAction(evt ->
                    showInformationDialog(entry,
                            this.getRoot()
                                    .getScene()
                                    .getWindow()
                    )
            );
            contextMenu.getItems().add(informationItem);

            return contextMenu;
        });

        //calendarView.setEntryDetailsPopOverContentCallback(new TaskEntryPopOverContentProvider());

        calendarView.addEventFilter(MouseEvent.MOUSE_CLICKED, javafx.event.Event::consume);
        calendarView.getCalendarSources().add(calendarSource);
        calendarView.setBackground(Background.fill(Paint.valueOf("#ffffff")));
        calendarView.setRequestedTime(LocalTime.now());

        spawnUpdateThread(); // update the calendar's "current time" every 10 seconds
    }

    private void showInformationDialog(TaskEntry entry, Window window) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(window);
        VBox dialogVbox = new VBox(10);
        dialogVbox.setPadding(new javafx.geometry.Insets(10));
        ArrayList<String> information = new ArrayList<>();
        information.add(entry.getTitle());
        information.add(entry.getInterval().getStartDate().toString());
        information.add(entry.getInterval().getEndDate().toString());
        information.add("Is done: " + entry.getIsDone());
        information.add("Priority: " + entry.getPriority());
        dialogVbox.getChildren().addAll(
                information.stream().map(Text::new).peek(
                        text -> text.wrappingWidthProperty()
                                .bind(dialogVbox.widthProperty())
                ).toArray(Node[]::new)
        );
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
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
        taskEntryCalendarMap.values().forEach(Calendar::clear);
        taskList.getTaskList().forEach(task -> {
            Class<? extends Task> taskClass = task.getClass();
            if (taskEntryCalendarMap.containsKey(taskClass)) {
                Calendar<TaskEntry> calendar = taskEntryCalendarMap.get(taskClass);
                calendar.addEntry(TaskEntryUtil.convert(task));
            }
        });
    }
}
