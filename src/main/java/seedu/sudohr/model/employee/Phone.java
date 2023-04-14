package seedu.sudohr.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.util.AppUtil.checkArgument;

/**
 * Represents a Employee's phone number in SudoHR.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, XXXX<SPACE>XXXX, and it should has strictly 8 digits";
    public static final String VALIDATION_REGEX = "^\\s*\\d{4}\\s*\\d{4}\\s*$";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = removeWhitespaces(phone);
    }

    /**
     * Removes leading zeroes from any given string
     */
    public static String removeWhitespaces(String str) {
        String strPattern = " ";
        str = str.replaceAll(strPattern, "");
        return str;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
