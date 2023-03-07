package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String deadlineName;
    public final LocalDateTime deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadlineName A valid deadline name.
     */
    public Deadline(String deadlineName, LocalDateTime deadline) {
        requireNonNull(deadlineName);
        checkArgument(isValidTagName(deadlineName), MESSAGE_CONSTRAINTS);
        this.deadlineName = deadlineName;
        this.deadline = deadline;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && deadlineName.equals(((Deadline) other).deadlineName) // state check
                && deadline.equals(((Deadline) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return deadlineName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + deadlineName + ']';
    }

}
