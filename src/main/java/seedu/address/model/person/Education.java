package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Education level in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidEducationName(String)}
 */
public class Education {

    public static final String MESSAGE_CONSTRAINTS = "Education levels names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String educationName;

    /**
     * Constructs a {@code Education}.
     *
     * @param educationName A valid tag name.
     */
    public Education(String educationName) {
        requireNonNull(educationName);
        checkArgument(isValidEducationName(educationName), MESSAGE_CONSTRAINTS);
        this.educationName = educationName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidEducationName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Education // instanceof handles nulls
                && educationName.equals(((Education) other).educationName)); // state check
    }

    @Override
    public int hashCode() {
        return educationName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[Education: " + educationName + ']';
    }

}
