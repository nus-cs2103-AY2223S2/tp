package seedu.address.testutil;

import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "Sports Week";
    public static final String DEFAULT_START_DATE_TIME = "11-09-2023 09:00";
    public static final String DEFAULT_END_DATE_TIME = "15-09-2023 19:00";

    private EventName name;
    private DateTime startDateTime;
    private DateTime endDateTime;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new EventName(DEFAULT_NAME);
        startDateTime = new DateTime(DEFAULT_START_DATE_TIME);
        endDateTime = new DateTime(DEFAULT_END_DATE_TIME);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        startDateTime = eventToCopy.getStartDateTime();
        endDateTime = eventToCopy.getEndDateTime();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new EventName(name);
        return this;
    }

    /**
     * Sets the {@code startDateTime} of the {@code Event} that we are building.
     */
    public EventBuilder withStartDateTime(String startDateTime) {
        this.startDateTime = new DateTime(startDateTime);
        return this;
    }

    /**
     * Sets the {@code endDateTime} of the {@code Event} that we are building.
     */
    public EventBuilder withEndDateTime(String endDateTime) {
        this.endDateTime = new DateTime(endDateTime);
        return this;
    }

    public Event build() {
        return new Event(name, startDateTime, endDateTime);
    }
}
