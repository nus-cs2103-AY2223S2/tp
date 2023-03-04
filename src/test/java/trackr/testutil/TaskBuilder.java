package trackr.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import trackr.model.task.Task;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_NAME = "Sort inventory";
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final LocalDate DEFAULT_TASK_DEADLINE = LocalDate.parse("01/01/2027", dtf);
    public static final String DEFAULT_TASK_STATUS = "N";
    private TaskName taskName;
    private TaskDeadline taskDeadline;
    private TaskStatus taskStatus;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = new TaskName(DEFAULT_TASK_NAME);
        taskDeadline = new TaskDeadline(DEFAULT_TASK_DEADLINE);
        taskStatus = new TaskStatus(DEFAULT_TASK_STATUS);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.getTaskName();
        taskDeadline = taskToCopy.getTaskDeadline();
        taskStatus = taskToCopy.getTaskStatus();
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskName(String taskName) {
        this.taskName = new TaskName(taskName);
        return this;
    }

    /**
     * Sets the {@code TaskDeadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDeadline(LocalDate taskDeadline) {
        this.taskDeadline = new TaskDeadline(taskDeadline);
        return this;
    }

    /**
     * Sets the {@code TaskStatus} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskStatus(String taskStatus) {
        this.taskStatus = new TaskStatus(taskStatus);
        return this;
    }

    public Task build() {
        return new Task(taskName, taskDeadline, taskStatus);
    }

}
