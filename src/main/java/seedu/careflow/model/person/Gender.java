package seedu.careflow.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.AppUtil.checkArgument;
import static seedu.careflow.commons.util.StringUtil.makeStringCaseInsensitive;

/**
 * Represents a patient's gender in the patient record
 */
public class Gender {
    public static final String MESSAGE_CONSTRAINTS =
            "Gender should only be either female or male, it should not be blank";
    private static final String VALIDATION_REGEX = "f|female|m|male";
    public final String value;

    /**
     * Constructs a {@code Gender}.
     *
     * @param value A valid gender.
     */
    public Gender(String value) {
        requireNonNull(value);
        checkArgument(isValidGender(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is either female or male.
     */
    public static boolean isValidGender(String test) {
        return makeStringCaseInsensitive(test).matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && value.equals(((Gender) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
