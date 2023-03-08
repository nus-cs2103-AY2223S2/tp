package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * A Sex class
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
        value = sex;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidSex(String test) {
        return test.matches(VALIDATION_REGEX);
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
