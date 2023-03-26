/*
package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;


*/
/**
 * Represents a CalendarEvent in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 *//*

public class CalendarEvent implements Comparable<CalendarEvent> {

    private final Person person;

    */
/**
     * Creates a CalendarEvent with the given name and appointment.
     *//*

    public CalendarEvent(Person person) {
        requireAllNonNull(person);

        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public Name getName() {
        return person.getName();
    }

    public Address getAddress() {
        return person.getAddress();
    }

    public String getDate() {
        return person.getSession().toString();
    }

    public int getDay() {
        return this.person.getSession().getDay();
    }

    public int getMonth() {
        return this.person.getSession().getMonth();
    }

    public int getYear() {
        return this.person.getSession().getYear();
    }

    public String getTimeFormat() {
        return this.person.getSession().getTimeFormat();
    }

    public String getDuration() {
        return this.person.getSession().getSessionDuration().toString().substring(2);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }

        if (!(other instanceof CalendarEvent)) { // instanceof handles nulls
            return false;
        }
        CalendarEvent otherEvent = (CalendarEvent) other;
        return person.getName().equals(otherEvent.getPerson().getName())
                && person.getSession().equals(otherEvent.getPerson().getSession()); // state check
    }

    @Override
    public String toString() {
        return String.format("%s, %s", getTimeFormat(), this.person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(person.getName(), person.getSession());
    }

    @Override
    public int compareTo(CalendarEvent other) {
        return person.getSession().compareTo(other.person.getSession());
    }


}
*/
