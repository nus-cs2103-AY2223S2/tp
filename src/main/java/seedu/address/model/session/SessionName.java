package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Session's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class SessionName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String sessionName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public SessionName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        String lowerCaseName = name.toLowerCase();
        sessionName = lowerCaseName.substring(0, 1).toUpperCase() + lowerCaseName.substring(1);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String get() {
        return sessionName;
    }

    @Override
    public String toString() {
        return sessionName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SessionName // instanceof handles nulls
                && sessionName.equalsIgnoreCase(((SessionName) other).sessionName)); // state check
    }

    @Override
    public int hashCode() {
        return sessionName.hashCode();
    }

}
