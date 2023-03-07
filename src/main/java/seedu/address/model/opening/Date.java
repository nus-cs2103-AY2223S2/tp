package seedu.address.model.opening;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Represents an Opening's date in the address book.
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should only be in the format yyyy-MM-dd e.g. 2023-01-01";

    public final String fullDate;
    public final String fullName;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String name, String date) {
        requireNonNull(date);
        requireNonNull(name);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        fullDate = date;
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateFormat.parse(test);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return fullName + ": " + fullDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && fullName.equals(((Date) other).fullName)
                && fullDate.equals(((Date) other).fullDate)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, fullDate);
    }
}
