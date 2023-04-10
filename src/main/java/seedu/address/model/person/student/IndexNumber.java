package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the index number of a Student object.
 */
public class IndexNumber {
    public static final String MESSAGE_CONSTRAINTS =
            "Index Number can only be digits (0-9), and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param indexNumber A valid indexNumber.
     */
    public IndexNumber(String indexNumber) {
        requireNonNull(indexNumber);
        checkArgument(isValidIndexNumber(indexNumber), MESSAGE_CONSTRAINTS);
        String strPattern = "^0+(?!$)";
        indexNumber = indexNumber.replaceAll(strPattern, "");
        value = indexNumber;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidIndexNumber(String test) {
        if (isDefaultIndexNumber(test)) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is the default string given when the index number isn't given by user.
     *
     * @param test String value to test.
     * @return Boolean value true if the string given is the default string by the system.
     */
    public static boolean isDefaultIndexNumber(String test) {
        if (test.equals("Insert student index number here!")) {
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
                || (other instanceof IndexNumber // instanceof handles nulls
                && value.equals(((IndexNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
