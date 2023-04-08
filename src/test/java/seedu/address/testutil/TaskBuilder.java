package seedu.address.testutil;

import seedu.address.model.task.Comment;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_DESCRIPTION = "Do assignments";
    public static final String DEAFULT_COMMENT = "Hard";
    public static final String DEFAULT_DATE = "";
    public static final String DEFAULT_TASKTYPE = "T";

    private TaskDescription description;
    private Comment comment;
    private String date;
    private String taskType;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        this.description = new TaskDescription(DEFAULT_DESCRIPTION);
        this.comment = new Comment(DEAFULT_COMMENT);
        this.date = DEFAULT_DATE;
        this.taskType = DEFAULT_TASKTYPE;
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        comment = taskToCopy.getTaskComment();
        date = taskToCopy.getDate();
        taskType = taskToCopy.getTaskType();
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDescription(String description) {
        this.description = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code taskType} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskType(String taskType) {
        this.taskType = taskType;
        return this;
    }

    /**
     * Sets the {@code comment} of the {@code Task} that we are building.
     */
    public TaskBuilder withComment(String comment) {
        this.comment = new Comment(comment);
        return this;
    }

    /**
     * Sets the {@code date} of the {@code Task} that we are building.
     */
    public TaskBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    public Task build() {
        return new Task(description, "", "T");
    }

}
