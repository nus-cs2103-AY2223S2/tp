package seedu.task.testutil;

import seedu.task.model.task.Description;
import seedu.task.model.task.Name;
import seedu.task.model.task.Subtask;


/**
 * A utility class to help with building Task objects.
 */
public class SubtaskBuilder {

    public static final String DEFAULT_NAME = "do homework";
    public static final String DEFAULT_DESCRIPTION = "lecture 1";

    private Name name;
    private Description description;

    /**
     * Creates a {@code SimpleTaskBuilder} with the default details.
     */
    public SubtaskBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the SimpleTaskBuilder with the data of {@code taskToCopy}.
     */
    public SubtaskBuilder(Subtask taskToCopy) {
        name = taskToCopy.getName();
        description = taskToCopy.getDescription();
    }

    /**
     * Sets the {@code Name} of the {@code Subtask} that we are building.
     */
    public SubtaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Subtask} that we are building.
     */
    public SubtaskBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }


    public Subtask build() {
        return new Subtask(name, description);
    }

    public Subtask buildDefault() {
        return new Subtask(name, description);
    }

}
