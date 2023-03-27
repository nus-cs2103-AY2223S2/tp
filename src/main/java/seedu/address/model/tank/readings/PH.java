package seedu.address.model.tank.readings;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tank.Tank;

/**
 * Represents a Tank's PH Level in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPH(String)}
 */
public class PH extends Reading {

    public static final String MESSAGE_CONSTRAINTS =
            "PH should be a double, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^-?\\d+(\\.\\d+)?$";
    private int value;

    /**
     * Constructs a {@code PH}.
     *
     * @param value A valid PH;
     * @param date A valid date;
     */
    public PH(String value, String date, Tank tank) {
        super(date, tank);
        requireNonNull(value);
        checkArgument(isValidPH(value), MESSAGE_CONSTRAINTS);
        this.value = Integer.parseInt(value);
    }

    public boolean isValidPH(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PH // instanceof handles nulls
                && localDate.equals(((PH) other).localDate)); // state check
    }

    @Override
    public int hashcode() {
        return (dateString + value).hashCode();
    }
}
