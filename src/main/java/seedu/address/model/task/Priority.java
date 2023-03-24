package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the priority of a Task.
 * Guarantees: Non-null priority in valid form.
 */
public class Priority {
    public static final String MESSAGE_CONSTRAINTS =
            "Priority should be of the form: Low / Medium / High, and it should not be blank";

    public final String priority;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority priority level of a Task.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        this.priority = priority;
    }

    /**
     * Returns true if a given string is a valid priority.
     */
    public static boolean isValidPriority(String test) {
        return test.equals("low") || test.equals("medium") || test.equals("high");
    }

    @Override
    public String toString() {
        return this.priority;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority// instanceof handles nulls
                && priority.equals(((Priority) other).priority)); // state check
    }

    @Override
    public int hashCode() {
        return priority.hashCode();
    }
}
