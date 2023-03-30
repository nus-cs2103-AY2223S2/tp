package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.YearMonthView;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyAppointmentList;

/**
 * A Calendar card that displays appointments in the appointment list.
 * Adapted from `CalendarPanel.java` written by ChoChihTun, from
 * <a href="https://github.com/CS2103JAN2018-T11-B1/main/blob/master/src/main/java/seedu/address/ui/CalendarPanel.java">
 *     Tuition Connect </a>.
 */
public class CalendarCard extends UiPart<Region> {
    private static final CalendarSource source = new CalendarSource("Schedule");
    private static Calendar calendar = new Calendar("Task");
    @FXML
    private static YearMonthView calendarView = new YearMonthView();
    private static final String FXML = "CalendarCard.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarCard.class);

    /**
     * Creates a {@code CalendarCard}.
     */
    public CalendarCard() {
        super(FXML);
        calendarView.setRequestedTime(LocalTime.now());
        calendarView.setToday(LocalDate.now());
        source.getCalendars().add(calendar);
        calendarView.getCalendarSources().add(source);
        calendarView.setClickBehaviour(YearMonthView.ClickBehaviour.SHOW_DETAILS);
    }

    /**
     * Adds appointments to the calendar.
     * @param appointmentList The appointment list to be added to the calendar.
     */
    public static void addAppointmentsToCalendar(ReadOnlyAppointmentList appointmentList) {
        if (source.getCalendars() != null) {
            source.getCalendars().clear();
        }
        Calendar calendar = new Calendar("Task");
        for (int i = 0; i < appointmentList.getAppointmentList().size(); i++) {
            String entryTitle = appointmentList.getAppointmentList().get(i).getPatientName().fullName + ", "
                    + appointmentList.getAppointmentList().get(i).getTimeslot().toString();
            Entry<String> entry = new Entry<>(entryTitle);
            entry.setInterval(appointmentList.getAppointmentList().get(i).getTimeslot().startingDateTime,
                    appointmentList.getAppointmentList().get(i).getTimeslot().endingDateTime);
            calendar.addEntry(entry);
        }
        source.getCalendars().add(calendar);

    }

    @Override
    public YearMonthView getRoot() {
        return calendarView;
    }
}
