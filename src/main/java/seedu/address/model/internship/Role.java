package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's role in InternBuddy.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {


    public static final String MESSAGE_CONSTRAINTS =
            "Roles should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

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
     * @param test The regex to test whether a string is a valid role.
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
                && fullRole.equals(((Role) other).fullRole)); // state check
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
