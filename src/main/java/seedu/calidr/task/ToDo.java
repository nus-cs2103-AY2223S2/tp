package seedu.calidr.task;

/**
 * Represents a task that should be completed, with no time constraints.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo object with the given description and priority.
     *
     * @param description The description of the ToDo.
     * @param priority The priority associated with the ToDo.
     */
    public ToDo(String description, Priority priority) {

        super(description, priority);
    }

    /**
     * Gets the String representation of the to-do to be stored in the text file.
     *
     * @return The String representation of the to-do to be stored in the text file.
     */
    @Override
    public String getFileRepresentation() {
        String mark = (super.isDone) ? "X" : " ";

        return "T" + "~"
                + this.priority + "~"
                + mark + "~"
                + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
