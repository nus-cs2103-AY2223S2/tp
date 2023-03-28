/*
package seedu.address.testutil;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.model.calendar.CalendarEvent;

*/
/**
 * Create CalendarEvents based on Typical Persons
 *//*

public class TypicalCalendarEvents {

    public static final CalendarEvent MEETING_AMY = new CalendarEventBuilder()
            .withPerson(TypicalPersons.AMY).build();

    public static final CalendarEvent TUTORIAL_BOB = new CalendarEventBuilder()
            .withPerson(TypicalPersons.BOB)
            .build();

    public static final CalendarEvent[] CALENDAR_EVENTS = {MEETING_AMY, TUTORIAL_BOB};

    private TypicalCalendarEvents() {} // prevents instantiation

    */
/**
     * Returns an {@code ObservableList<CalendarEvent>} with all the typical calendar events.
     *//*

    public static ObservableList<CalendarEvent> getTypicalCalendarEvents() {
        return Arrays.stream(CALENDAR_EVENTS)
                .collect(Collectors.toCollection(javafx.collections.FXCollections::observableArrayList));
    }
}
*/
