package seedu.address.task;

/**
 * Represents a task that should be completed, with no time constraints.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
