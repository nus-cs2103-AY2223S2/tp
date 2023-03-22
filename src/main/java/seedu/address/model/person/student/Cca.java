package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the CCA (Co-curricular Activity) of a Student object.
 */
public class Cca {
    public static final String MESSAGE_CONSTRAINTS = "CCA must be letters";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param cca A valid cca in String.
     */
    public Cca(String cca) {
        requireNonNull(cca);
        checkArgument(isValidCca(cca), MESSAGE_CONSTRAINTS);
        value = cca;
    }

    /**
     * Returns true if a given string is a valid email.
     *
     * @param test String to be tested
     * @return true if the string is a valid cca (alphanumeric value)
     */
    public static boolean isValidCca(String test) {
        if (test.equals("Insert student CCA here!")) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is the default string given when the cca isn't given by user.
     *
     * @param test String value to test.
     * @return Boolean value true if the string given is the default string by the system.
     */
    public static boolean isDefaultCca(String test) {
        if (test.equals("Insert student CCA here!")) {
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
                || (other instanceof Cca// instanceof handles nulls
                && value.equals(((Cca) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
