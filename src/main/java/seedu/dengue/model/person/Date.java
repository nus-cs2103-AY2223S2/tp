package seedu.dengue.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.commons.util.AppUtil.checkArgument;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents a Person's dengue case date in the Dengue Hotspot Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {


    public static final String MESSAGE_CONSTRAINTS = "Dates should be of the format yyyy-mm-dd HH:mm"
            + "and adhere to the following constraints:\n"
            + "1. Each block should only contain digits, and no other special characters\n"
            + "2. Each block is separated by a colon ':'\n"
            + "3. Dates must be valid. i.e.\n"
            + " - mm cannot take values >12\n"
            + " - HH:mm cannot go beyond 23:59";
    private static final String VALIDATION_DATE = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter VALIDATION_FORMAT = DateTimeFormatter.ofPattern(VALIDATION_DATE);



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
            LocalDateTime.parse(test, VALIDATION_FORMAT);
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
