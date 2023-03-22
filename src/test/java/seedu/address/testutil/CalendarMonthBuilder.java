package seedu.address.model.calendar;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * A builder class to help build {@code CalendarMonth} objects.
 */
public class CalendarMonthBuilder {
    private List<Person> persons = new ArrayList<>();

    /**
     * Adds the given person to the list of persons in this builder.
     */
    public CalendarMonthBuilder withPerson(Person person) {
        persons.add(person);
        return this;
    }

    /**
     * Builds and returns a {@code CalendarMonth} object based on the persons in this builder.
     */
    public CalendarMonth build() {
        List<CalendarEvent> calendarEvents = new ArrayList<>();
        for (Person person : persons) {
            calendarEvents.add(new CalendarEvent(person));
        }
        ObservableList<CalendarEvent> observableCalendarEvents = javafx.collections.FXCollections.observableList(calendarEvents);
        return new CalendarMonth(observableCalendarEvents);
    }
}
