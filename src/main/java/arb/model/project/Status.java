package arb.model.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Project's completion status in the address book.
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS = "Status must be 'done' / 'd' or 'not done' / 'nd' (case-insensitive). "
            + "\n Not done includes overdue status. Status must not be empty.";

    /**
     * List of possible string representations of "done" status.
     */
    public static final List<String> STATUS_DONE = List.of(new String[]{"DONE", "D"});
    /**
     * List of possible string representations of "not done" status.
     */
    public static final List<String> STATUS_NOT_DONE = List.of(new String[]{"NOT DONE", "ND"});

    private boolean isDone;

    /**
     * Constructs a {@code Status}: False by default.
     */
    public Status() {
        isDone = false;
    }

    /**
     * Constructs a {@code Status} with the given value.
     * @param status The value.
     */
    public Status(boolean status) {
        isDone = status;
    }

    /**
     * Returns the completion status as a boolean.
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Sets the completion status to True.
     */
    public void setTrue() {
        isDone = true;
    }

    /**
     * Sets the completion status to False.
     */
    public void setFalse() {
        isDone = false;
    }

    @Override
    public String toString() {
        return isDone ? STATUS_DONE.get(0) : STATUS_NOT_DONE.get(0);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // Short-circuit if same object
                || (other instanceof Status) // Handles null
                && isDone == ((Status) other).isDone; // Check isDone
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(isDone);
    }

}
