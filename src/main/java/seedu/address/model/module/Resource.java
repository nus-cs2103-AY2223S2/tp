package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's type number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidResource(String)}
 */
public class Resource {


    public static final String MESSAGE_CONSTRAINTS =
            "Resource can be the module website";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String value;

    /**
     * Constructs a {@code Type}.
     *
     * @param type A valid type number.
     */
    public Resource(String type) {
        requireNonNull(type);
        checkArgument(isValidResource(type), MESSAGE_CONSTRAINTS);
        value = type;
    }

    /**
     * Returns true if a given string is a valid type number.
     */
    public static boolean isValidResource(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Resource // instanceof handles nulls
                && value.equals(((Resource) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
