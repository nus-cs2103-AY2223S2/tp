package seedu.address.testutil;

import seedu.address.model.calendar.CalendarEvent;
import seedu.address.model.person.Person;

public class CalendarEventBuilder {
    private Person person;

    public CalendarEventBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }

    public CalendarEvent build() {
        return new CalendarEvent(person);
    }
}
