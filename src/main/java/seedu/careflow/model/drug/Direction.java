package seedu.careflow.model.drug;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.AppUtil.checkArgument;

/**
 * Represents a drug's direction in the drug inventory
 */
public class Direction {
    public static final String MESSAGE_CONSTRAINTS =
            "Direction should be in sentence form, it should not be blank and less than 500 characters long";
    public static final String VALIDATION_REGEX = "[0-9a-zA-Z][a-zA-Z0-9: ,.-]{0,499}";
    public final String value;

    /**
     * Constructs a {@code Direction}.
     *
     * @param direction A valid drug direction.
     */
    public Direction(String direction) {
        requireNonNull(direction);
        checkArgument(isValidDirection(direction), MESSAGE_CONSTRAINTS);
        this.value = direction;
    }

    /**
     * Returns true if a given string is a valid drug direction.
     */
    public static boolean isValidDirection(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Direction // instanceof handles nulls
                && value.equals(((Direction) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
