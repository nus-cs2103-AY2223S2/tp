package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's unit in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidUnit(String)}
 */
public class Unit {

    public static final String MESSAGE_CONSTRAINTS =
            "Unit should be alphanumeric phrase or word";

    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9 /]+$";

    public final String value;

    /**
     * Constructs a {@code Unit}.
     *
     * @param unit A valid unit.
     */
    public Unit(String unit) {
        requireNonNull(unit);
        checkArgument(isValidUnit(unit), MESSAGE_CONSTRAINTS);
        value = unit;
    }

    /**
     * Returns true if a given string is a valid unit.
     */
    public static boolean isValidUnit(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit // instanceof handles nulls
                && value.equals(((Unit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
