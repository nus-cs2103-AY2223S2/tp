package vimification.storage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vimification.common.exceptions.DataConversionException;
import vimification.model.task.Priority;
import vimification.model.task.Status;
import vimification.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    private final String title;
    private final Status status;
    private final Priority priority;
    private final LocalDateTime deadline;
    private final List<String> labels;

    /**
     * The constructor used by Jackson.
     *
     * @param title title of the task
     * @param status current status of the task
     * @param priority current priority of the task
     * @param deadline current deadline of the task
     * @param labels a list contains all labels of the task
     */
    @JsonCreator
    public JsonAdaptedTask(
            @JsonProperty("title") String title,
            @JsonProperty("status") Status status,
            @JsonProperty("priority") Priority priority,
            @JsonProperty("deadline") LocalDateTime deadline,
            @JsonProperty("labels") List<String> labels) {
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.deadline = deadline;
        this.labels = labels;
    }


    /**
     * Converts a given {@code Task} for Jackson use.
     *
     * @param task the task to be converted
     */
    public JsonAdaptedTask(Task task) {
        title = task.getTitle();
        status = task.getStatus();
        priority = task.getPriority();
        deadline = task.getDeadline();
        labels = List.copyOf(task.getLabels());
    }

    /**
     * Converts this instance into an actual {@code Task}.
     *
     * @return a {@code Task}, as a result of the conversion
     * @throws DataConversionException if there is any error occured during the conversion
     */
    public Task toModelType() throws DataConversionException {
        try {
            Task task = new Task(title, deadline, status, priority);
            labels.forEach(task::addLabel);
            return task;
        } catch (RuntimeException ex) {
            throw new DataConversionException(ex);
        }
    }

    @Override
    public String toString() {
        return "JsonAdaptedTask [title=" + title + ", status=" + status + ", priority=" + priority
                + ", deadline=" + deadline + ", labels=" + labels + "]";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsonAdaptedTask)) {
            return false;
        }
        JsonAdaptedTask otherTask = (JsonAdaptedTask) other;
        return Objects.equals(title, otherTask.title)
                && Objects.equals(status, otherTask.status)
                && Objects.equals(priority, otherTask.priority)
                && Objects.equals(deadline, otherTask.deadline)
                && Objects.equals(labels, otherTask.labels);
    }
}
