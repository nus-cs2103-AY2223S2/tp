package seedu.address.testutil;

import seedu.address.model.task.Date;
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.TaskDescription;

/**
 * A utility class to help with building DeadlineTask objects.
 */
public class DeadlineTaskBuilder {

    public static final String DEFAULT_DESCRIPTION = "Do assignments";
    public static final String DEFAULT_DATE = "11/11/2011";

    private TaskDescription description;
    private Date date;

    /**
     * Creates a {@code DeadlineTaskBuilder} with the default details.
     */
    public DeadlineTaskBuilder() {
        description = new TaskDescription(DEFAULT_DESCRIPTION);
        date = new Date(DEFAULT_DATE);
    }

    /**
     * Initializes the DeadlineTaskBuilder with the data of {@code taskToCopy}.
     */
    public DeadlineTaskBuilder(DeadlineTask taskToCopy) {
        description = taskToCopy.getDescription();
        date = taskToCopy.getDeadlineDate();
    }

    /**
     * Sets the {@code TaskDescription} of the {@code DeadlineTask} that we are building.
     */
    public DeadlineTaskBuilder withTaskDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code DeadlineTask} that we are building.
     */
    public DeadlineTaskBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }


    public DeadlineTask build() {
        return new DeadlineTask(description, date);
    }

}
