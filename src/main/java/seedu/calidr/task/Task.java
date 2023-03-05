package seedu.calidr.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with the description of the task and
 * the status of the task - whether it is done.
 */
public abstract class Task {

    protected final String description;
    protected boolean isDone;
    protected Priority priority;


    protected Task(String description) {
        assert description != null;

        this.description = description;
        this.isDone = false;
        this.priority = Priority.MEDIUM;
    }

    protected Task(String description, Priority priority) {
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

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "{" + this.priority.toString().toLowerCase()
                + "}[" + mark + "] "
                + this.description;
    }

    /**
     * Gets the String representation of the Task to be stored in the text file.
     *
     * @return The String representation of the Task to be stored in the text file.
     */
    public abstract String getFileRepresentation();

}
