package seedu.calidr.model.task;

import seedu.calidr.model.task.params.Description;
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
     * Creates a ToDo with the given details.
     *
     * @param title The title of the ToDo.
     * @param by The deadline date-time of the ToDo.
     * @param priority The priority of the ToDo.
     */
    public ToDo(Title title, Description description, TodoDateTime by) {
        super(title, description);

        assert by != null;
        this.byDateTime = by;
    }

    public TodoDateTime getBy() {
        return this.byDateTime;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("[T]");
        String mark = isDone() ? "X" : " ";
        sb.append("{")
                .append(getPriority().toString().toLowerCase())
                .append("}[").append(mark).append("] ")
                .append(getTitle())
                .append( " (")
                .append(byDateTime.toString())
                .append(")");

        if (getDescription().isPresent()) {
            sb.append(": \n").append(getDescription().get());
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other)
                && byDateTime.equals(((ToDo) other).byDateTime);
    }
}
