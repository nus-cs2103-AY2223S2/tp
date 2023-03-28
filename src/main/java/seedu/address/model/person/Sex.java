package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the gender of a Student object.
 */
public class Sex {
    public static final String MESSAGE_CONSTRAINTS = "Sex can only be 'M' or 'F', and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[MF]";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param sex A valid gender.
     */
    public Sex(String sex) {
        requireNonNull(sex);
        checkArgument(isValidSex(sex), MESSAGE_CONSTRAINTS);
        if (isDefaultSex(sex)) {
            value = "Insert student sex Here!";
            return;
        }
        value = sex;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidSex(String test) {
        test = test.toUpperCase();
        if (isDefaultSex(test)) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is the default string given when student's gender isn't given by user.
     *
     * @param test String value to test.
     * @return Boolean value true if the string given is the default string by the system.
     */
    public static boolean isDefaultSex(String test) {
        return test.equals("INSERT STUDENT SEX HERE!");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Sex // instanceof handles nulls
                && value.equals(((Sex) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
