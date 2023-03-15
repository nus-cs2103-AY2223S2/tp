package seedu.address.model.tank;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tank's name in the {@code Tanklist}.
 * Guarantees: immutable; is valid as declared in {@link #isValidTankName(String)}
 */
public class TankName {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullTankName;

    /**
     * Constructs a {@code TankName}.
     *
     * @param name A valid tank name.
     */
    public TankName(String name) {
        requireNonNull(name);
        checkArgument(isValidTankName(name), MESSAGE_CONSTRAINTS);
        fullTankName = name;
    }

    /**
     * Returns true if a given string is a valid Tank name.
     */
    public static boolean isValidTankName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullTankName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TankName // instanceof handles nulls
                && fullTankName.equals(((TankName) other).fullTankName)); // state check
    }

    @Override
    public int hashCode() {
        return fullTankName.hashCode();
    }
}
