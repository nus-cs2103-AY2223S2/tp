package seedu.address.model.calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.testutil.CalendarEventBuilder;
import seedu.address.testutil.TypicalPersons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

public class CalendarMonthTest {
    private static final CalendarEvent EVENT_1 = new CalendarEventBuilder()
            .withPerson(TypicalPersons.AMY)
            .build();
    private static final CalendarEvent EVENT_2 = new CalendarEventBuilder()
            .withPerson(TypicalPersons.BOB)
            .build();
    private static final CalendarEvent EVENT_3 = new CalendarEventBuilder()
            .withPerson(TypicalPersons.ALICE)
            .build();

    private static final ObservableList<CalendarEvent> EVENT_LIST_1 = FXCollections.observableArrayList(EVENT_1, EVENT_2);
    private static final ObservableList<CalendarEvent> EVENT_LIST_2 = FXCollections.observableArrayList(EVENT_3);

    @Test
    public void constructor_null_throwsNullPointerException() {
        // null list
        assertThrows(NullPointerException.class, () -> new CalendarMonth(null));
    }

    @Test
    public void constructor_validList_success() {
        // valid list
        ObservableList<CalendarEvent> events = FXCollections.observableArrayList(EVENT_1, EVENT_2);
        CalendarMonth month = new CalendarMonth(events);
        assertEquals(EVENT_LIST_1, month.getCalendarEvents());
    }

    @Test
    public void getCalendarEventInDayOfMonth_validDate_success() {
        // valid date
        ObservableList<CalendarEvent> events = FXCollections.observableArrayList(EVENT_1, EVENT_2);
        CalendarMonth month = new CalendarMonth(events);
        assertEquals(EVENT_LIST_1, month.getCalendarEventInDayOfMonth(1, 1, 2022));
    }

    @Test
    public void getCalendarEventInDayOfMonth_invalidDate_success() {
        // invalid date
        ObservableList<CalendarEvent> events = FXCollections.observableArrayList(EVENT_1, EVENT_2, EVENT_3);
        CalendarMonth month = new CalendarMonth(events);
        assertEquals(FXCollections.observableArrayList(), month.getCalendarEventInDayOfMonth(30, 2, 2022));
    }

    @Test
    public void equals_sameInstance_true() {
        CalendarMonth month = new CalendarMonth(EVENT_LIST_1);
        assertEquals(month, month);
    }

    @Test
    public void equals_sameValues_true() {
        CalendarMonth month1 = new CalendarMonth(EVENT_LIST_1);
        CalendarMonth month2 = new CalendarMonth(EVENT_LIST_1);
        assertEquals(month1, month2);
    }

    @Test
    public void equals_differentValues_false() {
        CalendarMonth month1 = new CalendarMonth(EVENT_LIST_1);
        CalendarMonth month2 = new CalendarMonth(EVENT_LIST_2);
        assertEquals(false, month1.equals(month2));
    }

    @Test
    public void equals_differentTypes_false() {
        CalendarMonth month = new CalendarMonth(EVENT_LIST_1);
        assertEquals(false, month.equals(EVENT_LIST_1));
    }
}
