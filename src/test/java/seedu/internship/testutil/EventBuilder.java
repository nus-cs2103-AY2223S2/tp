package seedu.internship.testutil;

import java.time.LocalDateTime;

import seedu.internship.model.event.End;
import seedu.internship.model.event.Event;
import seedu.internship.model.event.EventDescription;
import seedu.internship.model.event.Name;
import seedu.internship.model.event.Start;
import seedu.internship.model.internship.Internship;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {
    public static final String DEFAULT_NAME = "Interview";
    public static final LocalDateTime DEFAULT_START = LocalDateTime.parse("04/04/2023 1500",
            Start.NUMERIC_DATE_TIME_FORMATTER);
    public static final LocalDateTime DEFAULT_END = LocalDateTime.parse("04/04/2023 1800",
            End.NUMERIC_DATE_TIME_FORMATTER);
    public static final String DEFAULT_EVENT_DESCRIPTION = "The is a dummy event.";
    public static final InternshipBuilder DEFAULT_INTERNSHIP_BUILDER = new InternshipBuilder();

    // private Id id;
    private Name name;
    private Start start;
    private End end;
    private EventDescription description;
    private Internship internship;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        start = new Start(DEFAULT_START);
        end = new End(DEFAULT_END);
        description = new EventDescription(DEFAULT_EVENT_DESCRIPTION);
        internship = DEFAULT_INTERNSHIP_BUILDER.build();
    }

    /**
     * Initializes the EventBuilder with the data of {@code EventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        start = (Start) eventToCopy.getStart();
        end = (End) eventToCopy.getEnd();
        description = eventToCopy.getDescription();
        internship = eventToCopy.getInternship();
    }


    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Start} of the {@code Event} that we are building.
     */
    public EventBuilder withStart(String startDate) {
        this.start = new Start(LocalDateTime.parse(startDate, Start.NUMERIC_DATE_TIME_FORMATTER));
        return this;
    }

    /**
     * Sets the {@code End} of the {@code Event} that we are building.
     */
    public EventBuilder withEnd(String endDate) {
        this.end = new End(LocalDateTime.parse(endDate, End.NUMERIC_DATE_TIME_FORMATTER));
        return this;
    }

    /**
     * Sets the {@code EventDescription} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = new EventDescription(description);
        return this;
    }

    /**
     * Sets the {@code Internship} of the {@code Event} that we are building.
     */
    public EventBuilder withInternship(Internship intern) {
        if (intern == null) {
            // THis is important to test event commands
            this.internship = Internship.EMPTY_INTERNSHIP;
        } else {
            this.internship = new InternshipBuilder(intern).build();
        }
        return this;
    }

    /**
     * Builds the Event
     * @return Event Object
     */
    public Event build() {
        if (this.internship == null) {
            // This is important to test event commands
            return new Event(this.name, this.start, this.end, description);
        }
        return new Event(this.name, this.start, this.end, description, this.internship);
    }
}
