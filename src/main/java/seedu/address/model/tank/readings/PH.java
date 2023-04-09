package seedu.address.model.tank.readings;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tank.Tank;

/**
 * Represents a Tank's PH Level in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPH(String, String)}
 */
public class PH extends Reading {

    public static final String MESSAGE_CONSTRAINTS =
            "PH should be a number between 0 and 14, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX_VALUE = "^-?\\d+(\\.\\d+)?$";

    public static final String VALIDATION_REGEX_DATE =
            "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4} (?:[01]\\d|2[0-3]):[0-5]\\d$";

    private double value;

    /**
     * Constructs a {@code PH}.
     *
     * @param value A valid PH;
     * @param date A valid date;
     */
    public PH(String value, String date, Tank tank) {
        super(date, tank);
        requireNonNull(value);
        checkArgument(isValidPH(value, date), MESSAGE_CONSTRAINTS);
        this.value = Double.parseDouble(value);
    }

    /**
     * Returns true if the PH input is valid
     * @param value value
     * @param date date
     * @return true if valid
     */
    public static boolean isValidPH(String value, String date) {
        if (value.matches(VALIDATION_REGEX_VALUE) && date.matches(VALIDATION_REGEX_DATE)) {
            double valueDouble = Double.parseDouble(value);
            return (valueDouble >= 0 && valueDouble <= 14);
        }
        return false;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PH // instanceof handles nulls
                && localDateTime.equals(((PH) other).localDateTime)); // state check
    }

    @Override
    public int hashcode() {
        return (dateString + value).hashCode();
    }

    @Override
    public String toString() {
        return String.format("[%f, %s] [pH]", value, dateString);
    }
}
