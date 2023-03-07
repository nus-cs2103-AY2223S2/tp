package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class CCA {
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
     * @param cca A valid cca.
     */
    public CCA(String cca) {
        requireNonNull(cca);
        checkArgument(isValidCCA(cca), MESSAGE_CONSTRAINTS);
        value = cca;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidCCA(String test) {
        if (test.equals("Insert student CCA here!")) {
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
                || (other instanceof CCA// instanceof handles nulls
                && value.equals(((CCA) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
