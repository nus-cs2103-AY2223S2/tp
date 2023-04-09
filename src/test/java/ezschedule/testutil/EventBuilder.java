package ezschedule.testutil;

import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.Name;
import ezschedule.model.event.Time;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "CS2103 Event";
    public static final String DEFAULT_DATE = "2023-05-01";
    public static final String DEFAULT_START_TIME = "08:00";
    public static final String DEFAULT_END_TIME = "10:00";
    public static final String EDIT_EVENT_NAME = "Graduation";
    public static final String EDIT_EVENT_DATE = "2023-10-10";
    public static final String EDIT_EVENT_START_TIME = "10:00";
    public static final String EDIT_EVENT_END_TIME = "22:00";

    private Name name;
    private Date date;
    private Time startTime;
    private Time endTime;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        date = new Date(DEFAULT_DATE);
        startTime = new Time(DEFAULT_START_TIME);
        endTime = new Time(DEFAULT_END_TIME);
    }
    /**
     * Creates a {@code EventBuilder} with the given details.
     */
    public EventBuilder(String name, String date, String startTime, String endTime) {
        this.name = new Name(name);
        this.date = new Date(date);
        this.startTime = new Time(startTime);
        this.endTime = new Time(endTime);
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        date = eventToCopy.getDate();
        startTime = eventToCopy.getStartTime();
        endTime = eventToCopy.getEndTime();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code Event} that we are building.
     */
    public EventBuilder withStartTime(String time) {
        this.startTime = new Time(time);
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code Event} that we are building.
     */
    public EventBuilder withEndTime(String time) {
        this.endTime = new Time(time);
        return this;
    }

    public Event build() {
        return new Event(name, date, startTime, endTime);
    }
}
