package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.student.ParentName;

/**
 * Represents the Age of a Student object.
 */
public class Birthdate {
    public static final String MESSAGE_CONSTRAINTS = "Birthdate must be in the following format mm/dd/yyyy";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(0[1-9]|1[0-2])\\/(0[1-9]|[12][0-9]|3[01])\\/\\d{4}$";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param Birthdate A valid student Birthdate.
     */
    public Birthdate(String Birthdate) {
        requireNonNull(Birthdate);
        checkArgument(isValidBirthdate(Birthdate), MESSAGE_CONSTRAINTS);
        value = Birthdate;
    }

    /**
     * Returns true if a given string is a valid birthdate.
     */
    public static boolean isValidBirthdate(String test) {
        if (isDefaultBirthdate(test)) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is the default string given when the Birthdate isn't given by user.
     *
     * @param test String value to test.
     * @return Boolean value true if the string given is the default string by the system.
     */
    public static boolean isDefaultBirthdate(String test) {
        if (test.equals("Insert student birthdate here!")) {
            return true;
        }
        if (test.equals("Insert parent birthdate here!")) {
            return true;
        }
        return false;
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
