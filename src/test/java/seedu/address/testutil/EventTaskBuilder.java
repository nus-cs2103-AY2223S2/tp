package seedu.address.testutil;

import seedu.address.model.task.Date;
import seedu.address.model.task.EventTask;
import seedu.address.model.task.TaskDescription;

/**
 * A utility class to help with building DeadlineTask objects.
 */
public class EventTaskBuilder {

    public static final String DEFAULT_DESCRIPTION = "Do assignments";
    public static final String DEFAULT_START_DATE = "11/11/2011";
    public static final String DEFAULT_END_DATE = "21/11/2011";

    private TaskDescription description;
    private Date startDate;
    private Date endDate;

    /**
     * Creates a {@code EventTaskBuilder} with the default details.
     */
    public EventTaskBuilder() {
        description = new TaskDescription(DEFAULT_DESCRIPTION);
        startDate = new Date(DEFAULT_START_DATE);
        endDate = new Date(DEFAULT_END_DATE);
    }

    /**
     * Initializes the EventTaskBuilder with the data of {@code taskToCopy}.
     */
    public EventTaskBuilder(EventTask taskToCopy) {
        description = taskToCopy.getDescription();
        startDate = taskToCopy.getStartDate();
        endDate = taskToCopy.getEndDate();
    }

    /**
     * Sets the {@code TaskDescription} of the {@code EventTask} that we are building.
     */
    public EventTaskBuilder withTaskDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code DeadlineTask} that we are building.
     */
    public EventTaskBuilder withStartDate(String date) {
        this.startDate = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code DeadlineTask} that we are building.
     */
    public EventTaskBuilder withEndDate(String date) {
        this.endDate = new Date(date);
        return this;
    }


    public EventTask build() {
        return new EventTask(description, startDate, endDate);
    }

}


