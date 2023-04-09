package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's type number in the module tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidResource(String)}
 */
public class Resource {


    public static final String MESSAGE_CONSTRAINTS =
            "Resource can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String value;

    /**
     * Constructs a {@code Type}.
     *
     * @param resource A valid resource.
     */
    public Resource(String resource) {
        requireNonNull(resource);
        checkArgument(isValidResource(resource), MESSAGE_CONSTRAINTS);
        value = resource;
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
                || (other instanceof seedu.address.model.module.Resource // instanceof handles nulls
                && value.equals(((seedu.address.model.module.Resource) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
