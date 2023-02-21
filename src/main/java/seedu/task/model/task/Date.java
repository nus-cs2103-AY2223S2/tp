package seedu.task.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.task.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Represents a Task's date in the task book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS = "Date must be valid";
    public final LocalDateTime value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(date);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        String[] dateTime = test.trim().split(" ");
        try {
            String date = dateTime[0];
            String time = dateTime[1];
            String hour = time.substring(0, 2);
            String minute = time.substring(2, 4);
            String toParse = date + "T" + hour + ":" + minute + ":00";
            LocalDateTime.parse(toParse);
        } catch (DateTimeException | IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LocalDateTime // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
