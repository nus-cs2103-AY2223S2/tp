package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.YearMonthView;
import com.calendarfx.view.popover.DatePopOver;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;

/**
 * A Calendar card that displays appointments in the appointment list.
 * Adapted from `CalendarPanel.java` written by ChoChihTun, from
 * <a href="https://github.com/CS2103JAN2018-T11-B1/main/blob/master/src/main/java/seedu/address/ui/CalendarPanel.java">
 * Tuition Connect </a>.
 */
public class CalendarCard extends UiPart<Region> {
    private static CalendarSource source = new CalendarSource("Schedule");
    private static Calendar calendar = new Calendar("Task");
    @FXML
    private static YearMonthView calendarView;
    private static final String FXML = "CalendarCard.fxml";
    private static boolean isInstantiated;
    private final Logger logger = LogsCenter.getLogger(CalendarCard.class);
    private DatePopOver datePopOver = null;

    /**
     * Creates a {@code CalendarCard}.
     */
    public CalendarCard() {
        super(FXML);
        isInstantiated = true;
        calendarView = new YearMonthView();
        calendarView.setRequestedTime(LocalTime.now());
        calendarView.setToday(LocalDate.now());
        calendarView.setShowWeekNumbers(false);
        source.getCalendars().add(calendar);
        calendarView.getCalendarSources().add(source);
        calendarView.setClickBehaviour(YearMonthView.ClickBehaviour.SHOW_DETAILS);
        calendarView.setDateDetailsCallback(param -> {
            InputEvent evt = param.getInputEvent();
            if (evt instanceof MouseEvent) {
                MouseEvent mouseEvent = (MouseEvent) evt;
                if (mouseEvent.getClickCount() == 1) {
                    if (datePopOver != null) {
                        datePopOver.hide();
                    }
                    datePopOver = new DatePopOver(calendarView, param.getLocalDate());
                    datePopOver.show(param.getOwner());
                    return true;
                }
            }

            return false;
        });
    }

    /**
     * Adds appointments to the calendar.
     *
     * @param appointmentList The appointment list to be added to the calendar.
     */
    public static void addAppointmentsToCalendar(ObservableList<Appointment> appointmentList) {
        if (source.getCalendars() != null) {
            source.getCalendars().clear();
        }
        Calendar calendar = new Calendar("Task");
        for (Appointment appointment : appointmentList) {
            String entryTitle = appointment.getPatientName().fullName + ", "
                    + appointment.getTimeslot().toString();
            Entry<String> entry = new Entry<>(entryTitle);
            entry.setInterval(appointment.getTimeslot().startingDateTime,
                    appointment.getTimeslot().endingDateTime);
            calendar.addEntry(entry);
        }

        source.getCalendars().add(calendar);
    }

    @Override
    public YearMonthView getRoot() {
        return calendarView;
    }

    public static boolean getIsInstantiated() {
        return isInstantiated;
    }
}
