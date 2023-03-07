package seedu.address.testutil;

import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Subject;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String RANDOM_TASK_SUBJECT = "Random Subject";
    public static final String RANDOM_TASK_CONTENT = "Random Content ABC";
    public static final boolean RANDOM_TASK_STATUS = true;

    private final Subject subject;
    private final Content content;
    private final Status status;

    private TaskBuilder() {
        subject = new Subject(RANDOM_TASK_SUBJECT);
        content = new Content(RANDOM_TASK_CONTENT);
        status = new Status(RANDOM_TASK_STATUS);

    }

    private Task buildRandomTask() {
        return new Task(subject, content, status);
    }

    public static Task ofRandomTask() {
        return new TaskBuilder().buildRandomTask();
    }
}
