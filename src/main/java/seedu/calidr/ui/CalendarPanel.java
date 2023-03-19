package seedu.calidr.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.DateControl;
import com.calendarfx.view.DayView;
import com.calendarfx.view.EntryViewBase;
import com.calendarfx.view.MonthView;
import com.calendarfx.view.WeekView;

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
    private MonthView monthView;

    @FXML
    private WeekView weekView;

    @FXML
    private DayView dayView;

    private final Map<ViewType, DateControl> calendarViews = Map.of(
            ViewType.MONTH, monthView,
            ViewType.WEEK, weekView,
            ViewType.DAY, dayView
    );

    private ViewType currentView = ViewType.DAY;

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

        calendarViews.forEach((key, view) -> {
            view.setEntryContextMenuCallback(this::entryContextMenuCallback);
            view.setContextMenuCallback(param -> null);
            view.getCalendarSources().add(calendarSource);
            view.setBackground(Background.fill(Paint.valueOf("#ffffff")));
            view.setRequestedTime(LocalTime.now());
            view.managedProperty().bind(view.visibleProperty());
        });
        this.getRoot().addEventFilter(MouseEvent.ANY, javafx.event.Event::consume);
        setView(currentView);
        goToToday();
    }

    private ContextMenu entryContextMenuCallback(DateControl.EntryContextMenuParameter param) {
        EntryViewBase<?> entryView = param.getEntryView();
        TaskEntry entry = (TaskEntry) entryView.getEntry();

        ContextMenu contextMenu = new ContextMenu();

        MenuItem informationItem = new MenuItem("Information");
        informationItem.setOnAction(evt ->
                showInformationDialog(entry)
        );
        contextMenu.getItems().add(informationItem);

        return contextMenu;
    }

    public DateControl getActiveView() {
        return calendarViews.get(currentView);
    }

    public void showInformationDialog(TaskEntry entry) {
        Window window = getActiveView().getScene().getWindow();
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

    public void setDate(LocalDate date) {
        getActiveView().setDate(date);
    }

    public void goToToday() {
        getActiveView().goToday();
    }

    public void goToNext() {
        getActiveView().goForward();
    }

    public void goToPrevious() {
        getActiveView().goBack();
    }

    public void nextView() {
        setView(currentView.getNext());
    }

    public void setView(ViewType viewType) {
        currentView = viewType;
        calendarViews.forEach((key, view) -> view.setVisible(key == viewType));
    }

    private void spawnUpdateThread() {
        updateTimeThread = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    getActiveView().setToday(LocalDate.now());
                    getActiveView().setTime(LocalTime.now());
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
        taskEntryCalendarMap.values().forEach(Calendar::clear);
        taskList.getTaskList().forEach(task -> {
            Class<? extends Task> taskClass = task.getClass();
            if (taskEntryCalendarMap.containsKey(taskClass)) {
                Calendar<TaskEntry> calendar = taskEntryCalendarMap.get(taskClass);
                calendar.addEntry(TaskEntryUtil.convert(task));
            }
        });
    }

    public enum ViewType {
        MONTH, WEEK, DAY;

        public ViewType getNext() {
            return values()[(ordinal() + 1) % values().length];
        }
    }
}
