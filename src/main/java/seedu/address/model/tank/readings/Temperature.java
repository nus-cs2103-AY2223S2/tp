package seedu.address.model.tank.readings;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.tank.Tank;

/**
 * Represents a Tank's temperature in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTemperature(String, String)}
 */
public class Temperature extends Reading {

    public static final String MESSAGE_CONSTRAINTS =
            "Temperature should be a double, and it should not be blank Date format is DD/MM/YYYY";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX_VALUE = "^-?\\d+(\\.\\d+)?$";

    public static final String VALIDATION_REGEX_DATE = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";

    private double value;

    /**
     * Constructs a {@code Temperature}.
     *
     * @param value A valid temperature;
     * @param date A valid date;
     */
    public Temperature(String value, String date, Tank tank) {
        super(date, tank);
        requireNonNull(value);
        checkArgument(isValidTemperature(value, date), MESSAGE_CONSTRAINTS);
        this.value = Double.parseDouble(value);
    }

    /**
     * Returns true if the temperature input is valid
     * @param value value
     * @param date date
     * @return true if valid
     */
    public static boolean isValidTemperature(String value, String date) {
        return value.matches(VALIDATION_REGEX_VALUE)
                && date.matches(VALIDATION_REGEX_DATE);
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Temperature // instanceof handles nulls
                && localDate.equals(((Temperature) other).localDate)); // state check
    }

    @Override
    public int hashcode() {
        return (dateString + value).hashCode();
    }

    @Override
    public String toString() {
        return String.format("[%f, %s] [Temperature]", value, dateString);
    }
}
