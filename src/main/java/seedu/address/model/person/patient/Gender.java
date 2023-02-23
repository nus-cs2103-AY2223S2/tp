package seedu.address.model.person.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.makeStringCaseInsensitive;

/**
 * Represents a patient's gender in the patient record
 */
public class Gender {
    public static final String MESSAGE_CONSTRAINTS =
            "Gender should only be either female or male, it should not be blank";
    private static final String VALIDATION_REGEX = "f|female|m|male";
    private final String gender;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid gender.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.gender = gender;
    }

    /**
     * Returns true if a given string is either female or male.
     */
    public static boolean isValidGender(String test) {
        return makeStringCaseInsensitive(test).matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return gender;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender // instanceof handles nulls
                && gender.equals(((Gender) other).gender)); // state check
    }

    @Override
    public int hashCode() {
        return gender.hashCode();
    }
}
