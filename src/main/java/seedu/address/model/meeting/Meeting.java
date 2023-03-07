package seedu.address.model.meeting;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Meeting in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting {

    // Identity fields
    private final Title title;
    private final DateTime dateTime;
    private final Attendees attendees;

    // Data fields
    private final Location location;
    private final Description description;

    /**
     * Every field must be present and not null.
     */
    public Meeting(Title title, DateTime dateTime, Attendees attendees, Location location, Description description) {
        requireAllNonNull(title, dateTime, attendees, location, description);
        this.title = title;
        this.dateTime = dateTime;
        this.attendees = attendees;
        this.location = location;
        this.description = description;
    }

    public Title getTitle() {
        return title;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public Attendees getAttendees() {
        return attendees;
    }

    public Location getLocation() {
        return location;
    }

    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if both meetings have the same title, dateTime and attendees.
     * This defines a weaker notion of equality between two meetings.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        boolean hasSameTitle = otherMeeting.getTitle().equals(getTitle());
        boolean hasSameDateTime = otherMeeting.getDateTime().equals(getDateTime());
        boolean hasSameAttendees = otherMeeting.getAttendees().equals(getAttendees());

        return otherMeeting != null && hasSameDateTime && hasSameDateTime && hasSameAttendees;
    }

    /**
     * Returns true if both meetings have the same identity and data fields.
     * This defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getTitle().equals(getTitle())
                && otherMeeting.getDateTime().equals(getDateTime())
                && otherMeeting.getAttendees().equals(getAttendees())
                && otherMeeting.getLocation().equals(getLocation())
                && otherMeeting.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, dateTime, attendees, location, description);
    }

    @Override
    public String toString() {
        return getTitle() +
                "; Date/Time: " +
                getDateTime() +
                "; Attendees: " +
                getAttendees() +
                "; Location: " +
                getLocation() +
                "; Description: " +
                getDescription();
    }
}
