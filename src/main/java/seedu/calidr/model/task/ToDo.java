package seedu.calidr.model.task;

import seedu.calidr.model.task.params.Priority;
import seedu.calidr.model.task.params.Title;
import seedu.calidr.model.task.params.TodoDateTime;

/**
 * Represents a deadline - a task that should be completed within
 * a particular date and time.
 */
public class ToDo extends Task {

    private final TodoDateTime byDateTime;

    /**
     * Creates a ToDo object with the given details and MEDIUM priority.
     *
     * @param title The title of the ToDo.
     * @param by The deadline date-time of the ToDo.
     */
    public ToDo(Title title, TodoDateTime by) {
        super(title);

        assert by != null;

        this.byDateTime = by;
    }

    /**
     * Creates a ToDo with the given details.
     *
     * @param title The title of the ToDo.
     * @param by The deadline date-time of the ToDo.
     * @param priority The priority of the ToDo.
     */
    public ToDo(Title title, TodoDateTime by, Priority priority) {
        super(title, priority);

        assert by != null;

        this.byDateTime = by;
    }

    public TodoDateTime getBy() {
        return this.byDateTime;
    }

    @Override
    public String toString() {

        return "[T]" + super.toString()
                + " ("
                + byDateTime.toString()
                + ")";
    }
}
