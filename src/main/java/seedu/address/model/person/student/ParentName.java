package seedu.address.model.person.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of the Parent object of a Student object.
 */
public class ParentName {
    public static final String MESSAGE_CONSTRAINTS = "Parent Name must be letters";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param parentName A valid parent name.
     */
    public ParentName(String parentName) {
        requireNonNull(parentName);
        checkArgument(isValidParentName(parentName), MESSAGE_CONSTRAINTS);
        value = parentName;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidParentName(String test) {
        if (test.equals("Insert parent name here!")) {
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
