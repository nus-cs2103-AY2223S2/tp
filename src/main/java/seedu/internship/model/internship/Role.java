package seedu.internship.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's role in InternBuddy.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {


    public static final String MESSAGE_CONSTRAINTS =
            "Roles should not be blank, and should be at most 50 characters";

    /*
     * The first character of the role must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^.{1,50}$";

    public final String fullRole;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role to be associated with an Internship.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        this.fullRole = role;
    }

    /**
     * Returns true if a given string is a valid role.
     *
     * @param test The string to test against the regex.
     * @return true if the given string corresponds to a valid string for a role, else returns false.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the String representation of the Role.
     *
     * @return a String representing the role.
     */
    @Override
    public String toString() {
        return fullRole;
    }

    /**
     * Determines if another object is equal to this Role object.
     *
     * @param other The other object to compare with.
     * @return true if the other object is a Role object with the same role string.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Role // instanceof handles nulls
                && fullRole.toLowerCase().equals(((Role) other).fullRole.toLowerCase())); // state check
    }

    /**
     * Gets the hash code of the Role object.
     *
     * @return the hash code for the role represented by the Role object.
     */
    @Override
    public int hashCode() {
        return fullRole.hashCode();
    }

}
