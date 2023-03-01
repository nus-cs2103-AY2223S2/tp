package seedu.address.testutil;

import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Subject;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_SUBJECT = "Sports";
    public static final String DEFAULT_CONTENT = "Play football";
    public static final boolean DEFAULT_STATUS = false;

    private Subject subject;
    private Content content;
    private Status status;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        subject = new Subject(DEFAULT_SUBJECT);
        content = new Content(DEFAULT_CONTENT);
        status = new Status(DEFAULT_STATUS);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        subject = taskToCopy.getSubject();
        content = taskToCopy.getContent();
        status = taskToCopy.getStatus();
    }

    /**
     * Sets the {@code Subject} of the {@code Task} that we are building.
     */
    public TaskBuilder withSubject(String subject) {
        this.subject = new Subject(subject);
        return this;
    }

    /**
     * Sets Task to be created
     * @return Task containing specified {@code Subject, @code Content, @code Status}
     */
    public Task build() {
        return new Task(subject, content, status);
    }
}
