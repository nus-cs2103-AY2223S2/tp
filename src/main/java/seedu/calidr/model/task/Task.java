package seedu.calidr.model.task;

import seedu.calidr.model.task.params.Priority;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with the description of the task and
 * the status of the task - whether it is done.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;
    private Priority priority;

    /**
     * Creates a Task object with the given description and MEDIUM priority.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        assert description != null;

        this.description = description;
        this.isDone = false;
        this.priority = Priority.MEDIUM;
    }

    /**
     * Creates a Task object with the given description and priority.
     *
     * @param description The description of the Task.
     * @param priority    The priority associated with the Task.
     */
    public Task(String description, Priority priority) {
        assert description != null;
        assert priority != null;

        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * Gets the representation of the LocalDateTime object formatted like Jan 21 2023 06:00 PM.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted String representation of the LocalDateTime object.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        assert dateTime != null;

        DateTimeFormatter formatToPrint = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatToPrint);

    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void setPriority(Priority p) {
        this.priority = p;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "{" + this.priority.toString().toLowerCase() + "}[" + mark + "] " + this.description;
    }

    /**
     * Gets the String representation of the Task to be stored in the text file.
     *
     * @return The String representation of the Task to be stored in the text file.
     */
    public abstract String getFileRepresentation();

}
