package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's platoon in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPlatoon(String)}
 */
public class Platoon {

    public static final String MESSAGE_CONSTRAINTS =
            "Platoon should be alphanumeric phrase or word";

    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9 /]+$";

    public final String value;

    /**
     * Constructs a {@code Platoon}.
     *
     * @param platoon A valid platoon.
     */
    public Platoon(String platoon) {
        requireNonNull(platoon);
        checkArgument(isValidPlatoon(platoon), MESSAGE_CONSTRAINTS);
        value = platoon;
    }

    /**
     * Returns true if a given string is a valid platoon.
     */
    public static boolean isValidPlatoon(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Platoon // instanceof handles nulls
                && value.equals(((Platoon) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
