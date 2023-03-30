package seedu.internship.ui.pages;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.DateControl;
import com.calendarfx.view.page.MonthPage;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import seedu.internship.commons.core.LogsCenter;
import seedu.internship.model.event.Event;

/**
 * A panel to display detailed information about an internship.
 */
public class CalendarPage extends Page {

    private static final String FXML = "CalendarPage.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarPage.class);

    // Controls from CalendarFX
    private MonthPage monthPage;
    private Calendar internshipEvents;

    private ObservableList<Event> events;

    @FXML
    private StackPane calendarPlaceholder;


    /**
     * Creates a {@code InternshipInfoPanel} displaying all information of an Internship.
     */
    public CalendarPage(ObservableList<Event> events) {
        super(FXML);
        this.events = events;
        initializeCalendar();
        addCalendarEntries();
        calendarPlaceholder.getChildren().add(monthPage);
    }

    /**
     * Initializes all components required to show the calendar and sets time for {@code MonthPage}
     * @return A MonthPage that is initialized.
     */
    public MonthPage initializeCalendar() {

        // Solution below adapted from https://dlsc-software-consulting-gmbh.github.io/CalendarFX/#_quick_start with
        // minor modifications
        monthPage = new MonthPage();

        internshipEvents = new Calendar("Internship events");

        internshipEvents.setStyle(Calendar.Style.STYLE1);

        CalendarSource calendarSource = new CalendarSource("The Intern's Ship");
        calendarSource.getCalendars().addAll(internshipEvents);

        monthPage.getCalendarSources().addAll(calendarSource);

        monthPage.setRequestedTime(LocalTime.now());

        monthPage.setEntryDetailsPopOverContentCallback(this::makeCalendarPopover);
        monthPage.setEntryFactory(param -> null);

        monthPage.getDateDetailsCallback();

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        monthPage.setToday(LocalDate.now());
                        monthPage.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();

        return monthPage;

    }

    /**
     * Enters each {@code Event} as an {@code Entry} into the {@code Calendar}
     */
    public void addCalendarEntries() {
        for (Event event : events) {
            Entry<Event> entry = new CalendarEntry(event);
            if (event.isDeadline()) {
                entry.setInterval(event.getStart().getLocalDateTime());
                entry.setFullDay(true);
                entry.setInterval(event.getStart().getLocalDateTime());
            } else {
                entry.setInterval(event.getStart().getLocalDateTime(),
                        event.getEnd().getLocalDateTime());
            }
            this.internshipEvents.addEntry(entry);
        }
    }

    /**
     * Returns a custom popover for the {@code MonthPage}
     * @param param Specified input for the producer taken in by monthPage.setEntryDetailsPopOverContentCallback
     * @return A javafx Node
     */
    public Node makeCalendarPopover(DateControl.EntryDetailsPopOverContentParameter param) {
        // Okay to suppress because all entries created are CalendarEntry (s)
        @SuppressWarnings("unchecked")
        CalendarEntry calendarEntry = (CalendarEntry) param.getEntry();
        return new CalendarPopOver(calendarEntry.getEvent()).getRoot();
    }

}
