package seedu.task.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.task.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's description in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Description must contain at least one character";
    public static final String DEFAULT_VALUE = "No Description";
    public final String value;

    private boolean hasDescription = false;

    /**
     * Constructs a {@code Description} with default description.
     */
    public Description() {
        value = DEFAULT_VALUE;
    }

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
        hasDescription = true;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidDescription(String test) {
        return !test.trim().isEmpty();
    }

    public boolean getHasDescription() {
        return hasDescription;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
