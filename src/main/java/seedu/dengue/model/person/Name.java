package seedu.dengue.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the Dengue Hotspot Tracker.
 * A name can only contain alphabets (capital or otherwise), and whitespaces.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphabets and spaces, and it should not be blank."
                    + "\nTrimmed names can have a maximum length of 54 characters.";


    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z\\s]*";
    private static final int MAX_LENGTH = 54;
    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = trimName(name);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        test = trimName(test);
        return test.matches(VALIDATION_REGEX) && test.length() <= MAX_LENGTH;
    }

    /**
     * Trims a name to delete excessive whitespaces.
     * @param name A name.
     * @return A trimmed name, where excessive (consecutive) whitespaces,
     *     as well as whitespaces at the front and back are removed.
     */
    public static String trimName(String name) {
        return name.trim().replaceAll("\\s{2,}", " ");
    }
    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
