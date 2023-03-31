package seedu.quickcontacts.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.quickcontacts.model.meeting.DateTime;
import seedu.quickcontacts.model.meeting.Description;
import seedu.quickcontacts.model.meeting.Location;
import seedu.quickcontacts.model.meeting.Meeting;
import seedu.quickcontacts.model.meeting.Title;
import seedu.quickcontacts.model.person.Person;
import seedu.quickcontacts.model.util.SampleDataUtil;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {
    public static final String DEFAULT_TITLE = "Meeting with Bob";
    public static final String DEFAULT_DATETIME = "01/01/2023 13:00";
    public static final String DEFAULT_LOCATION = "Plaza Singapura";
    public static final String DEFAULT_DESCRIPTION = "Celebrate Bob's birthday";
    public static final Person DEFAULT_PERSON = TypicalPersons.ALICE;

    private Title title;
    private DateTime dateTime;
    private Set<Person> attendees;
    private Location location;
    private Description description;
    private boolean isDone;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        title = new Title(DEFAULT_TITLE);
        dateTime = new DateTime(DEFAULT_DATETIME);
        attendees = new HashSet<>();
        attendees.add(DEFAULT_PERSON);
        location = new Location(DEFAULT_LOCATION);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        title = meetingToCopy.getTitle();
        dateTime = meetingToCopy.getDateTime();
        attendees = new HashSet<>(meetingToCopy.getAttendees());
        location = meetingToCopy.getLocation();
        description = meetingToCopy.getDescription();
    }

    /**
     * Sets the {@code Title} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDateTime(String dateTime) {
        this.dateTime = new DateTime(dateTime);
        return this;
    }

    /**
     * Parses the {@code attendees} into a {@code Set<Person>} and set it to the {@code Meeting} that we are building.
     */
    public MeetingBuilder withAttendees(Person... attendees) {
        this.attendees = SampleDataUtil.getAttendeeSet(attendees);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Meeting}
     */
    public MeetingBuilder withIsDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    public Meeting build() {
        return new Meeting(title, dateTime, attendees, location, description, isDone);
    }
}
