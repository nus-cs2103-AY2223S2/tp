package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.student.ParentName;

/**
 * Represents the Age of a Student object.
 */
public class Age {
    public static final String MESSAGE_CONSTRAINTS = "Age must be digits (0-9)";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param age A valid student age.
     */
    public Age(String age) {
        requireNonNull(age);
        checkArgument(isValidAge(age), MESSAGE_CONSTRAINTS);
        value = age;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAge(String test) {
        if (test.equals("Insert student age here!")) {
            return true;
        }
        if (test.equals("Insert parent age here!")) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParentName// instanceof handles nulls
                && value.equals(((ParentName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
