package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Education level in the address book.
 * Guarantees: immutable; value is valid as declared in {@link #isValidEducation(String)}
 */
public class Education {

    public static final String MESSAGE_CONSTRAINTS = "Education levels names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String value;

    /**
     * Constructs a {@code Education}.
     *
     * @param value A valid tag name.
     */
    public Education(String education) {
        requireNonNull(education);
        checkArgument(isValidEducation(education), MESSAGE_CONSTRAINTS);
        this.value = education;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidEducation(String test) {
        return test.matches(VALIDATION_REGEX) || test.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Education // instanceof handles nulls
                && value.equals(((Education) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[Education: " + value + ']';
    }

}
