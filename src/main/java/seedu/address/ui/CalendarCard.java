package seedu.address.ui;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.YearMonthView;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyAppointmentList;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.logging.Logger;

// class to display calendar using CalendarFX
public class CalendarCard extends UiPart<Region> {

    private static final String FXML = "CalendarCard.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarCard.class);
    private static final CalendarSource source = new CalendarSource("Schedule");
    private static Calendar calendar = new Calendar("Task");
    @FXML
    private static YearMonthView calendarView = new YearMonthView();

    public CalendarCard() {
        super(FXML);
        calendarView.setRequestedTime(LocalTime.now());
        calendarView.setToday(LocalDate.now());
        source.getCalendars().add(calendar);
        calendarView.getCalendarSources().add(source);
        calendarView.setClickBehaviour(YearMonthView.ClickBehaviour.SHOW_DETAILS);
//        calendarView.setShowAddCalendarButton(false);
//        calendarView.setShowPrintButton(false);
//        calendarView.setShowPageToolBarControls(false);
//        calendarView.setShowSearchField(false);
//        calendarView.showDayPage();
    }

    public static void addAppointmentsToCalendar(ReadOnlyAppointmentList appointmentList) {
        for (int i = 0; i < appointmentList.getAppointmentList().size(); i++) {
            Entry<String> entry = new Entry<>(appointmentList.getAppointmentList().get(i).getDescription().description);
            entry.setInterval(appointmentList.getAppointmentList().get(i).getTimeslot().startingDateTime,
                    appointmentList.getAppointmentList().get(i).getTimeslot().endingDateTime);
            calendar.addEntry(entry);
        }
    }

    @Override
    public YearMonthView getRoot() {
        return calendarView;
    }


}