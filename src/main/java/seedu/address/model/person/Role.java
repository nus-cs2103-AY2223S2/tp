package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {
    /*
     * The role string must be either "Doctor" or "Patient".
     */
    public static final String VALIDATION_REGEX = "^(Doctor|Patient)$";

    public final String role;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role));
        this.role = role;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return role;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && role.equals(((Role) other).role)); // state check
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

}

