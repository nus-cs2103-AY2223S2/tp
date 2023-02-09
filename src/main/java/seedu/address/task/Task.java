package seedu.address.task;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task with the description of the task and
 * the status of the task - whether it is done.
 */
public class Task {

    protected final String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Gets the representation of the LocalDateTime object formatted like Jan 21 2023 06:00 PM.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted String representation of the LocalDateTime object.
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
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

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + mark + "] " + this.description;
    }

}
