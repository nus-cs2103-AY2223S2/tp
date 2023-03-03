package trackr.model.task;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's name in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskName(String)}
 */
public class TaskName {

    public static final String MESSAGE_CONSTRAINTS =
            "Task names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullTaskName;

    /**
     * Constructs a {@code TaskName}.
     *
     * @param taskName A valid task name.
     */
    public TaskName(String taskName) {
        requireNonNull(taskName);
        checkArgument(isValidTaskName(taskName), MESSAGE_CONSTRAINTS);
        fullTaskName = taskName;
    }

    /**
     * Returns true if a given string is a valid task name.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullTaskName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskName // instanceof handles nulls
                && fullTaskName.equals(((TaskName) other).fullTaskName)); // state check
    }

    @Override
    public int hashCode() {
        return fullTaskName.hashCode();
    }

}

