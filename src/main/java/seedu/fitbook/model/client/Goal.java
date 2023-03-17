package seedu.fitbook.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's Goal in FitBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidGoal(String)}
 */
public class Goal {

    public static final String MESSAGE_CONSTRAINTS =
            "Goal can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String value;

    /**
     * Constructs a {@code Goal}.
     *
     * @param goal A valid goal.
     */
    public Goal(String goal) {
        requireNonNull(goal);
        checkArgument(isValidGoal(goal), MESSAGE_CONSTRAINTS);
        value = goal;
    }

    /**
     * Returns true if a given string is a goal.
     */
    public static boolean isValidGoal(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Goal // instanceof handles nulls
                && value.equals(((Goal) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
