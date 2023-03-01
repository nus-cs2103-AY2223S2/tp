package seedu.address.model.listing;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Listing's description in GoodMatch.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * Validation regex
     */
    public static final String VALIDATION_ALL_CAPS = "^[A-Z]*$";
    public static final String VALIDATION_ALL_NON_ALPHANUM = "^[^a-zA-Z0-9]*$";

    public final String fullDescription;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        description = description.strip();
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        fullDescription = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        // Check for maximum length
        if (test.length() > 500) {
            return false;
        }

        // Check for all caps
        if (test.matches(VALIDATION_ALL_CAPS)) {
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
                || (other instanceof Description // instanceof handles nulls
                && fullDescription.equals(((Description) other).fullDescription)); // state check
    }

    @Override
    public int hashCode() {
        return fullDescription.hashCode();
    }

}
