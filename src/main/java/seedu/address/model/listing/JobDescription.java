package seedu.address.model.listing;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Listing's description in GoodMatch.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class JobDescription {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should contain at least one alphanumeric character, and it should not be blank "
                    + "nor exceed 500 characters.";

    /*
     * Validation regex
     */
    public static final String VALIDATION_ALL_CAPS = "^[A-Z]*$";
    public static final String VALIDATION_ALL_NON_ALPHANUM = "^[^a-zA-Z0-9]*$";
    public static final int VALIDATION_CHAR_LIMIT = 500;

    public final String fullDescription;

    /**
     * Constructs a {@code JobDescription}.
     *
     * @param description A valid description.
     */
    public JobDescription(String description) {
        requireNonNull(description);
        description = description.strip();
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        fullDescription = description;
    }

    /**
     * Returns true if a given string is a valid description.
     * @param test the String to be tested for valid description.
     * @return true if test is a valid description, else false.
     */
    public static boolean isValidDescription(String test) {
        // Check for maximum length
        if (test.length() > VALIDATION_CHAR_LIMIT) {
            return false;
        }

        // Check for all non-alphanumeric characters
        if (test.matches(VALIDATION_ALL_NON_ALPHANUM)) {
            return false;
        }

        // All checks passed, description is valid
        return true;
    }


    @Override
    public String toString() {
        return fullDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobDescription // instanceof handles nulls
                && fullDescription.equals(((JobDescription) other).fullDescription)); // state check
    }

    @Override
    public int hashCode() {
        return fullDescription.hashCode();
    }

}
