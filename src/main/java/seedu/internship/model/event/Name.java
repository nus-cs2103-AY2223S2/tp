package seedu.internship.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's name.
 */
public class Name {
    public static final String MESSAGE_CONSTRAINTS =
            "Name of event should not be blank";
    public static final String VALIDATION_REGEX = "\\S.*";
    public final String name;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name for a Name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid Name.
     */
    public static boolean isValidName(String test) {
        // Anything can be a valid Name, so return True
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && name.toLowerCase().equals(((Name) other).name.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
