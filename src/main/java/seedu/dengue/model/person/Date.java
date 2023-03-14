package seedu.dengue.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


/**
 * Represents a Person's dengue case date in the Dengue Hotspot Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {


    public static final String MESSAGE_CONSTRAINTS = "Dates should be of the format yyyy-MM-dd"
            + "and adhere to the following constraints:\n"
            + "1. Date to be formatted as yyyy-MM-dd in 3 blocks separated by a hyphen '-'\n"
            + " - Each block should only contain digits, and no other special characters\n"
            + "2. Dates must be valid. i.e.\n"
            + " - MM must be a number between 01 and 12 inclusive\n"
            + " - dd must be a number between 01 and 31 inclusive\n"
            + " - dd should be within the valid range for the MM given";

    private static final String VALIDATION_DATE = "uuuu-MM-dd";
    private static final DateTimeFormatter VALIDATION_FORMAT = DateTimeFormatter.ofPattern(VALIDATION_DATE)
            .withResolverStyle(ResolverStyle.STRICT);
    public final String value;

    /**
     * Constructs an {@code Date}.
     *
     * @param date A Date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, VALIDATION_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
