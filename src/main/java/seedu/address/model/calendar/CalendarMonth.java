
package seedu.address.model.calendar;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.session.Session;

//@@author wongyewjon
/**
 * Represents a Calendar in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class CalendarMonth {
    // Data fields
    private ObservableList<Session> sessions;

    //@@author wongyewjon
    /**
     * Every field must be present and not null.
     */

    public CalendarMonth(ObservableList<Session> sessions) {
        requireAllNonNull(sessions);
        this.sessions = sessions;
    }

    //@@author wongyewjon
    /**
     * Returns a mutable list of CalendarEvents.
     */
    public ObservableList<Session> getSessions() {
        return sessions;
    }

    //@@author wongyewjon
    public ObservableList<Session> getSessionsInDayOfMonth(Integer day, Integer month, Integer year) {
        requireAllNonNull(day, month, year);
        List<Session> sessionsInDayOfMonth = new ArrayList<>();
        Predicate<Session> filter = (e) -> e.getDay() == day && e.getMonth() == month && e.getYear() == year;
        sessions.stream().filter(filter).forEach(sessionsInDayOfMonth::add);
        return FXCollections.observableList(sessionsInDayOfMonth);
    }

    //@@author wongyewjon
    /**
     * Returns true if both calendar month data field.
     * This defines a stronger notion of equality between two calendar month.
     */

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CalendarMonth)) {
            return false;
        }

        CalendarMonth otherCalendar = (CalendarMonth) other;
        return otherCalendar.getSessions().equals(getSessions());
    }
    //@@author wongyewjon
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(sessions);
    }

}
