package seedu.address.model.tank.readings;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tank.Tank;

/**
 * Represents a Tank's last fed date number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmmoniaLevel(String)}
 */
public class AmmoniaLevel extends Reading {

    public static final String MESSAGE_CONSTRAINTS =
            "Ammonia Level should be a double, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^-?\\d+(\\.\\d+)?$";
    private int value;

    /**
     * Constructs a {@code AmmoniaLevel}.
     *
     * @param value A valid AmmoniaLevel;
     * @param date A valid date;
     */
    public AmmoniaLevel(String value, String date, Tank tank) {
        super(date, tank);
        requireNonNull(value);
        checkArgument(isValidAmmoniaLevel(value), MESSAGE_CONSTRAINTS);
        this.value = Integer.parseInt(value);
    }

    public boolean isValidAmmoniaLevel(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AmmoniaLevel // instanceof handles nulls
                && localDate.equals(((AmmoniaLevel) other).localDate)); // state check
    }

    @Override
    public int hashcode() {
        return (dateString + value).hashCode();
    }
}
