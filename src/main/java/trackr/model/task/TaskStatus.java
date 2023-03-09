package trackr.model.task;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's status in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskStatus(String)}
 */
public class TaskStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Task status should only be `N` or `n` for not done or `D` or `d` for done";

    /*
     * The first character of the task status must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[nNdD]$";

    private boolean isDone;

    /**
     * Constructs a {@code TaskStatus}.
     */
    public TaskStatus() {
        //Task status defaulted to false
        isDone = false;
    }

    /**
     * Constructs a {@code TaskStatus}.
     *
     * @param status A valid status.
     */
    public TaskStatus(String status) {
        requireNonNull(status);
        checkArgument(isValidTaskStatus(status), MESSAGE_CONSTRAINTS);
        if (status.equalsIgnoreCase("D")) {
            isDone = true;
        }
    }

    /**
     * Returns true if a given string is a valid task status.
     */
    public static boolean isValidTaskStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the status stored in string format for json storage.
     * @return A string representation of the status.
     */
    public String toJsonString() {
        return isDone ? "D" : "N";
    }


    @Override
    public String toString() {
        return isDone ? "Done" : "Not Done";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskStatus // instanceof handles nulls
                && isDone == ((TaskStatus) other).isDone); // state check
    }

    @Override
    public int hashCode() {
        // Hashcode 1231 in Java represents true while 1237 represents false
        return isDone ? 1231 : 1237;
    }

}

