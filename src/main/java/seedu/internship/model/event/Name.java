package seedu.internship.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's name.
 */
public class Name {
    public static final String MESSAGE_CONSTRAINTS =
            "Name should not be blank";

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
        if (test.equals("")) {
            // An Empty String Cannot Be Valid
            return false;
        }
        return true;
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
