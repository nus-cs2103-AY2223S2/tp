package seedu.address.model.job;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Dateline in the Tech track.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {
    public static final String VALIDATION_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    public final String deadline;
    public static String messageConstraint = "";

    /**
     * Constructs a {@code deadline}.
     *
     * @param deadline A valid date.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), messageConstraint);
        this.deadline = deadline;
    }

    /**
     * Returns true if a given string is a valid dateline.
     */
    public static boolean isValidDeadline(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            LocalDate lDate = LocalDate.parse(test);
            if (lDate.isBefore(LocalDate.now())) {
                messageConstraint = "Deadline should be older than the current date!";
                return false;
            }
            return true;
        } else {
            messageConstraint = "Deadline should be in this format: {YYYY-MM-DD}";
            return false;
        }
    }

    @Override
    public String toString() {
        return deadline;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
