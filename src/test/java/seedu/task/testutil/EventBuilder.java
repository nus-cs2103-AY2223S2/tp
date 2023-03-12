package seedu.task.testutil;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import seedu.task.model.tag.Tag;
import seedu.task.model.task.Date;
import seedu.task.model.task.Description;
import seedu.task.model.task.Event;
import seedu.task.model.task.Name;
import seedu.task.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_DESCRIPTION = "Amy's description";
    public static final String DEFAULT_START = "2023-01-01 1800";
    public static final String DEFAULT_END = "2023-01-02 1800";
    private Name name;
    private Description description;
    private Set<Tag> tags;
    private Date from;
    private Date to;
    private Duration alertWindow;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
        from = new Date(DEFAULT_START);
        to = new Date(DEFAULT_END);
    }

    /**
     * Initializes the EventBuilder with the data of {@code taskToCopy}.
     */
    public EventBuilder(Event taskToCopy) {
        name = taskToCopy.getName();
        description = taskToCopy.getDescription();
        tags = new HashSet<>(taskToCopy.getTags());
        from = taskToCopy.getFrom();
        to = taskToCopy.getTo();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public EventBuilder withFrom(String from) {
        this.from = new Date(from);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Event} that we are building.
     */
    public EventBuilder withTo(String to) {
        this.to = new Date(to);
        return this;
    }

    /**
     * Sets the {@code alertWindow} of the {@code Event} that we are building.
     */
    public EventBuilder withAlertWindow(String alertWindow) {
        this.alertWindow = Duration.ofHours(Long.valueOf(alertWindow));
        return this;
    }


    public Event build() {
        return new Event(name, description, tags, from, to);
    }

}
