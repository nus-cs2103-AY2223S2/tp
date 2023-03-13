package seedu.calidr.model.task;

import seedu.calidr.model.task.params.Priority;

import java.time.LocalDateTime;

/**
 * Represents a deadline - a task that should be completed within
 * a particular date and time.
 */
public class ToDo extends Task {

    private final LocalDateTime by;

    /**
     * Creates a Deadline object with the given description and MEDIUM priority.
     *
     * @param description The description of the ToDo.
     */
    public ToDo(String description, LocalDateTime by) {
        super(description);

        assert by != null;

        this.by = by;
    }

    /**
     * Creates a Deadline object with the given description and priority.
     *
     * @param description The description of the Deadline.
     * @param priority The priority associated with the Deadline.
     */
    public ToDo(String description, LocalDateTime by, Priority priority) {
        super(description, priority);

        assert by != null;

        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }


    /**
     * Gets the String representation of the deadline to be stored in the text file.
     *
     * @return The String representation of the deadline to be stored in the text file.
     */
    @Override
    public String getFileRepresentation() {
        String mark = (super.isDone()) ? "X" : " ";

        return "T" + "~" + this.getPriority() + "~" + mark + "~" + this.getDescription() + "~" + this.by;
    }

    @Override
    public String toString() {

        return "[T]" + super.toString()
                + " (by: "
                + Task.getDateTimeString(this.by)
                + ")";
    }
}
