package seedu.task.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.task.model.tag.Tag;
import seedu.task.model.task.*;
import seedu.task.model.util.SampleDataUtil;

/**
 * A utility class to help with building Deadline objects.
 */
public class DeadlineBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_DESCRIPTION = "Amy's description";
    public static final String DEFAULT_TIME = "2023-01-01 1800";
    private Name name;
    private Description description;
    private Set<Tag> tags;
    private Date deadline;
    private Effort effort;

    /**
     * Creates a {@code DeadlineBuilder} with the default details.
     */
    public DeadlineBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        tags = new HashSet<>();
        deadline = new Date(DEFAULT_TIME);
        effort = new Effort();
    }

    /**
     * Initializes the DeadlineBuilder with the data of {@code taskToCopy}.
     */
    public DeadlineBuilder(Deadline taskToCopy) {
        name = taskToCopy.getName();
        description = taskToCopy.getDescription();
        tags = new HashSet<>(taskToCopy.getTags());
        deadline = taskToCopy.getDeadline();
        effort = taskToCopy.getEffort();
    }

    /**
     * Sets the {@code Name} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withDate(String deadline) {
        this.deadline = new Date(deadline);
        return this;
    }

    /**
     * Sets the {@code Effort} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withEffort(long e) {
        this.effort = new Effort(e);
        return this;
    }

    public Deadline build() {
        return new Deadline(name, description, tags, deadline, effort);
    }

}
