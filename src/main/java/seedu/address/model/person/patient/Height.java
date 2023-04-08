package seedu.address.model.person.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's height in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidHeight(String)}
 */
public class Height {

    public static final String MESSAGE_CONSTRAINTS =
            "Height should be a positive decimal point number with two decimal places representing the patient's height"
                    + " in metres and should not be blank";

    /*
     * The first character of the specialty must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^\\s*\\d{1}\\.\\d{2}\\s*$";

    private final String value;

    /**
     * Constructs a {@code Height}.
     *
     * @param height A valid height.
     */
    public Height(String height) {
        requireNonNull(height);
        checkArgument(isValidHeight(height), MESSAGE_CONSTRAINTS);
        String trimmedHeight = height.trim();
        this.value = trimmedHeight;
    }

    /**
     * Returns true if a given string is a valid height.
     */
    public static boolean isValidHeight(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value + " m";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Height // instanceof handles nulls
                && value.equals(((Height) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public String getValue() {
        return value;
    }
}
