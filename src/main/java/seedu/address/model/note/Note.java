package seedu.address.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Note in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidNoteName(String)}
 */
public class Note {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}-]+";

    public final String noteName;

    /**
     * Constructs a {@code Note}.
     *
     * @param noteName A valid note name.
     */
    public Note(String noteName) {
        requireNonNull(noteName);
        checkArgument(isValidNoteName(noteName), MESSAGE_CONSTRAINTS);
        this.noteName = noteName;
    }

    /**
     * Returns true if a given string is a valid note name.
     */
    public static boolean isValidNoteName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && noteName.equals(((Note) other).noteName)); // state check
    }

    @Override
    public int hashCode() {
        return noteName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + noteName + ']';
    }

}
