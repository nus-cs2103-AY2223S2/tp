package seedu.address.testutil;

import seedu.address.model.Repository;
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
    public static final String DEFAULT_SUBJECT = "Recreation day";
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

    private Task buildRandomTask() {
        return new Task(new Subject(RANDOM_TASK_SUBJECT), new Content(RANDOM_TASK_CONTENT),
            new Status(RANDOM_TASK_STATUS));
    }

    private Task buildRandomTaskWithIndex(int index) {
        return new Task(new Subject(RANDOM_TASK_SUBJECT + " " + index),
            new Content(RANDOM_TASK_CONTENT + " " + index),
            new Status(RANDOM_TASK_STATUS));
    }

    /**
     * Generates a random task
     */
    public static Task ofRandomTask() {
        return new TaskBuilder().buildRandomTask();

    }
    /**
     * Generates a random list of tasks
     */
    public static Repository<Task> ofRandomTaskBook(int size) {
        Repository<Task> taskRepository = new Repository<>();
        for (int i = 0; i < size; i++) {
            taskRepository.addItem(new TaskBuilder().buildRandomTaskWithIndex(i));
        }
        return taskRepository;

    }

    /**
     * Sets the {@code Subject} of the {@code Task} that we are building.
     */
    public TaskBuilder withSubject(String subject) {
        this.subject = new Subject(subject);
        return this;
    }

    /**
     * Sets the {@code Content} of the {@code Task} that we are building.
     */
    public TaskBuilder withContent(String content) {
        this.content = new Content(content);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Task} that we are building.
     */
    public TaskBuilder withStatus(boolean status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets Task to be created
     *
     * @return Task containing specified {@code Subject, @code Content, @code Status}
     */
    public Task build() {
        return new Task(subject, content, status);
    }


}
