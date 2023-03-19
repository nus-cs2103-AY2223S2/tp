package trackr.model.commons;

import static trackr.commons.util.AppUtil.checkArgument;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a name in the list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public abstract class Name {

    private static final String MESSAGE_CONSTRAINTS =
            "%s names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace, otherwise " " (a blank string) becomes a valid input.
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
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getName() {
        return name;
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
