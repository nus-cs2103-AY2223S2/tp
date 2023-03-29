package seedu.ultron.model.opening;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Represents an Opening's date in the address book.
 */
public class Keydate {
    public static final String MESSAGE_CONSTRAINTS =
            "Keys should not be blank or only contain whitespaces. Keys and dates should be directly connected by '@'."
                    + " Dates should only be in the format YYYY-MM-DD e.g. Interview@2023-01-01";

    public final String fullDate;
    public final String fullName;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Keydate(String name, String date) {
        requireNonNull(date);
        requireNonNull(name);
        checkArgument(isValidKeydate(date), MESSAGE_CONSTRAINTS);
        fullDate = date;
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidKeydate(String test) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
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
                || (other instanceof Keydate // instanceof handles nulls
                && fullName.equals(((Keydate) other).fullName)
                && fullDate.equals(((Keydate) other).fullDate)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, fullDate);
    }
}
