
package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.session.Session;


/**
 * Represents a CalendarEvent in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class CalendarEvent implements Comparable<CalendarEvent> {

    private final Session session;


    /**
     * Creates a CalendarEvent with the given name and appointment.
     */

    public CalendarEvent(Session session) {
        requireAllNonNull(session);

        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public String getName() {
        return session.getName();
    }

    public String getAddress() {
        return session.getLocation();
    }

    public String getDate() {
        return session.getDate();
    }

    public int getDay() {
        return this.session.getDay();
    }

    public int getMonth() {
        return this.session.getMonth();
    }

    public int getYear() {
        return this.session.getYear();
    }

    public String getTimeFormat() {
        return this.session.getTimeFormat();
    }

    public String getDuration() {
        return this.session.getSessionDuration().toString().substring(2);
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
        return session.getName().equals(otherEvent.getSession().getName())
                && session.equals(otherEvent.getSession()); // state check
    }

    @Override
    public String toString() {
        return String.format("%s, %s", getTimeFormat(), this.session.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(session.getName(), session);
    }

    @Override
    public int compareTo(CalendarEvent other) {
        return session.compareTo(other.getSession());
    }


}

