package arb.model.project;

import static arb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Project's deadline date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Validation to be implemented.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}]*";

    public final String dueDate;

    /**
     * Constructs a {@code Deadline}.
     * @param date A valid due date.
     */
    public Deadline(String date) {
        requireNonNull(date);
        checkArgument(isValidDeadline(date), MESSAGE_CONSTRAINTS);
        dueDate = date;
    }

    /**
     * Implementation NOT COMPLETE. TBD:
     * Returns true if a given string is a valid date.
     * @param test String to test.
     * @return True if valid.
     */
    public static boolean isValidDeadline(String test) {
        return true;
    }

    @Override
    public String toString() {
        return dueDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline) // handles null
                && dueDate.equals(((Deadline) other).dueDate); // checks date
    }

    @Override
    public int hashCode() {
        return dueDate.hashCode();
    }
}
