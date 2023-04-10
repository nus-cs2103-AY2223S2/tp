package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's description in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskDescription(String)}
 */
public class TaskDescription {

    public static final String MESSAGE_CONSTRAINTS =
            "Task descriptions should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullTaskDescription;

    /**
     * Constructs a {@code TaskDescription}.
     *
     * @param description A valid description.
     */
    public TaskDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidTaskDescription(description), MESSAGE_CONSTRAINTS);
        fullTaskDescription = description;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTaskDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullTaskDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.task.TaskDescription // instanceof handles nulls
                && fullTaskDescription.equals(((seedu.address.model.task.TaskDescription) other).fullTaskDescription));
    }

    @Override
    public int hashCode() {
        return fullTaskDescription.hashCode();
    }

}

