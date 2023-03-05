package seedu.address.model.person.student;

import seedu.address.model.person.Address;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class IndexNumber {
    public static final String MESSAGE_CONSTRAINTS = "Index Number can only be digits (0-9), and it should not be blank";

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
        value = indexNumber;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidIndexNumber(String test) {
        return test.matches(VALIDATION_REGEX);
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
