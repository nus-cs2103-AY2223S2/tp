package trackr.model.commons;

import static trackr.commons.util.AppUtil.checkArgument;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a name in the list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public abstract class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "%s names should only contain alphanumerical characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String name;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     * @param type Type of name
     */
    public Name(String name, String type) {
        requireAllNonNull(name, type);
        checkArgument(isValidName(name), String.format(MESSAGE_CONSTRAINTS, type));
        this.name = name;
    }

    /**
     * Checks if a given name String conforms to the expected format.
     *
     * @param test The given string to check.
     * @return True if a given string is a of valid format false otherwise.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getName() {
        return name;
    }

    /**
     * Compare two names lexicographically (ignoring case).
     *
     * @param other The name to compare this name with.
     * @return 1 if this name is lexicographically larger (ignoring case) than the other name,
     *         -1 if this name is lexicographically smaller (ignoring case) than the other name,
     *         0 if both names are lexicographically equal (ignoring case).
     */
    public int compare(Name other) {
        int compareVal = name.compareToIgnoreCase(other.name);

        if (compareVal == 0) {
            return 0;
        } else {
            return compareVal / Math.abs(compareVal);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && name.equals(((Name) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
