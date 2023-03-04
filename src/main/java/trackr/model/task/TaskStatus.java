package trackr.model.task;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's status in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskStatus(String)}
 */
public class TaskStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Task status should only be `N` for not done or `D` for done";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

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
        return test.matches(VALIDATION_REGEX)
                && (test.equalsIgnoreCase("N") || test.equalsIgnoreCase("D"));
    }


    @Override
    public String toString() {
        if (isDone) {
            return "D";
        }
        return "N";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskStatus // instanceof handles nulls
                && isDone == ((TaskStatus) other).isDone); // state check
    }

}

