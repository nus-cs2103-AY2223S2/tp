package seedu.address.model.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Delivery's earning in the delivery jobs book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEarning(String)}
 */
public class Earning implements Comparable<Earning> {

    public static final String MESSAGE_CONSTRAINTS = "Earning should only contain double, and it should not be blank";

    public static final String VALIDATION_REGEX = "\\d+";
    public static final String VALIDATION_REGEX_DECI = "\\d+\\.\\d+";

    public final String value;
    public final String dollar;
    public final String cent;

    /**
     * Constructs an {@code earning}.
     *
     * @param earning A valid earning.
     */
    public Earning(String earning) {
        requireNonNull(earning);
        checkArgument(isValidEarning(earning), MESSAGE_CONSTRAINTS);
        value = Double.toString(Double.parseDouble(earning));

        String[] format = earning.split("\\.");
        if (format.length == 2) {
            dollar = format[0];
            cent = String.format("%-2s", format[1]).replace(' ', '0').substring(0, 2);
        } else {
            dollar = format[0];
            cent = "00";
        }
    }

    /**
     * Returns earning in double data type
     */
    public double getEarning() {
        return Double.parseDouble(value);
    }

    /**
     * Returns new earning as 0.0
     */
    public static Earning placeholder() {
        return new Earning("0.00");
    }

    /**
     * Returns true if a given string is a valid earning.
     */
    public static boolean isValidEarning(String test) {
        return test.matches(VALIDATION_REGEX) || test.matches(VALIDATION_REGEX_DECI);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Earning // instanceof handles nulls
                && value.equals(((Earning) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Parses string value to double.
     *
     * @return
     */
    public double toDouble() {
        return Double.parseDouble(value);
    }

    @Override
    public int compareTo(Earning other) {
        if (this.getEarning() - other.getEarning() < 0) {
            return 1;
        } else if (this.getEarning() - other.getEarning() > 0) {
            return -1;
        }
        return 0;
    }
}
