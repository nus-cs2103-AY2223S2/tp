package vimification.storage;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task task) {
        title = task.getTitle();
        status = task.getStatus();
        priority = task.getPriority();
        deadline = task.getDeadline();
        labels = List.copyOf(task.getLabels());
    }

    public Task toModelType() {
        Task task = new Task(title, deadline, status, priority);
        labels.forEach(task::addLabel);
        return task;
    }


    @Override
    public String toString() {
        return "JsonAdaptedTask [title=" + title + ", status=" + status + ", priority=" + priority
                + ", deadline=" + deadline + ", labels=" + labels + "]";
    }
}
