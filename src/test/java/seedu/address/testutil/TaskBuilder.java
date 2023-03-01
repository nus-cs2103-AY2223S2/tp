package seedu.address.testutil;


import seedu.address.model.person.*;
import seedu.address.model.task.Content;
import seedu.address.model.task.Status;
import seedu.address.model.task.Subject;
import seedu.address.model.task.Task;

import java.util.HashSet;

public class TaskBuilder {

    public static final String DEFAULT_SUBJECT = "Sports";
    public static final String DEFAULT_CONTENT = "Play football";
    public static final boolean DEFAULT_STATUS = false;

    private Subject subject;
    private Content content;
    private Status status;

    public TaskBuilder() {
        subject = new Subject(DEFAULT_SUBJECT);
        content = new Content(DEFAULT_CONTENT);
        status = new Status(DEFAULT_STATUS);
    }

    public TaskBuilder(Task taskToCopy) {
        subject = taskToCopy.getSubject();
        content = taskToCopy.getContent();
        status = taskToCopy.getStatus();
    }

    public TaskBuilder withSubject(String subject) {
        this.subject = new Subject(subject);
        return this;
    }

    public Task build() {
        return new Task(subject, content, status);
    }
}
